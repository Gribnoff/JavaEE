package ru.gribnoff.shop.entities.bean;

import ru.gribnoff.shop.entities.CartRecord;
import ru.gribnoff.shop.entities.Product;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Stateful
public class Cart implements Serializable {
    private static final long serialVersionUID = 1204905195025121207L;

    private static Cart cart;
    private List<CartRecord> cartRecords;
    private double price;

    @PostConstruct
    public void init() {
        cartRecords = new ArrayList<>();
        recalculatePrice();
    }

    public List<CartRecord> getCartRecords() {
        return cartRecords;
    }

    public boolean isEmpty() {
        return cartRecords.isEmpty();
    }

    public double getPrice() {
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
        cartRecords.add(new CartRecord(product, 1, product.getPrice(), null));
        recalculatePrice();
    }

    public void remove(long productId) {
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
