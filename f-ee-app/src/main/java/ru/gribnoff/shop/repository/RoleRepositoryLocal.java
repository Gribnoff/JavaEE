package ru.gribnoff.shop.repository;

import ru.gribnoff.shop.entities.Role;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

@Local
public interface RoleRepositoryLocal extends CrudRepository<Role, Long> {
	void save(Role role);
	void delete(Long id);
	Optional<Role> findById(Long id);
	Optional<List<Role>> findAll();
}
