package ru.gribnoff.shop.service;

import ru.gribnoff.shop.entities.Role;
import ru.gribnoff.shop.repository.RoleRepositoryLocal;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.List;
import java.util.Optional;

@Stateless
public class RoleService implements RoleServiceLocal {
	private static final long serialVersionUID = 3905126204815005127L;

	@EJB
	private RoleRepositoryLocal roleRepository;

	@Override
	@TransactionAttribute
	public void save(Role role) {
		roleRepository.save(role);
	}

	@Override
	@TransactionAttribute
	public void delete(Long id) {
		roleRepository.delete(id);
	}

	@Override
	public Optional<Role> findById(Long id) {
		return roleRepository.findById(id);
	}

	@Override
	public Optional<List<Role>> findAll() {
		return roleRepository.findAll();
	}
}
