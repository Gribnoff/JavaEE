package ru.gribnoff.shop.entities;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

public class CartRecord implements Serializable {
    private static final long serialVersionUID = 1905122041950251207L;

    private final long id;
    private static final AtomicLong totalCount = new AtomicLong(0);
    private final Product product;
    private int quantity;
    private double price;

    public long getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public CartRecord(Product product) {
        this.id = totalCount.incrementAndGet();
        this.product = product;
        this.quantity = 1;
        this.price = product.getPrice();
    }

    public CartRecord(Product product, int quantity) {
        this.id = totalCount.incrementAndGet();
        this.product = product;
        this.quantity = quantity;
        this.price = product.getPrice() * quantity;
    }

    public CartRecord(long id, Product product, int quantity) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.price = product.getPrice();
    }
}
