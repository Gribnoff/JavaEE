package ru.gribnoff.shop.service;

import ru.gribnoff.shop.entities.Product;
import ru.gribnoff.shop.repository.ProductRepository;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Named
@SessionScoped
public class ProductService implements CrudService<Product, Long> {
	private static final long serialVersionUID = 590512204502517L;

	@Inject
	ProductRepository productRepository;

	@Override
	@Transactional
	public void save(Product product) {
		productRepository.save(product);
	}

	@Override
	@Transactional
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
}
