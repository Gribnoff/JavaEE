package ru.gribnoff.shop.ws;

import ru.gribnoff.shop.entities.Product;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface ProductServiceIWS {
	@WebMethod
	Product findById(Long id);
	@WebMethod
	List<Product> findAll();
}
