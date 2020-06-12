package ru.gribnoff.shop.entities;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

public class Product implements Serializable {
	private static final long serialVersionUID = 10419221207L;

	private final long id;
	private static final AtomicLong totalCount = new AtomicLong(0);

	private String title;
	private String description;
	private double price;

	public Product() {
		this.id = totalCount.incrementAndGet();
	}

	public Product(String title, String description, double price) {
		this.id = totalCount.incrementAndGet();
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
