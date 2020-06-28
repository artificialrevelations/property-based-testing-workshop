package org.artrev.workshop.pbt.examples.shoppingcart.commands;

import org.artrev.workshop.pbt.examples.shoppingcart.Price;
import org.artrev.workshop.pbt.examples.shoppingcart.ShoppingCart;
import org.artrev.workshop.pbt.examples.shoppingcart.model.ShoppingCartModel;
import org.junit.jupiter.api.Assertions;

public final class ClearDiscountCommand implements Command<ShoppingCartModel, ShoppingCart> {
    @Override
    public void execute(final ShoppingCartModel model,
                        final ShoppingCart sut) {
        model.clearDiscount();
        sut.clearDiscount();
    }

    @Override
    public void postcondition(final ShoppingCartModel model,
                              final ShoppingCart sut) {
        Assertions.assertEquals(
                Price.from(model.getTotalPrice()).get(),
                sut.getTotalPrice(),
                "Total price of products after clearing discount in model does not equal to total price in SUT"
        );
    }

    @Override
    public String toString() {
        return "ClearDiscountCommand{}";
    }
}
