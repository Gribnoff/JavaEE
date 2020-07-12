package ru.gribnoff.shop.service;

import ru.gribnoff.shop.entities.Product;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

@Local
public interface ProductServiceLocal extends CrudService<Product, Long> {
	void save(Product product);
	void delete(Long id);
	Optional<Product> findById(Long id);
	Optional<List<Product>> findAll();
	Optional<List<Product>> findAllByActive(boolean active);
	void setActive(Product product, boolean active);
}
