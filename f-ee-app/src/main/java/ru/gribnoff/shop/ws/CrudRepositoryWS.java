package ru.gribnoff.shop.ws;

import java.io.Serializable;
import java.util.List;

public interface CrudRepositoryWS<T, ID> extends Serializable {
	void save(T object);
	void delete(ID id);
	T findById(ID id);
	List<T> findAll();
}
