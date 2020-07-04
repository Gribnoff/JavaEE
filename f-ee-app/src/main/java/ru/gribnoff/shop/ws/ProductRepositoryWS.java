package ru.gribnoff.shop.ws;

import ru.gribnoff.shop.entities.Product;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class ProductRepositoryWS implements ProductRepositoryLocalWS {
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
	public Product findById(Long id) {
		return em.find(Product.class, id);
	}

	@Override
	public List<Product> findAll() {
		return em.createQuery("from Product", Product.class).getResultList();
	}

	public List<Product> findAllByActive(boolean active) {
		Query query = em.createQuery("select p from Product p where active = :active", Product.class).setParameter("active", active);
		return query.getResultList();
	}
}
