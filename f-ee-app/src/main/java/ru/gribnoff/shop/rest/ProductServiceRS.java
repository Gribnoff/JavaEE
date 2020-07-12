package ru.gribnoff.shop.rest;

import ru.gribnoff.shop.entities.Product;

import javax.ejb.Local;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Local
@Path("/product")
public interface ProductServiceRS {
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	void save(Product product);

	@DELETE
	@Path("/{id}/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	void delete(@PathParam("id") Long id);

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	List<Product> findAll();

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	Product findById(@PathParam("id") Long id);
}
