package org.artrev.workshop.pbt.examples.shoppingcart;

import java.util.Optional;

public final class Discount {
    private final int value;

    private Discount(final int value) {
        this.value = value;
    }

    public Price applyTo(final Price price) {
        final int discountedPriceValue =
                (price.getValue() * value) / 100;
        return Price.from(price.getValue() - discountedPriceValue).get();
    }

    public static Optional<Discount> from(final int value) {
        if (value >= 0 && value <= 100) {
            return Optional.of(new Discount(value));
        }
        return Optional.empty();
    }

    public static Discount FREE = new Discount(100);
    public static Discount NONE = new Discount(0);

    @Override
    public boolean equals(final Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;

        final Discount discount = (Discount) other;

        return value == discount.value;
    }

    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "value=" + value +
                '}';
    }
}
