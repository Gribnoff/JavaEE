package ru.gribnoff.shop.repository;

import ru.gribnoff.shop.entities.CartRecord;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Named
@ApplicationScoped
public class CartRecordRepository implements CrudRepository<CartRecord, Long> {
	private static final long serialVersionUID = 1051220419502520L;

	@PersistenceContext(unitName = "datasource")
	private transient EntityManager em;

	@Override
	public void save(CartRecord cartRecord) {
		em.persist(cartRecord);
	}

	@Override
	public void delete(Long id) {
		CartRecord cartRecord = em.find(CartRecord.class, id);
		if (cartRecord != null)
			em.remove(cartRecord);
	}

	@Override
	public Optional<CartRecord> findById(Long id) {
		return Optional.of(em.find(CartRecord.class, id));
	}

	@Override
	public Optional<List<CartRecord>> findAll() {
		return Optional.of(em.createQuery("from CartRecord ", CartRecord.class).getResultList());
	}

	public Optional<List<CartRecord>> findAllByOrderId(Long id) {
		Query query = em.createQuery("select p from CartRecord p where id = :id", CartRecord.class).setParameter("id", id);
//		query.setParameter(0, active);
		return Optional.of(query.getResultList());
	}
}
