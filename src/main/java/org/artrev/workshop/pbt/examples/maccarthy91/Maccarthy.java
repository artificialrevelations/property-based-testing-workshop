package org.artrev.workshop.pbt.examples.maccarthy91;

/**
 * This class contains several different implementations of McCarthy 91 function.
 * <p>
 * As described by the Wikipedia:
 * The McCarthy 91 function is a recursive function, defined by the computer
 * scientist John McCarthy as a test case for formal verification within computer
 * science.
 * <p>
 * For more information please check the page:
 * https://en.wikipedia.org/wiki/McCarthy_91_function
 */
public class Maccarthy {
    private Maccarthy() {
        throw new IllegalStateException("Maccarthy should not be instantiated!");
    }

    public static int maccarthy91_recursive(final int n) {
        if (n > 100) {
            return n - 10;
        } else {
            return maccarthy91_recursive(maccarthy91_recursive(n + 11));
        }
    }

    public static int maccarthy91_tailrecursive(final int n) {
        return maccarthy91_internal(n, 1);
    }

    private static int maccarthy91_internal(final int n, final int c) {
        if (c == 0) {
            return n;
        } else if (n > 100) {
            return maccarthy91_internal(n - 10, c - 1);
        } else {
            return maccarthy91_internal(n + 11, c + 1);
        }
    }

    /**
     * The simplest implementation of the function without nested
     * recursion, tail recursion, iteration. Function does not
     * perform any complicated calculation and returns value 91
     * for any argument that is less or equal to 100. For values
     * above 100 it returns value minus 10.
     *
     * @param n value
     * @return n - 10 for values greater then 100, 91 otherwise.
     */
    public static int maccarthy91_nonrecursive(final int n) {
        if (n > 100) {
            return n - 10;
        } else {
            return 91;
        }
    }

    /**
     *
     * @param n
     * @return
     */
    public static int maccarthy91_iterative(final int n) {
        int counter = 1;
        int accumulator = n;
        while (counter != 0) {
            if (accumulator > 100) {
                accumulator = accumulator - 10;
                counter = counter - 1;
            } else {
                accumulator = accumulator + 11;
                counter = counter + 1;
            }
        }
        return accumulator;
    }
}
