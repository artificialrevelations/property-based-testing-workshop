package org.artrev.workshop.pbt.examples.reverse;

import java.util.ArrayList;
import java.util.List;

/**
 * Collection of general purpose functions for working with {@link List}.
 */
public final class Lists {
    private Lists() {
        throw new IllegalStateException("Lists should not be instantiated!");
    }

    /**
     * This function returns a new instance of a list containing elements
     * from the given list but in the reverse order.
     *
     * @param original List containing elements that needs to be reversed.
     * @param <A>      List elements type
     * @return instance of an {@link ArrayList} with the elements of the
     * original list placed in the reversed order.
     */
    public static <A> List<A> reverse(final List<A> original) {
        final int size = original.size();
        final List<A> reversed = new ArrayList<>(size);
        // going from the last element to the first of the original list
        for (int i = size - 1; i >= 0; i--) {
            // thus adding the elements in the reversed order
            reversed.add(original.get(i));
        }
        return reversed;
    }
    /*
     What should be tested for this function?
     - reverse for empty list should return an empty list
     - reverse for single element list should return a single element list
     - reverse for single element list should return the same element
     - reverse for multiple element list should return a list of the same size
     - reverse for multiple element list should return a list with the same elements

     We now can create unit tests that check these scenarios. For each scenario
     we need to come up with some example data. If in our unit tests we will check
     a three element list, does it mean the algorithm will work for a five element
     list? how about 100 element list?

     The reverse function above has a generic argument, meaning we can pass
     different lists to it but our unit tests that are using example based approach
     need to specify the type. If reversing List<String> work, will reversing
     List<Integer> work also?

     As you see our doubts may only increase and will never be satisfied by mere
     example based approach. We need something better!
     */
}
