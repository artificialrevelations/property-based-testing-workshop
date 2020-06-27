package org.artrev.workshop.pbt.examples.shoppingcart.command;

import org.artrev.workshop.pbt.examples.shoppingcart.Command;
import org.artrev.workshop.pbt.examples.shoppingcart.Product;
import org.artrev.workshop.pbt.examples.shoppingcart.Quantity;
import org.artrev.workshop.pbt.examples.shoppingcart.ShoppingCart;
import org.artrev.workshop.pbt.examples.shoppingcart.model.ShoppingCartModel;
import org.junit.jupiter.api.Assertions;

// Is there something like a full shopping cart?
public class AddProductCommand implements Command<ShoppingCartModel, ShoppingCart> {
    private final Product product;
    private final Quantity quantity;

    public AddProductCommand(final Product product, final Quantity quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    @Override
    public void execute(final ShoppingCartModel model,
                        final ShoppingCart sut) {
        model.addProduct(
                product.getName(),
                quantity.getValue(),
                product.getPrice().getValue()
        );
        sut.add(product, quantity);
    }

    @Override
    public void checkPostconditions(final ShoppingCartModel model,
                                    final ShoppingCart sut) {
        Assertions.assertEquals(
                model.getQuantity(product.getName()),
                sut.get(product).getValue()
        );
    }
}
