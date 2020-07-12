package ru.gribnoff.shop.repository;

import ru.gribnoff.shop.entities.User;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

@Local
public interface UserRepositoryLocal extends CrudRepository<User, Long> {
	void save(User user);
	void delete(Long id);
	Optional<User> findById(Long id);
	Optional<List<User>> findAll();
}
