package ru.gribnoff.shop.service;

import ru.gribnoff.shop.entities.User;
import ru.gribnoff.shop.repository.UserRepositoryLocal;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Stateless
public class UserService implements UserServiceLocal {
	private static final long serialVersionUID = 3905126204815005127L;

	@EJB
	private UserRepositoryLocal userRepository;

	@Override
	@TransactionAttribute
	public void save(User user) {
		userRepository.save(user);
	}

	@Override
	@TransactionAttribute
	public void delete(Long id) {
		userRepository.delete(id);
	}

	@Override
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public Optional<List<User>> findAll() {
		return userRepository.findAll();
	}

	@Override
	public void logout() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		try {
			request.logout();
		} catch (ServletException ignored) {
		}
		request.getSession().invalidate();
	}
}
