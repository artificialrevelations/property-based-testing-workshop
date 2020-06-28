package org.artrev.workshop.pbt.examples.shoppingcart.commands;

import org.artrev.workshop.pbt.examples.shoppingcart.Product;
import org.artrev.workshop.pbt.examples.shoppingcart.Quantity;
import org.artrev.workshop.pbt.examples.shoppingcart.ShoppingCart;
import org.artrev.workshop.pbt.examples.shoppingcart.model.ShoppingCartModel;
import org.junit.jupiter.api.Assertions;

public final class RemoveProductCommand implements Command<ShoppingCartModel, ShoppingCart> {
    private final Product product;
    private final Quantity quantity;

    public RemoveProductCommand(final Product product,
                                final Quantity quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    @Override
    public boolean precondition(final ShoppingCartModel model,
                                final ShoppingCart sut) {
        // we can only remove a product if there is something
        return !model.isEmpty();
    }

    @Override
    public void execute(final ShoppingCartModel model,
                        final ShoppingCart sut) {
        model.removeProduct(product.getName(), quantity.getValue());
        sut.remove(product, quantity);
    }

    @Override
    public void postcondition(ShoppingCartModel model, ShoppingCart sut) {
        final int modelQuantity =
                model.getQuantity(product.getName());
        final int sutQuantity =
                sut.get(product).map(Quantity::getValue).orElse(0);

        Assertions.assertEquals(
                modelQuantity,
                sutQuantity
        );
    }

    @Override
    public String toString() {
        return "RemoveProductCommand{" +
                " product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
