package ru.gribnoff.shop.controller;

import ru.gribnoff.shop.entities.bean.Cart;
import ru.gribnoff.shop.entities.Product;
import ru.gribnoff.shop.repository.ProductRepository;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@Named
@SessionScoped
public class ProductController implements Serializable {
	@Inject
	private ProductRepository productRepository;
	@Inject
	private Cart cart;

	private Product product;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void addProductToCart(Product product) throws SQLException {
		cart.add(product);
	}

	public List<Product> getAll() throws SQLException {
		return productRepository.findAll();
	}
}
