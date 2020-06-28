package org.artrev.workshop.pbt.examples.shoppingcart;

import java.io.Serializable;
import java.util.Optional;

/**
 * Describes the quantity of an item. Is a non negative value,
 * can be zero.
 */
public final class Quantity implements Comparable<Quantity>, Serializable {
    private int value = 0;

    private Quantity(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    /**
     * Adds one quantity to another.
     *
     * @param other quantity that needs to be added.
     * @return result of addition.
     */
    public Quantity plus(final Quantity other) {
        return new Quantity(this.value + other.value);
    }

    /**
     * Subtracts one quantity from another, if the quantity would be
     * negative then it is set to 0.
     *
     * @param other quantity that needs to be subtracted.
     * @return result of the subtraction.
     */
    public Quantity minus(final Quantity other) {
        return new Quantity(Math.max(0, this.value - other.value));
    }

    /**
     * Tries to create a quantity for the specified value. Value needs to be
     * non-negative.
     *
     * @param value Value for which the {@link Quantity} should
     *              be created.
     * @return Quantity wrapped in an Optional if the value satisfies the
     * conditions, Empty otherwise.
     */
    public static Optional<Quantity> from(final int value) {
        if (value >= 0) {
            return Optional.of(new Quantity(value));
        }
        return Optional.empty();
    }

    /**
     * For easily denoting a quantity of zero.
     */
    public static Quantity NOTHING = new Quantity(0);

    @Override
    public boolean equals(final Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;

        final Quantity quantity = (Quantity) other;

        return value == quantity.value;
    }

    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public int compareTo(final Quantity other) {
        return this.value - other.value;
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ')';
    }
}
