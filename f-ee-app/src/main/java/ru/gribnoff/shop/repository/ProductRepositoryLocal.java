package ru.gribnoff.shop.repository;

import ru.gribnoff.shop.entities.Product;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

@Local
public interface ProductRepositoryLocal extends CrudRepository<Product, Long> {
	void save(Product cartRecord);
	void delete(Long id);
	Optional<Product> findById(Long id);
	Optional<List<Product>> findAll();
	Optional<List<Product>> findAllByActive(boolean active);
}
