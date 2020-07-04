package ru.gribnoff.shop.ws;

import ru.gribnoff.shop.entities.Product;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.jws.WebService;
import java.util.List;

@Stateless
@WebService(endpointInterface = "ru.gribnoff.shop.ws.ProductServiceIWS", serviceName = "ProductService")
public class ProductServiceWS implements ProductServiceLocalWS, ProductServiceRemoteWS, ProductServiceIWS {
	private static final long serialVersionUID = 590512204502517L;

	@EJB
	private ProductRepositoryLocalWS productRepository;

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
	public Product findById(Long id) {
		return productRepository.findById(id);
	}

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	public List<Product> findAllByActive(boolean active) {
		return productRepository.findAllByActive(active);
	}

	@TransactionAttribute
	public void setActive(Product product, boolean active) {
		product.setActive(active);
		productRepository.save(product);
	}
}
