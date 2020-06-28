package org.artrev.workshop.pbt.examples.shoppingcart.test;

import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.Combinators;
import net.jqwik.api.Tuple.Tuple2;
import net.jqwik.api.stateful.Action;
import org.artrev.workshop.pbt.examples.shoppingcart.Price;
import org.artrev.workshop.pbt.examples.shoppingcart.Product;
import org.artrev.workshop.pbt.examples.shoppingcart.Quantity;
import org.artrev.workshop.pbt.examples.shoppingcart.ShoppingCart;
import org.artrev.workshop.pbt.examples.shoppingcart.commands.*;
import org.artrev.workshop.pbt.examples.shoppingcart.model.ShoppingCartModel;

import java.util.Optional;

public final class ShoppingCartCommands {
    private static Arbitrary<Product> getProducts() {
        return Arbitraries.strings()
                .alpha()
                .ofMinLength(3)
                .ofMaxLength(10)
                .map(name -> new Product(
                        name,
                        Price.from(name.length()).get()
                ));
    }

    private static Arbitrary<Quantity> getQuantities() {
        return Arbitraries.integers()
                .between(1, 200)
                .map(Quantity::from)
                .filter(Optional::isPresent)
                .map(Optional::get);
    }

    public static Arbitrary<Action<Tuple2<ShoppingCartModel, ShoppingCart>>> getAddProductCommands() {
        return Combinators.combine(
                getProducts(),
                getQuantities()
        ).as(AddProductCommand::new);
    }

    public static Arbitrary<Action<Tuple2<ShoppingCartModel, ShoppingCart>>> getClearCommands() {
        return Arbitraries.constant(new ClearCommand());
    }

    public static Arbitrary<Action<Tuple2<ShoppingCartModel, ShoppingCart>>> getRemoveProductCommands() {
        return Combinators.combine(
                getProducts(),
                getQuantities()
        ).as(RemoveProductCommand::new);
    }

    public static Arbitrary<Action<Tuple2<ShoppingCartModel, ShoppingCart>>> getGetTotalQuantityCommands() {
        return Arbitraries.constant(new GetTotalQuantityCommand());
    }

    public static Arbitrary<Action<Tuple2<ShoppingCartModel, ShoppingCart>>> getGetTotalPriceCommands() {
        return Arbitraries.constant(new GetTotalPriceCommand());
    }

    public static Arbitrary<Action<Tuple2<ShoppingCartModel, ShoppingCart>>> getClearDiscountCommands() {
        return Arbitraries.constant(new ClearDiscountCommand());
    }
}
