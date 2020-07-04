package ru.gribnoff.shop.ws;

import ru.gribnoff.shop.entities.Product;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface ProductServiceRemoteWS extends CrudServiceWS<Product, Long> {
	List<Product> findAll();
}
