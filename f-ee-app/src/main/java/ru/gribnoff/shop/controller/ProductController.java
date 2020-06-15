package ru.gribnoff.shop.controller;

import org.jetbrains.annotations.Nullable;
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

	public List<Product> getAllActive() throws SQLException {
		return productRepository.findAllByActive().orElse(new ArrayList<>());
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

	public String createOrEditProduct(@Nullable Product product) {
		this.product = product == null ? new Product() : product;
		return "/product-form.xhtml?faces-redirect";
	}

	public String saveProduct() throws SQLException {
		if (this.product.getId() == 0)
			productRepository.insert(this.product);
		else
			productRepository.update(this.product);

		return "/catalog.xhtml?faces-redirect=true";
	}

	public String deleteProduct(Product product) {
//		productRepository.delete(product.getId());
		product.setActive(false); //товар может быть в оформленном заказе, поэтому не удаляется из БД, а деактивируется
		return "/catalog.xhtml?faces-redirect=true";
	}
}
