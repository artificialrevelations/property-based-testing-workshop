package org.artrev.workshop.pbt.examples.shoppingcart;

import java.util.Optional;

public final class Price {
    private int value = 0;

    private Price(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Price plus(final Price other) {
        return new Price(this.value + other.value);
    }

    public Price times(final Quantity quantity) {
        return new Price(this.value * quantity.getValue());
    }

    public static Optional<Price> from(final int value) {
        if (value >= 0) {
            return Optional.of(new Price(value));
        }

        return Optional.empty();
    }

    public static Price FREE = new Price(0);

    @Override
    public boolean equals(final Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;

        final Price price = (Price) other;

        return value == price.value;
    }

    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public String toString() {
        return "Price{ value=" + value + '}';
    }
}
