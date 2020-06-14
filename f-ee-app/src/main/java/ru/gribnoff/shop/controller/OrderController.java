package ru.gribnoff.shop.controller;

import ru.gribnoff.shop.entities.CartRecord;
import ru.gribnoff.shop.entities.Order;
import ru.gribnoff.shop.entities.bean.Cart;
import ru.gribnoff.shop.repository.CartRecordRepository;
import ru.gribnoff.shop.repository.OrderRepository;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class OrderController implements Serializable {
	private static final long serialVersionUID = 101241942267182071L;

	@Inject
	private OrderRepository orderRepository;
	@Inject
	private CartRecordRepository cartRecordRepository;
	@Inject
	private Cart cart;

	private transient Order order;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public List<Order> getAll() throws SQLException {
		return orderRepository.findAll().orElse(new ArrayList<>());
	}

	public String showOrder(long id) throws SQLException {
		this.order = orderRepository.findById(id).get();
		return "/order.xhmtl?faces-redirect=true";
	}

	public String proceedToCheckout() throws SQLException {
		this.order = new Order(-1L, cart.getCartRecords(), cart.getPrice());
		this.order.setId(orderRepository.insert(order));

		for (CartRecord cartRecord : cart.getCartRecords()) {
			cartRecord.setOrder(this.order);
			cartRecordRepository.insert(cartRecord);
		}
		cart.clear();
		return "/checkout.xhtml?faces-redirect=true";
	}
}
