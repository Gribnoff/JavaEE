package ru.gribnoff.shop.repository;

import ru.gribnoff.shop.entities.Role;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Stateless
public class RoleRepository implements RoleRepositoryLocal {
	private static final long serialVersionUID = 4195019051025107L;

	@PersistenceContext(unitName = "datasource")
	private transient EntityManager em;

	@Override
	public void save(Role role) {
		em.persist(role);
	}

	@Override
	public void delete(Long id) {
		Role role = em.find(Role.class, id);
		if (role != null)
			em.remove(role);
	}

	@Override
	public Optional<Role> findById(Long id) {
		return Optional.of(em.find(Role.class, id));
	}

	@Override
	public Optional<List<Role>> findAll() {
		return Optional.of(em.createQuery("from Role", Role.class).getResultList());
	}
}
