package ru.gribnoff.shop.repository;

import ru.gribnoff.shop.entities.Product;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Stateless
public class ProductRepository implements ProductRepositoryLocal {
	private static final long serialVersionUID = 9051220415025127L;

	@PersistenceContext(unitName = "datasource")
	private transient EntityManager em;

	@Override
	public void save(Product product) {
		em.persist(product);
	}

	@Override
	public void delete(Long id) {
		Product product = em.find(Product.class, id);
		if (product != null)
			em.remove(product);
	}

	@Override
	public Optional<Product> findById(Long id) {
		return Optional.of(em.find(Product.class, id));
	}

	@Override
	public Optional<List<Product>> findAll() {
		return Optional.of(em.createQuery("from Product", Product.class).getResultList());
	}

	public Optional<List<Product>> findAllByActive(boolean active) {
		Query query = em.createQuery("select p from Product p where active = :active", Product.class).setParameter("active", active);
		return Optional.of(query.getResultList());
	}
}
