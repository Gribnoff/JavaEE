package ru.gribnoff.shop.service;

import ru.gribnoff.shop.entities.Order;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

@Local
public interface OrderServiceLocal extends CrudService<Order, Long> {
	public void save(Order order);
	public void delete(Long id);
	public Optional<Order> findById(Long id);
	public Optional<List<Order>> findAll();
	public Order saveNewOrder();
}
