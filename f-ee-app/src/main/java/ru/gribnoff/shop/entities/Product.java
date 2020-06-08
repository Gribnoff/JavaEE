package ru.gribnoff.shop.entities;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Product {
	private final long id;
	private static final AtomicLong totalCount = new AtomicLong(0);

	private String title;
	private String description;
	private double price;

	private final static List<Product> products = new ArrayList<>();

	public Product(String title, String description, double price) {
		this.id = totalCount.incrementAndGet();
		this.title = title;
		this.description = description;
		this.price = price;

		products.add(this);
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

	public static List<Product> getProducts() {
		return products;
	}

	public static Product getProductByIdFromRequest(HttpServletRequest request) {
		return Product.getProducts().get((int)Long.parseLong(request.getParameter("id")) - 1);
	}
}
