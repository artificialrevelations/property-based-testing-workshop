package org.artrev.workshop.pbt.examples.shoppingcart.commands;

import org.artrev.workshop.pbt.examples.shoppingcart.Product;
import org.artrev.workshop.pbt.examples.shoppingcart.Quantity;
import org.artrev.workshop.pbt.examples.shoppingcart.ShoppingCart;
import org.artrev.workshop.pbt.examples.shoppingcart.model.ShoppingCartModel;
import org.junit.jupiter.api.Assertions;

public final class AddProductCommand implements Command<ShoppingCartModel, ShoppingCart> {
    private final Product product;
    private final Quantity quantity;

    public AddProductCommand(final Product product,
                             final Quantity quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    @Override
    public void execute(final ShoppingCartModel model,
                        final ShoppingCart sut) {
        model.addProduct(
                product.getName(),
                product.getPrice().getValue(),
                quantity.getValue()
        );
        sut.add(product, quantity);
    }

    @Override
    public void postcondition(final ShoppingCartModel model,
                              final ShoppingCart sut) {
        Assertions.assertTrue(sut.get(product).isPresent());

        final int sutQuantity = sut.get(product).get().getValue();
        final int modelQuantity = model.getQuantity(product.getName());
        Assertions.assertEquals(
                modelQuantity,
                sutQuantity,
                "Product name = " + product.getName() + ", Model quantity = " + modelQuantity + ", SUT quantity = " + sutQuantity
        );
    }

    @Override
    public String toString() {
        return "AddProductCommand{" +
                " product=" + product +
                ", quantity=" + quantity +
                "}";
    }
}
