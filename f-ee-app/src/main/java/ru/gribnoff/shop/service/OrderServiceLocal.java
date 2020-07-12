package ru.gribnoff.shop.service;

import ru.gribnoff.shop.entities.Order;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

@Local
public interface OrderServiceLocal extends CrudService<Order, Long> {
	void save(Order order);
	void delete(Long id);
	Optional<Order> findById(Long id);
	Optional<List<Order>> findAll();
	Order saveNewOrder();
}
