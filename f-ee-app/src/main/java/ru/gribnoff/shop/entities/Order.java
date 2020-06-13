package ru.gribnoff.shop.entities;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable {
	private static final long serialVersionUID = 104119221352077L;

	private final long id;

	private final double price;
	private final List<CartRecord> cartRecords;

	public long getId() {
		return id;
	}

	public double getPrice() {
		return price;
	}

	public List<CartRecord> getCartRecords() {
		return cartRecords;
	}

	public Order(long id, @NotNull List<CartRecord> cartRecords, Double price) {
		this.id = id;
		this.cartRecords = new ArrayList<>(cartRecords);
		this.price = price;
	}

	public static double calculatePrice(@NotNull List<CartRecord> cartRecords) {
		double overall = 0;
		for (CartRecord cartRecord : cartRecords) {
			overall += cartRecord.getPrice();
		}
		return overall;
	}
}
