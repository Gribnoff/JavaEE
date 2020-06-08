package ru.gribnoff.shop.entities;

public class CartRecord {
    private final Product product;
    private Integer quantity;
    private Double price;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public CartRecord(Product product) {
        this.product = product;
        this.quantity = 1;
        this.price = product.getPrice();
    }
}
