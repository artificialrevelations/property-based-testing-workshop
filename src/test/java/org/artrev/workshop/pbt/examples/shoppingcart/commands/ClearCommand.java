package org.artrev.workshop.pbt.examples.shoppingcart.commands;

import org.artrev.workshop.pbt.examples.shoppingcart.ShoppingCart;
import org.artrev.workshop.pbt.examples.shoppingcart.model.ShoppingCartModel;
import org.junit.jupiter.api.Assertions;

public final class ClearCommand implements Command<ShoppingCartModel, ShoppingCart> {
    @Override
    public void execute(final ShoppingCartModel model,
                        final ShoppingCart sut) {
        model.clear();
        sut.clear();
    }

    @Override
    public void postcondition(final ShoppingCartModel model,
                              final ShoppingCart sut) {
        Assertions.assertEquals(
                model.getTotalQuantity(),
                sut.getTotalPrice().getValue()
        );
        Assertions.assertEquals(0, model.getTotalQuantity());
        Assertions.assertEquals(0, sut.getTotalPrice().getValue());
    }

    @Override
    public String toString() {
        return "ClearCommand{}";
    }
}
