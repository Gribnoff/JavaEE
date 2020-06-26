package ru.gribnoff.shop.service;

import ru.gribnoff.shop.entities.Product;
import ru.gribnoff.shop.repository.ProductRepositoryLocal;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.List;
import java.util.Optional;

@Stateless
public class ProductService implements ProductServiceLocal {
	private static final long serialVersionUID = 590512204502517L;

	@EJB
	private ProductRepositoryLocal productRepository;

	@Override
	@TransactionAttribute
	public void save(Product product) {
		productRepository.save(product);
	}

	@Override
	@TransactionAttribute
	public void delete(Long id) {
		productRepository.delete(id);
	}

	@Override
	public Optional<Product> findById(Long id) {
		return productRepository.findById(id);
	}

	@Override
	public Optional<List<Product>> findAll() {
		return productRepository.findAll();
	}

	public Optional<List<Product>> findAllByActive(boolean active) {
		return productRepository.findAllByActive(active);
	}

	@TransactionAttribute
	public void setActive(Product product, boolean active) {
		product.setActive(active);
		productRepository.save(product);
	}
}
