package ru.gribnoff.shop.controller;

import ru.gribnoff.shop.entities.Order;
import ru.gribnoff.shop.entities.bean.Cart;
import ru.gribnoff.shop.service.CartRecordService;
import ru.gribnoff.shop.service.OrderService;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class OrderController implements Serializable {
	private static final long serialVersionUID = 101241942267182071L;

	@Inject
	private OrderService orderService;
	@Inject
	private CartRecordService cartRecordService;
	@Inject
	private Cart cart;

	private Order order;
	private List<Order> orders;

	public void preloadOrders() {
		this.orders = orderService.findAll().orElse(new ArrayList<>());
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public List<Order> getAll() {
		return orders;
	}

	public String showOrder(Order order) {
		this.order = order;
		return "/order.xhmtl?faces-redirect=true";
	}

	public String proceedToCheckout() {
		this.order = orderService.saveNewOrder();
		cart.clear();
		return "/checkout.xhtml?faces-redirect=true";
	}
}
