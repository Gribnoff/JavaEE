package ru.gribnoff.shop.service;

import ru.gribnoff.shop.entities.User;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

@Local
public interface UserServiceLocal extends CrudService<User, Long> {
	void save(User user);
	void delete(Long id);
	Optional<User> findById(Long id);
	Optional<List<User>> findAll();

	void logout();
}
