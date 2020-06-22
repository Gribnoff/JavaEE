package ru.gribnoff.shop.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, ID> extends Serializable {
	void save(T object);
	void delete(ID id);
	Optional<T> findById(ID id);
	Optional<List<T>> findAll();
}
