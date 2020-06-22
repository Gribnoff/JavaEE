package ru.gribnoff.shop.repository;

import ru.gribnoff.shop.entities.Product;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Named
@ApplicationScoped
public class ProductRepository implements CrudRepository<Product, Long> {
	private static final long serialVersionUID = 9051220415025127L;

	@PersistenceContext(unitName = "datasource")
	private transient EntityManager em;

	public void save(Product product) {
		em.persist(product);
	}

	public void delete(Long id) {
		Product product = em.find(Product.class, id);
		if (product != null)
			em.remove(product);
	}

	public Optional<Product> findById(Long id) {
		return Optional.of(em.find(Product.class, id));
	}

	public Optional<List<Product>> findAll() {
		return Optional.of(em.createQuery("from Product", Product.class).getResultList());
	}

	public Optional<List<Product>> findAllByActive(boolean active) {
		Query query = em.createQuery("select p from Product p where active = :active", Product.class).setParameter("active", active);
//		query.setParameter(0, active);
		return Optional.of(query.getResultList());
	}
}
