package ru.gribnoff.shop.entities;

import java.io.Serializable;

public class Product implements Serializable {
	private static final long serialVersionUID = 10419221207L;

	private long id;

	private String title;
	private String description;
	private double price;

	private boolean active;

	public Product() {}

	public Product(long id, String title, String description, double price, boolean active) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.price = price;
		this.active = active;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
