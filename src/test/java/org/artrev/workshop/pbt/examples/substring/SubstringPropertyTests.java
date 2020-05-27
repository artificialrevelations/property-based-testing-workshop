package org.artrev.workshop.pbt.examples.substring;

import net.jqwik.api.*;

public class SubstringPropertyTests {
    /*
      Let's check if the substring just works correctly with
      the arguments we will supply to it. We are invoking
      the function without checking the result, we just
      expect not to see any exceptions
     */
    @Property
    public void substringShouldJustWork(@ForAll String string,
                                        @ForAll Integer index) {
        string.substring(index);
    }

    /*
      First failure is found immediately after running the test:
      sample = ["", -1]

      Empty sting and index equal to -1, this can be fixed by
      some initial assumptions:
     */
    @Property
    public void substringShouldJustWork_FixAttempt1(@ForAll String string,
                                                    @ForAll Integer index) {
        Assume.that(index >= 0);
        Assume.that(index < string.length());

        string.substring(index);
    }

    /*
      The main issue is that we are generating a lot of examples that are
      wasted as they either have incorrect index or the string is empty.

      This is visible in the report:
      tries = 1000                  | # of calls to property
      checks = 92                   | # of not rejected calls

      For 1000 tries we did only 92 checks, this is a scary waste of resources.

      We need to cleverly generate the correct data. First let's create a class
      that will express our test data:
     */
    static final class SubstringTestCase {
        public final String string;
        public final Integer index;

        public SubstringTestCase(
                final String string,
                final Integer index
        ) {
            this.string = string;
            this.index = index;
        }
    }

    @Provide
    public Arbitrary<SubstringTestCase> substringTestCases() {
        /*
          We want strings and indexes that are between 0 and the size of the string to match
          our assumptions from the previous example.
         */
        return Arbitraries.strings()
                .flatMap(generatedString -> Arbitraries.integers()
                        .between(0, generatedString.length())
                        .map(generatedIndex ->
                                new SubstringTestCase(generatedString, generatedIndex)
                        )
                );
    }

    @Property
    public void substringShouldJustWork_FixAttempt1(@ForAll("substringTestCases") SubstringTestCase testCase) {
        testCase.string.substring(testCase.index);
    }

    /*
      The code above gave us better results, as from report:

      tries = 1000                  | # of calls to property
      checks = 1000                 | # of not rejected calls
     */
}
