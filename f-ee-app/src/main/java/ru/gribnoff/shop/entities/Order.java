package ru.gribnoff.shop.entities;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Order{
	private final long id;
	private static final AtomicLong totalCount = new AtomicLong(0);

	private final Double price;
	private final List<CartRecord> cartRecords;

	private static List<Order> orders;

	public long getId() {
		return id;
	}

	public Double getPrice() {
		return price;
	}

	public List<CartRecord> getCartRecords() {
		return cartRecords;
	}

	public static List<Order> getOrders() {
		return orders;
	}

	public Order(List<CartRecord> cartRecords, Double price) {
		this.id = totalCount.incrementAndGet();
		this.cartRecords = new ArrayList<>(cartRecords);
		this.price = price;

		if (orders == null)
			orders = new ArrayList<>();
		orders.add(this);
	}

	public static Order getOrderByIdFromRequest(HttpServletRequest request) {
		return Order.getOrders().get((int)Long.parseLong(request.getParameter("id")) - 1);
	}
}
