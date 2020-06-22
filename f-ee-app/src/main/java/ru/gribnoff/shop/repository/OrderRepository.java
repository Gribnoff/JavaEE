package ru.gribnoff.shop.repository;

import ru.gribnoff.shop.entities.Order;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Named
@ApplicationScoped
public class OrderRepository implements CrudRepository<Order, Long> {
	private static final long serialVersionUID = 1905104195025107L;

	@PersistenceContext(unitName = "datasource")
	private transient EntityManager em;

	public void save(Order order) {
		em.persist(order);
	}

	public void delete(Long id) {
		Order order = em.find(Order.class, id);
		if (order != null)
			em.remove(order);
	}

	public Optional<Order> findById(Long id) {
		return Optional.of(em.find(Order.class, id));
	}

	public Optional<List<Order>> findAll() {
		return Optional.of(em.createQuery("from Order", Order.class).getResultList());
	}

	public long findMaxId() {
		return em.createQuery("select MAX(id) from Order").getFirstResult();
	}
}
