package org.artrev.workshop.pbt.examples.shoppingcart;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class ShoppingCart {
    // Here the implementation is simple but this could easily be something
    // that is using several different databases and microservices underneath
    private final Map<Product, Quantity> products = new HashMap<>();
    private Discount discount = Discount.NONE;

    public Optional<Quantity> get(final Product product) {
        return Optional.ofNullable(products.get(product));
    }

    public Set<Product> getAllProducts() {
        return products.keySet();
    }

    public Quantity add(final Product product, final Quantity additionalQuantity) {
        final Quantity newQuantity;
        if (products.containsKey(product)) {
            final Quantity currentQuantity = products.get(product);
            newQuantity = currentQuantity.plus(additionalQuantity);
        } else {
            newQuantity = additionalQuantity;
        }
        products.put(product, newQuantity);
        return newQuantity;
    }

    public Quantity remove(final Product product, final Quantity quantitiy) {
        if (products.containsKey(product)) {
            final Quantity currentQuantity = products.get(product);
            final Quantity newQuantity = currentQuantity.minus(quantitiy);
            if (newQuantity.equals(Quantity.NOTHING)) {
                products.remove(product);
            } else {
                products.put(product, newQuantity);
            }

            return newQuantity;
        }
        return Quantity.NOTHING;
    }

    public void clear(final Product product) {
        products.remove(product);
    }

    public void clear() {
        products.clear();
    }

    public void setDiscount(final Discount code) {
        if (null == code) {
            discount = Discount.NONE;
        } else {
            discount = code;
        }
    }

    public void clearDiscount() {
        discount = Discount.NONE;
    }

    public Price getTotalPrice() {
        return discount.applyTo(
                products
                        .entrySet()
                        .stream()
                        .map(entry -> {
                            final Product product = entry.getKey();
                            final Quantity quantity = entry.getValue();
                            return product.getPrice().times(quantity);
                        })
                        .reduce(Price.FREE, Price::plus)
        );
    }

    public Quantity getTotalQuantity() {
        return products
                .values()
                .stream()
                .reduce(Quantity.NOTHING, Quantity::plus);
    }
}
