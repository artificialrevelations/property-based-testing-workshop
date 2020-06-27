package org.artrev.workshop.pbt.examples.reverse;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.artrev.workshop.pbt.examples.reverse.Lists.reverse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListReverseExampleBasedPropertyTests {
    @Test
    public void reverse_reversed_equals_original() {
        // given:
        final List<String> tested = Arrays.asList("This", "is", "a", "test");
        // when:
        final List<String> result = reverse(reverse(tested));
        // then:
        assertEquals(tested, result);
        // Should we create more examples?
    }

    /*
     Can we be more certain if we will generate Lists of data to test?
     What do we need?
     - something that will generate different size lists for us
     - something that will fill the lists with random data
     - something that will pass the list to our "property" for check
     - something that will repeat the process several times

     First let's write a function that returns an integer from a given
     range:
     */
    private static int randInt(final Random seed, final int min, final int max) {
        return seed.nextInt((max - min) + 1) + min;
    }

    /*
      Then let's create a function that returns a List of random size filled
      with random data:
     */
    private static List<String> getRandomList(final Random seed) {
        // Here we limit our list to be of maximum size 100
        // this is not very useful as we might be hiding bugs
        // what if errors occur for Lists of sizes > 100?
        // but for the simplicity of the example we will leave
        // it as such
        final int randomSize = randInt(seed, 0, 100);
        final List<String> randomizedList = new ArrayList<>(randomSize);
        for (int i = 0; i < randomSize; i++) {
            randomizedList.add(String.valueOf(randInt(seed, 0, 100)));
        }
        return randomizedList;
    }

    /*
      Now we need to define the property in some convenient way. Let's
      define and interface that will describe our property:
     */
    private interface Property<A> {
        boolean check(A value);
    }

    /*
      Now let's use this newly created interface to describe our
      reverse property.
     */
    private static <A> Property<List<A>> reverseOfReversedEqualsOriginal() {
        return original -> reverse(reverse(original)).equals(original);
    }

    /*
      Our property is generic, why not data generation? Let's have an interface
      for it similar to the property. Let's feed it with a random seed, so we can
      control the data generation.
     */
    private interface Generator<A> {
        A generate(Random seed);
    }

    /*
     Let's prepare a generator for our example using our getRandomList function
     that we've created.
     */
    private Generator<List<String>> randomStringListsGenerator() {
        return seed -> {
            final int randomSize = randInt(seed, 0, 100);
            final List<String> randomizedList = new ArrayList<>(randomSize);
            for (int i = 0; i < randomSize; i++) {
                randomizedList.add(String.valueOf(randInt(seed, 0, 100)));
            }
            return randomizedList;
        };
    }

    /*
     Let's use all the interfaces to creates a mini framework for
     testing properties:
     */
    private static <A> void quickCheck(final long seedValue,
                                       final int numberOfTries,
                                       final Property<A> property,
                                       final Generator<A> generator) {
        final Random seed = new Random(seedValue);
        for (int i = 0; i < numberOfTries; i++) {
            // Let's generate the test example
            final A tested = generator.generate(seed);
            // and try it against our property
            final boolean result = property.check(tested);
            // if the property finds a problem with our algorithm
            // let's log information about the seed and example
            if (!result) {
                final StringBuilder builder =
                        new StringBuilder()
                                .append("Property test failed")
                                .append("\nSeed value: ")
                                .append(seedValue)
                                .append("\nExample data: ")
                                .append(tested);
                throw new AssertionError(builder);
            }
        }
    }

    /*
      Now we need to use all of the created elements to get a test:
     */
    @Test
    public void custom_reverse_property_test() {
        // Let's try 1000 times to find a counterexample
        final int numberOfTries = 1000;
        // this value will help us with reproducing the issue
        // if any is found
        final long seedValue = new Random().nextLong();

        // Let's prepare our property, it's nice that it does not
        // rely on the type but is fully generic
        final Property<List<String>> reverseProperty =
                reverseOfReversedEqualsOriginal();

        final Generator<List<String>> generator =
                randomStringListsGenerator();

        quickCheck(seedValue, numberOfTries, reverseProperty, generator);
    }

    /*
      How can we know if this little framework works correctly?
      Let's introduce a property that will immediately fail:
     */
    private static <A> Property<List<A>> reverseWrongProperty() {
        // this is obviously wrong as reversed list cannot be equal to the original
        return original -> reverse(original).equals(original);
    }

    @Test
    public void custom_reverse_property_fail_test() {
        final int numberOfTries = 1000;

        final Property<List<String>> reverseProperty =
                reverseWrongProperty();

        final Generator<List<String>> generator =
                randomStringListsGenerator();

        final long seedValue = new Random().nextLong();
        quickCheck(seedValue, numberOfTries, reverseProperty, generator);
    }

    /*
      Let's use a seed that was used in the failing example and run a test
      with it to reproduce the results.
     */
    @Test
    public void custom_reverse_property_from_seed() {
        final int numberOfTries = 1000;

        final Property<List<String>> reverseProperty =
                reverseWrongProperty();

        final Generator<List<String>> generator =
                randomStringListsGenerator();

        /*
          this time we want to repeat the failing case:

          java.lang.AssertionError: Property test failed
          Seed value: -2569510089704470893
          Example data: [40, 15, 20, 30, 36, 35, 55, 99, 89, 93, 67, 27, 31, 95, 26, 6, 84, 23, 92]
         */
        final long seedValue = -2569510089704470893L;
        quickCheck(seedValue, numberOfTries, reverseProperty, generator);
    }

    /*
      What else do we need?

      There are a few things that would a nice thing to have:
      - if we could not have to repeat the test 1000 times even if we pass the failing seed.

      In the implementation above although we can repeat the tests with the same random
      number generator thanks to the seed value, we cannot just repeat the one failing
      example that our "framework" has found. To be completely honest we can because
      we can just copy the example data that was printed but we would have to write another
      tests where we could pass this example data to, there is no easy and quick way to do
      it.

      - if we could receive the smallest failing example as possible!

      In the example seed -2569510089704470893 the list that caused the property to fail was
      rather small but it could be much much shorter instead. The wrong property fails for a list
      as short as [1, 2]. Our "framework" does not have a capability of shrinking the failing
      example unfortunately.
     */
}
