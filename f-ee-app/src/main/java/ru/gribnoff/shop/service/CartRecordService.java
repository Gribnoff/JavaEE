package ru.gribnoff.shop.service;

import ru.gribnoff.shop.entities.CartRecord;
import ru.gribnoff.shop.repository.CartRecordRepository;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Named
@SessionScoped
public class CartRecordService implements CrudService<CartRecord, Long> {
	private static final long serialVersionUID = 3905126204815005127L;

	@Inject
	private CartRecordRepository cartRecordRepository;

	@Override
	@Transactional
	public void save(CartRecord cartRecord) {
		cartRecordRepository.save(cartRecord);
	}

	@Override
	@Transactional
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
