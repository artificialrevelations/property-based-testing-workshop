package org.artrev.workshop.pbt.examples.shoppingcart.commands;

import org.artrev.workshop.pbt.examples.shoppingcart.Command;
import org.artrev.workshop.pbt.examples.shoppingcart.Quantity;
import org.artrev.workshop.pbt.examples.shoppingcart.ShoppingCart;
import org.artrev.workshop.pbt.examples.shoppingcart.model.ShoppingCartModel;

public class RemoveProductCommand implements Command<ShoppingCartModel, ShoppingCart> {
    private final int productIndex;
    private final Quantity quantity;

    public RemoveProductCommand(final int productIndex,
                                final Quantity quantity) {
        this.productIndex = productIndex;
        this.quantity = quantity;
    }

    @Override
    public boolean precondition(final ShoppingCartModel model,
                                final ShoppingCart sut) {
        // we can only remove a product if there is something
        return !model.isEmpty() &&
                productIndex >= 0 &&
                productIndex < model.getNumberOfProducts();
    }

    @Override
    public void execute(final ShoppingCartModel model,
                        final ShoppingCart sut) {
//        final ProductModel productModel =
//                new ArrayList<>(model.getProducts()).get(productIndex);


    }

    @Override
    public void postcondition(ShoppingCartModel model, ShoppingCart sut) {

    }
}
