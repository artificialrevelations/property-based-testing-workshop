package org.artrev.workshop.pbt.examples.shoppingcart.commands;

import org.artrev.workshop.pbt.examples.shoppingcart.Price;
import org.artrev.workshop.pbt.examples.shoppingcart.ShoppingCart;
import org.artrev.workshop.pbt.examples.shoppingcart.model.ShoppingCartModel;
import org.junit.jupiter.api.Assertions;

public final class GetTotalPriceCommand implements Command<ShoppingCartModel, ShoppingCart> {
    private Price modelTotalPrice;
    private Price sutTotalPrice;

    @Override
    public void execute(final ShoppingCartModel model,
                        final ShoppingCart sut) {
        modelTotalPrice = Price.from(model.getTotalPrice()).get();
        sutTotalPrice = sut.getTotalPrice();
    }

    @Override
    public void postcondition(ShoppingCartModel model, ShoppingCart sut) {
        Assertions.assertEquals(
                modelTotalPrice,
                sutTotalPrice,
                "Total price of products in model does not equal to total price in SUT"
        );
    }
}
