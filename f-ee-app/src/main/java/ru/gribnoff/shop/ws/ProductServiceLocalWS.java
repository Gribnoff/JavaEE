package ru.gribnoff.shop.ws;

import ru.gribnoff.shop.entities.Product;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ProductServiceLocalWS extends CrudServiceWS<Product, Long> {
	void save(Product product);
	void delete(Long id);
	Product findById(Long id);
	List<Product> findAll();
	List<Product> findAllByActive(boolean active);
	void setActive(Product product, boolean active);
}
