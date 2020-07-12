package ru.gribnoff.shop.repository;

import ru.gribnoff.shop.entities.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Stateless
public class UserRepository implements UserRepositoryLocal {
	private static final long serialVersionUID = 4195019051025107L;

	@PersistenceContext(unitName = "datasource")
	private transient EntityManager em;

	@Override
	public void save(User user) {
		em.persist(user);
	}

	@Override
	public void delete(Long id) {
		User user = em.find(User.class, id);
		if (user != null)
			em.remove(user);
	}

	@Override
	public Optional<User> findById(Long id) {
		return Optional.of(em.find(User.class, id));
	}

	@Override
	public Optional<List<User>> findAll() {
		return Optional.of(em.createQuery("from User", User.class).getResultList());
	}
}
