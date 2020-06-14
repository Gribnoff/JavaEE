package ru.gribnoff.shop.entities;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class CartRecord implements Serializable {
    private static final long serialVersionUID = 1905122041950251207L;

    private final long id;
    private final Product product;
    private int quantity;
    private double price;
    private Order order;

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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public CartRecord(long id, @NotNull Product product, int quantity, Order order) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.price = product.getPrice() * quantity;
        this.order = order;
    }
}
