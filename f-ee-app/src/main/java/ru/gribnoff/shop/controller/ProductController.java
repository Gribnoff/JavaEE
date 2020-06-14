package ru.gribnoff.shop.controller;

import ru.gribnoff.shop.entities.bean.Cart;
import ru.gribnoff.shop.entities.Product;
import ru.gribnoff.shop.repository.ProductRepository;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class ProductController implements Serializable {
	private static final long serialVersionUID = 1041241922145207L;

	@Inject
	private ProductRepository productRepository;
	@Inject
	private Cart cart;

	private transient Product product;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<Product> getAll() throws SQLException {
		return productRepository.findAll().orElse(new ArrayList<>());
	}

	public String showProduct(Product product) {
		this.product = product;
		return "/product.xhmtl?faces-redirect=true";
	}

	public void addProductToCart(Product product) {
		cart.add(product);
	}

	public void removeProductFromCart(Product product) {
		cart.remove(product.getId());
	}
}
