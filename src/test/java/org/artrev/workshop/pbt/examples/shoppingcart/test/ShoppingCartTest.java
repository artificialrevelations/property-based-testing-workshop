package org.artrev.workshop.pbt.examples.shoppingcart.test;

import net.jqwik.api.*;
import net.jqwik.api.Tuple.Tuple2;
import net.jqwik.api.stateful.ActionSequence;
import org.artrev.workshop.pbt.examples.shoppingcart.ShoppingCart;
import org.artrev.workshop.pbt.examples.shoppingcart.model.ShoppingCartModel;

import static org.artrev.workshop.pbt.examples.shoppingcart.test.ShoppingCartCommands.*;

public class ShoppingCartTest {
    @Property
    void checkShoppingCart(@ForAll("commands") ActionSequence<Tuple2<ShoppingCartModel, ShoppingCart>> actions) {
        actions.run(Tuple.of(
                new ShoppingCartModel(),
                new ShoppingCart()
        ));
    }

    @Provide
    Arbitrary<ActionSequence<Tuple2<ShoppingCartModel, ShoppingCart>>> commands() {
        return Arbitraries.sequences(
                Arbitraries.oneOf(
                        getAddProductCommands(),
                        getClearCommands(),
                        getRemoveProductCommand(),
                        getGetTotalQuantityCommand(),
                        getGetTotalPriceCommand()
                )
        );
    }
}
