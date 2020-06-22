package ru.gribnoff.shop.service;

import ru.gribnoff.shop.entities.CartRecord;
import ru.gribnoff.shop.entities.Order;
import ru.gribnoff.shop.entities.bean.Cart;
import ru.gribnoff.shop.repository.CartRecordRepository;
import ru.gribnoff.shop.repository.OrderRepository;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Named
@SessionScoped
public class OrderService implements CrudService<Order, Long> {
	private static final long serialVersionUID = 3905126204815005127L;

	@Inject
	private OrderRepository orderRepository;
	@Inject
	private CartRecordRepository cartRecordRepository;
	@Inject
	private Cart cart;

	@Override
	@Transactional
	public void save(Order order) {
		orderRepository.save(order);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		for (CartRecord cartRecord : cartRecordRepository.findAllByOrderId(id).orElse(new ArrayList<>())) {
			cartRecordRepository.delete(cartRecord.getId());
		}
		orderRepository.delete(id);
	}

	@Override
	public Optional<Order> findById(Long id) {
		return orderRepository.findById(id);
	}

	@Override
	public Optional<List<Order>> findAll() {
		return orderRepository.findAll();
	}

	@Transactional
	public void saveNewOrder() {
		Order order = new Order(cart.getCartRecords(), cart.getPrice());
		orderRepository.save(order);
		long id = orderRepository.findMaxId();
		order.setId(id);

		for (CartRecord cartRecord : cart.getCartRecords()) {
			cartRecord.setOrder(order);
			cartRecordRepository.save(cartRecord);
		}
	}
}
