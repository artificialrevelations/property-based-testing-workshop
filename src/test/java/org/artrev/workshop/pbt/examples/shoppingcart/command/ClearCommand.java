package org.artrev.workshop.pbt.examples.shoppingcart.command;

import org.artrev.workshop.pbt.examples.shoppingcart.Command;
import org.artrev.workshop.pbt.examples.shoppingcart.ShoppingCart;
import org.artrev.workshop.pbt.examples.shoppingcart.model.ShoppingCartModel;
import org.junit.jupiter.api.Assertions;

// We can always clear a shopping cart
// Should we run this command only on a non empty shopping cart?
// Are certain nothing bad will happen if a chain of clears
// is invoked?
public class ClearCommand implements Command<ShoppingCartModel, ShoppingCart> {
    @Override
    public void execute(final ShoppingCartModel model,
                        final ShoppingCart sut) {
        model.clear();
        sut.clear();
    }

    @Override
    public void checkPostconditions(final ShoppingCartModel model,
                                    final ShoppingCart sut) {
        Assertions.assertEquals(
                model.getTotalQuantity(),
                sut.getTotalPrice().getValue()
        );
        Assertions.assertEquals(0, model.getTotalQuantity());
        Assertions.assertEquals(0, sut.getTotalPrice().getValue());
    }
}
