package ru.gribnoff.shop.repository;

import ru.gribnoff.shop.entities.Order;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

@Local
public interface OrderRepositoryLocal extends CrudRepository<Order, Long> {
	void save(Order cartRecord);
	void delete(Long id);
	Optional<Order> findById(Long id);
	Optional<List<Order>> findAll();
}
