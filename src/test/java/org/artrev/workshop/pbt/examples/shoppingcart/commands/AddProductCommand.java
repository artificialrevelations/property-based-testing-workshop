package org.artrev.workshop.pbt.examples.shoppingcart.commands;

import org.artrev.workshop.pbt.examples.shoppingcart.Command;
import org.artrev.workshop.pbt.examples.shoppingcart.Product;
import org.artrev.workshop.pbt.examples.shoppingcart.Quantity;
import org.artrev.workshop.pbt.examples.shoppingcart.ShoppingCart;
import org.artrev.workshop.pbt.examples.shoppingcart.model.ShoppingCartModel;
import org.junit.jupiter.api.Assertions;

import java.util.Objects;

public class AddProductCommand implements Command<ShoppingCartModel, ShoppingCart> {
    private final Product product;
    private final Quantity quantity;

    public AddProductCommand(final Product product,
                             final Quantity quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    @Override
    public void execute(final ShoppingCartModel model,
                        final ShoppingCart sut) {
        model.addProduct(
                product.getName(),
                product.getPrice().getValue(),
                quantity.getValue()
        );
        sut.add(product, quantity);
    }

    @Override
    public void postcondition(final ShoppingCartModel model,
                              final ShoppingCart sut) {
        Assertions.assertTrue(sut.get(product).isPresent());

        final int sutQuantity = sut.get(product).get().getValue();
        final int modelQuantity = model.getQuantity(product.getName());
        Assertions.assertEquals(
                modelQuantity,
                sutQuantity,
                "Product name = " + product.getName() + ", Model quantity = " + modelQuantity + ", SUT quantity = " + sutQuantity
        );
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;

        final AddProductCommand that = (AddProductCommand) other;

        if (!Objects.equals(product, that.product))
            return false;

        return Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        int result = product != null ? product.hashCode() : 0;
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AddProductCommand{" +
                "\n\t\tproduct=" + product +
                "\n\t\tquantity=" + quantity +
                "\n\t}";
    }
}
