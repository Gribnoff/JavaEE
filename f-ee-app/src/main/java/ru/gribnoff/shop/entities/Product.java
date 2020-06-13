package ru.gribnoff.shop.entities;

import java.io.Serializable;

public class Product implements Serializable {
	private static final long serialVersionUID = 10419221207L;

	private final long id;

	private String title;
	private String description;
	private double price;

	public Product(long id, String title, String description, double price) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.price = price;
	}

	public long getId() {
		return id;
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

	public void setPrice(long price) {
		this.price = price;
	}
}
