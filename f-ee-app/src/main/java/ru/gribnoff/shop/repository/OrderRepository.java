package ru.gribnoff.shop.repository;

import ru.gribnoff.shop.entities.Order;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Stateless
public class OrderRepository implements OrderRepositoryLocal {
	private static final long serialVersionUID = 1905104195025107L;

	@PersistenceContext(unitName = "datasource")
	private transient EntityManager em;

	@Override
	public void save(Order order) {
		em.persist(order);
	}

	@Override
	public void delete(Long id) {
		Order order = em.find(Order.class, id);
		if (order != null)
			em.remove(order);
	}

	@Override
	public Optional<Order> findById(Long id) {
		return Optional.of(em.find(Order.class, id));
	}

	@Override
	public Optional<List<Order>> findAll() {
		return Optional.of(em.createQuery("from Order", Order.class).getResultList());
	}
}
