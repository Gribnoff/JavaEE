package ru.gribnoff.shop.service;

import ru.gribnoff.shop.entities.CartRecord;
import ru.gribnoff.shop.entities.Order;
import ru.gribnoff.shop.entities.bean.Cart;
import ru.gribnoff.shop.repository.CartRecordRepositoryLocal;
import ru.gribnoff.shop.repository.OrderRepositoryLocal;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Stateless
public class OrderService implements OrderServiceLocal {
	private static final long serialVersionUID = 3905126204815005127L;

	@EJB
	private OrderRepositoryLocal orderRepository;
	@EJB
	private CartRecordRepositoryLocal cartRecordRepository;
	@EJB
	private Cart cart;

	@Override
	@TransactionAttribute
	public void save(Order order) {
		orderRepository.save(order);
	}

	@Override
	@TransactionAttribute
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

	@TransactionAttribute
	public Order saveNewOrder() {
		Order order = new Order(cart.getCartRecords(), cart.getPrice());
		orderRepository.save(order);

		for (CartRecord cartRecord : cart.getCartRecords()) {
			cartRecord.setOrder(order);
			cartRecordRepository.save(cartRecord);
		}

		return order;
	}
}
