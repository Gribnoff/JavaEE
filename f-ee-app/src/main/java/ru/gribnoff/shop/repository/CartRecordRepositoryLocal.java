package ru.gribnoff.shop.repository;

import ru.gribnoff.shop.entities.CartRecord;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

@Local
public interface CartRecordRepositoryLocal extends CrudRepository<CartRecord, Long> {
	void save(CartRecord cartRecord);
	void delete(Long id);
	Optional<CartRecord> findById(Long id);
	Optional<List<CartRecord>> findAll();
	Optional<List<CartRecord>> findAllByOrderId(Long id);
}
