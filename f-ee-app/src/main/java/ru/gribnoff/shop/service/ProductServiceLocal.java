package ru.gribnoff.shop.service;

import ru.gribnoff.shop.entities.Product;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

@Local
public interface ProductServiceLocal extends CrudService<Product, Long> {
	public void save(Product product);
	public void delete(Long id);
	public Optional<Product> findById(Long id);
	public Optional<List<Product>> findAll();
	public Optional<List<Product>> findAllByActive(boolean active);
	public void setActive(Product product, boolean active);
}
