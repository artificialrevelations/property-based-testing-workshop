package org.artrev.workshop.pbt.examples.shoppingcart;

import java.util.Objects;

public class ShoppingCartItem {
    private final Product product;
    private final Quantity quantity;

    public ShoppingCartItem(final Product product,
                            final Quantity quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        final ShoppingCartItem that = (ShoppingCartItem) other;
        return Objects.equals(product, that.product) &&
                Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, quantity);
    }

    @Override
    public String toString() {
        return "ShoppingCartItem{" +
                "\n\t\tproduct=" + product +
                "\n\t\tquantity=" + quantity +
                "\n\t}";
    }
}
