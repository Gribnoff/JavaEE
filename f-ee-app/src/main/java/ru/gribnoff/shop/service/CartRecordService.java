package ru.gribnoff.shop.service;

import ru.gribnoff.shop.entities.CartRecord;
import ru.gribnoff.shop.repository.CartRecordRepositoryLocal;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.List;
import java.util.Optional;

@Stateless
public class CartRecordService implements CartRecordServiceLocal {
	private static final long serialVersionUID = 3905126204815005127L;

	@EJB
	private CartRecordRepositoryLocal cartRecordRepository;

	@Override
	@TransactionAttribute
	public void save(CartRecord cartRecord) {
		cartRecordRepository.save(cartRecord);
	}

	@Override
	@TransactionAttribute
	public void delete(Long id) {
		cartRecordRepository.delete(id);
	}

	@Override
	public Optional<CartRecord> findById(Long id) {
		return cartRecordRepository.findById(id);
	}

	@Override
	public Optional<List<CartRecord>> findAll() {
		return cartRecordRepository.findAll();
	}

	public Optional<List<CartRecord>> findAllByOrderId(Long id) {
		return cartRecordRepository.findAllByOrderId(id);
	}
}
