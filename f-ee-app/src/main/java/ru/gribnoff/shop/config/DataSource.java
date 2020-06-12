package ru.gribnoff.shop.config;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Named
@ApplicationScoped
public class DataSource {
	@Inject
	private ServletContext servletContext;
	private Connection connection;

	public Connection getConnection() {
		return connection;
	}

	@PostConstruct
	public void init(){
		String jdbcConnectionString = servletContext.getInitParameter("jdbcConnectionString");
		String username = servletContext.getInitParameter("username");
		String password = servletContext.getInitParameter("password");

		try {
			connection = DriverManager.getConnection(jdbcConnectionString, username, password);
		} catch (SQLException throwables) {
			throw new NullPointerException("Couldn't start connection");
		}

	}
}
