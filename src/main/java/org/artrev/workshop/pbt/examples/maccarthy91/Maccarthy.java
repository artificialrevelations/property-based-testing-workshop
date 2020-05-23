package org.artrev.workshop.pbt.examples.maccarthy91;

/**
 * This class contains several different implementations of McCarthy 91 function.
 *
 * As described by the Wikipedia:
 * The McCarthy 91 function is a recursive function, defined by the computer
 * scientist John McCarthy as a test case for formal verification within computer
 * science.
 *
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

    public static int maccarthy91_nonrecursive(final int n) {
        if (n > 100) {
            return n - 10;
        } else {
            return 91;
        }
    }

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
