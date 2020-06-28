package org.artrev.workshop.pbt.examples.shoppingcart;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ShoppingCart {
    private final List<ShoppingCartItem> products = new ArrayList<>();
    private Discount discount = Discount.NONE;

    public Optional<Quantity> get(final Product product) {
        return products
                .stream()
                .filter(item -> item.getProduct().equals(product))
                .map(ShoppingCartItem::getQuantity)
                .findFirst();
    }

    public Set<Product> getAllProducts() {
        return products
                .stream()
                .map(ShoppingCartItem::getProduct)
                .collect(Collectors.toSet());
    }

    public void add(final Product product,
                    final Quantity quantity) {

        final Optional<ShoppingCartItem> cartItem =
                products
                        .stream()
                        .filter(item -> item.getProduct().equals(product))
                        .findFirst();

        final Quantity newQuantity =
                cartItem
                        .map(item -> item.getQuantity().plus(quantity))
                        .orElse(quantity);

        cartItem.ifPresent(products::remove);

        products.add(new ShoppingCartItem(
                product,
                newQuantity
        ));
    }

    public Quantity remove(final Product product,
                           final Quantity quantity) {

        return Quantity.NOTHING;
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
                        .stream()
                        .map(item -> {
                            final Product product = item.getProduct();
                            final Quantity quantity = item.getQuantity();
                            return product.getPrice().times(quantity);
                        })
                        .reduce(Price.FREE, Price::plus)
        );
    }

    public Quantity getTotalQuantity() {
        return products
                .stream()
                .map(ShoppingCartItem::getQuantity)
                .reduce(Quantity.NOTHING, Quantity::plus);
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "\n\t\tproducts=" + products +
                "\n\t\tdiscount=" + discount +
                "\n\t}";
    }
}
