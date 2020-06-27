package org.artrev.workshop.pbt.examples.shoppingcart.model;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCartModel {
    private Map<String, Integer> productQuantityMap = new HashMap<>();
    private Map<String, Integer> productPriceMap = new HashMap<>();
    // ...

    public void addProduct(String product, int quantity, int price) {
        final int newQuantity;
        if (productQuantityMap.containsKey(product)) {
            final int oldQuantity = productQuantityMap.get(product);
            newQuantity = oldQuantity + quantity;
        } else {
            newQuantity = 0;
        }
        productQuantityMap.put(product, newQuantity);
        productPriceMap.put(product, price);
    }

    public int getQuantity(String product) {
        if (productQuantityMap.containsKey(product)) {
            return productQuantityMap.get(product);
        }
        return 0;
    }

    public int getTotalQuantity() {
        int total = 0;
        for (final Map.Entry<String, Integer> product : productQuantityMap.entrySet()) {
            total += product.getValue();
        }
        return total;
    }

    public void clear() {
        productPriceMap.clear();
        productQuantityMap.clear();
    }
}
