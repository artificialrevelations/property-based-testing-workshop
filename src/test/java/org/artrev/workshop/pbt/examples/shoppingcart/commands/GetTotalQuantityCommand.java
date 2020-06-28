package org.artrev.workshop.pbt.examples.shoppingcart.commands;

import org.artrev.workshop.pbt.examples.shoppingcart.Quantity;
import org.artrev.workshop.pbt.examples.shoppingcart.ShoppingCart;
import org.artrev.workshop.pbt.examples.shoppingcart.model.ShoppingCartModel;
import org.junit.jupiter.api.Assertions;

public final class GetTotalQuantityCommand implements Command<ShoppingCartModel, ShoppingCart> {
    private Quantity sutTotalQuantity;
    private Quantity modelTotalQuantity;

    @Override
    public void execute(final ShoppingCartModel model,
                        final ShoppingCart sut) {
        sutTotalQuantity = sut.getTotalQuantity();
        modelTotalQuantity = Quantity.from(model.getTotalQuantity()).get();
    }

    @Override
    public void postcondition(final ShoppingCartModel model,
                              final ShoppingCart sut) {
        Assertions.assertEquals(
                modelTotalQuantity,
                sutTotalQuantity,
                "Total quantity of products in model does not equal to total quantity in SUT"
        );
    }

    @Override
    public String toString() {
        return "GetTotalQuantityCommand{}";
    }
}
