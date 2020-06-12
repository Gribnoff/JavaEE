package ru.gribnoff.shop.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.gribnoff.shop.entities.Product;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
public class ProductRepository {

	private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);

	@Inject
	private ServletContext servletContext;
	private Connection conn;

	@PostConstruct
	public void init() throws SQLException {
		String jdbcConnectionString = servletContext.getInitParameter("jdbcConnectionString");
		String username = servletContext.getInitParameter("username");
		String password = servletContext.getInitParameter("password");

		try {
			conn = DriverManager.getConnection(jdbcConnectionString, username, password);
			createTableIfNotExists(conn);
		} catch (SQLException throwables) {
			logger.error("", throwables);
			throw new RuntimeException(throwables);
		}

	}

	public void insert(Product product) throws SQLException {
		try (PreparedStatement stmt = conn.prepareStatement(
				"insert into `java_ee_shop`.`products`(`title`, `description`, `price`) values (?, ?, ?);")) {
			stmt.setString(1, product.getTitle());
			stmt.setString(2, product.getDescription());
			stmt.setDouble(3, product.getPrice());
			stmt.execute();
		}
	}

	public void update(Product product) throws SQLException {
		try (PreparedStatement stmt = conn.prepareStatement(
				"update `java_ee_shop`.`products` set `title` = ?, `description` = ?, `price` = ? where `id` = ?;")) {
			stmt.setString(1, product.getTitle());
			stmt.setString(2, product.getDescription());
			stmt.setDouble(3, product.getPrice());
			stmt.setLong(4, product.getId());
			stmt.execute();
		}
	}

	public void delete(long id) throws SQLException {
		try (PreparedStatement stmt = conn.prepareStatement(
				"delete from `java_ee_shop`.`products` where `id` = ?;")) {
			stmt.setLong(1, id);
			stmt.execute();
		}
	}

	public Product findById(long id) throws SQLException {
		try (PreparedStatement stmt = conn.prepareStatement(
				"select `id`, `title`, `description`, `price` from `java_ee_shop`.`products` where `id` = ?")) {
			stmt.setLong(1, id);
			try (ResultSet rs = stmt.executeQuery()) {

				if (rs.next()) {
					return new Product(rs.getString("title"),
							rs.getString("description"), rs.getDouble("price"));
				}
			}
		}
		return new Product("", "", 0.);
	}

	public List<Product> findAll() throws SQLException {
		List<Product> result = new ArrayList<>();
		try (Statement stmt = conn.createStatement()) {
			try (ResultSet rs = stmt.executeQuery("select `title`, `description`, `price` from products")) {

				while (rs.next()) {
					result.add(new Product(rs.getString("title"),
							rs.getString("description"), rs.getDouble("price")));
				}
			}
		}
		return result;
	}

	private void createTableIfNotExists(Connection conn) throws SQLException {
		try (Statement stmt = conn.createStatement()) {
			stmt.execute("create table if not exists `products` (\n" +
					"\t	`id` bigint(20) unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,\n" +
					"\t	`title` varchar(255) NOT NULL,\n" +
					"\t	`description` varchar(255) DEFAULT NULL,\n" +
					"\t	`price` double unsigned NOT NULL)");
		}
	}
}
