package ru.gribnoff.shop.controller;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import ru.gribnoff.shop.entities.Product;
import ru.gribnoff.shop.entities.bean.Cart;
import ru.gribnoff.shop.service.ProductService;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class ProductController implements Serializable {
	private static final long serialVersionUID = 1041241922145207L;

	@Inject
	private ProductService productService;
	@Inject
	private Cart cart;

	@Getter
	@Setter
	private transient Product product;

	private List<Product> products;

	public void preloadProducts() {
		this.products = productService.findAllByActive(true).orElse(new ArrayList<>());
	}

	public List<Product> getAll() {
		return productService.findAll().orElse(new ArrayList<>());
	}

	public List<Product> getAllActive() {
		return products;
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

	public String saveProduct() {
		productService.save(this.product);
		return "/catalog.xhtml?faces-redirect=true";
	}

	public String deleteProduct(Product product) {
		product.setActive(false); //товар может быть в оформленном заказе, поэтому не удаляется из БД, а деактивируется
		return "/catalog.xhtml?faces-redirect=true";
	}
}
