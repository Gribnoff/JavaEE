package ru.gribnoff.shop.entities;

import java.util.ArrayList;
import java.util.List;

public class Cart{
    private static Cart cart;

    private final List<CartRecord> cartRecords;
    private Double price;

    private Cart() {
        cartRecords = new ArrayList<>();
        recalculatePrice();
    }

    public static Cart getCart() {
        if (cart == null)
            cart = new Cart();
        return cart;
    }

    public List<CartRecord> getCartRecords() {
        return cartRecords;
    }

    public Double getPrice() {
        return price;
    }

    public void clear() {
        cartRecords.clear();
        recalculatePrice();
    }

    public void add(Product product) {
        for (CartRecord cartRecord : cartRecords) {
            if (cartRecord.getProduct().getId() == product.getId()) {
                cartRecord.setQuantity(cartRecord.getQuantity() + 1);
                cartRecord.setPrice(cartRecord.getQuantity() * cartRecord.getProduct().getPrice());
                recalculatePrice();
                return;
            }
        }
        cartRecords.add(new CartRecord(product));
        recalculatePrice();
    }

    public void removeByProductId(long productId) {
        for (int i = 0; i < cartRecords.size(); i++) {
            if (cartRecords.get(i).getProduct().getId() == productId) {
                cartRecords.remove(i);
                recalculatePrice();
                return;
            }
        }
    }

    private void recalculatePrice() {
        price = 0.;
        cartRecords.forEach(
                cartRecord -> price += cartRecord.getPrice()
        );
    }

}
