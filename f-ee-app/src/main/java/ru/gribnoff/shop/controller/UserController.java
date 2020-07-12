package ru.gribnoff.shop.controller;

import ru.gribnoff.shop.entities.Role;
import ru.gribnoff.shop.entities.User;
import ru.gribnoff.shop.service.RoleServiceLocal;
import ru.gribnoff.shop.service.UserServiceLocal;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.ServletException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SessionScoped
@Named
public class UserController implements Serializable {
	@EJB
	private UserServiceLocal userService;
	@EJB
	private RoleServiceLocal roleService;

	private User user;
	private List<Role> roles;
	private List<User> users;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUsers() {
		return users;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void preload() {
		this.users = userService.findAll().orElse(new ArrayList<>());
		this.roles = roleService.findAll().orElse(new ArrayList<>());
	}

	public String logout() throws ServletException {
		userService.logout();
		return "/home.xhtml?faces-redirect=true";
	}
}
