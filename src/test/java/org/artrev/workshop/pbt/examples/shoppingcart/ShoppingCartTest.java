package org.artrev.workshop.pbt.examples.shoppingcart;

import net.jqwik.api.*;
import net.jqwik.api.Tuple.Tuple2;
import net.jqwik.api.arbitraries.IntegerArbitrary;
import net.jqwik.api.arbitraries.StringArbitrary;
import net.jqwik.api.stateful.Action;
import net.jqwik.api.stateful.ActionSequence;
import org.artrev.workshop.pbt.examples.shoppingcart.commands.AddProductCommand;
import org.artrev.workshop.pbt.examples.shoppingcart.commands.ClearCommand;
import org.artrev.workshop.pbt.examples.shoppingcart.model.ShoppingCartModel;

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
                        addProductCommand(),
                        clearCommand()
                )
        );
    }

    private Arbitrary<Action<Tuple2<ShoppingCartModel, ShoppingCart>>> addProductCommand() {
        final StringArbitrary productNames =
                Arbitraries.strings()
                        .alpha()
                        .ofMinLength(5)
                        .ofMaxLength(50);

        final IntegerArbitrary productQuantity =
                Arbitraries.integers()
                        .between(1, 200);

        return Combinators.combine(
                productNames,
                productQuantity
        ).as((name, quantity) -> new AddProductCommand(
                new Product(
                        name,
                        Price.from(name.length()).get()
                ),
                Quantity.from(quantity).get()
        ));
    }

    private Arbitrary<Action<Tuple2<ShoppingCartModel, ShoppingCart>>> clearCommand() {
        return Arbitraries.constant(new ClearCommand());
    }
}
