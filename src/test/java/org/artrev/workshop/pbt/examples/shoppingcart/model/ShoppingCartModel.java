package org.artrev.workshop.pbt.examples.shoppingcart.model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ShoppingCartModel {
    private final List<ShoppingCartModelElement> products = new LinkedList<>();
    private int discount = 0; //%

    public void addProduct(final String product,
                           final int price,
                           final int quantity) {
        for (final ShoppingCartModelElement element : products) {
            if (element.product.contentEquals(product)) {
                element.quantity = element.quantity + quantity;
                return;
            }
        }

        products.add(new ShoppingCartModelElement(product, price, quantity));
    }

    public void removeProduct(final String product, final int quantity) {
        final Iterator<ShoppingCartModelElement> iterator = products.iterator();
        while (iterator.hasNext()) {
            final ShoppingCartModelElement element = iterator.next();
            if (element.product.contentEquals(product)) {
                final int newQuantity = element.quantity - quantity;
                if (newQuantity > 0) {
                    element.quantity = newQuantity;
                } else {
                    iterator.remove();
                }
                return;
            }
        }
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    public int getQuantity(final String product) {
        for (final ShoppingCartModelElement element : products) {
            if (element.product.contentEquals(product)) {
                return element.quantity;
            }
        }
        return 0;
    }

    public int getTotalQuantity() {
        int total = 0;
        for (final ShoppingCartModelElement element : products) {
            total += element.quantity;
        }
        return total;
    }

    public int getTotalPrice() {
        int total = 0;
        for (final ShoppingCartModelElement element : products) {
            total += element.quantity * element.price;
        }
        return total;
    }

    public void clear() {
        products.clear();
    }

    @Override
    public String toString() {
        return "ShoppingCartModel{" +
                "\n\t\tproducts=" + products +
                "\n\t\tdiscount=" + discount +
                "\n\t}";
    }

    private static class ShoppingCartModelElement {
        public String product;
        public int price;
        public int quantity;

        public ShoppingCartModelElement(
                final String product,
                final int price,
                final int quantity
        ) {
            this.product = product;
            this.price = price;
            this.quantity = quantity;
        }

        @Override
        public String toString() {
            return "ShoppingCartModelElement{" +
                    "\n\t\t\t\tproduct='" + product +
                    "\n\t\t\t\tprice=" + price +
                    "\n\t\t\t\tquantity=" + quantity +
                    "\n\t}";
        }
    }
}
