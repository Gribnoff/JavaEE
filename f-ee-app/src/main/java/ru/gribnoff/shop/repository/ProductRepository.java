package ru.gribnoff.shop.repository;

import ru.gribnoff.shop.config.DataSource;
import ru.gribnoff.shop.entities.Product;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Named
@ApplicationScoped
public class ProductRepository {
	@Inject
	private DataSource dataSource;

	private Connection connection;

	@PostConstruct
	public void init() throws SQLException {
		this.connection = dataSource.getConnection();
		createTableIfNotExists(connection);
	}

	public void insert(Product product) throws SQLException {
		try (PreparedStatement stmt = connection.prepareStatement(
				"insert into `java_ee_shop`.`products`(`title`, `description`, `price`, `active`) " +
						"values (?, ?, ?, ?);")) {
			stmt.setString(1, product.getTitle());
			stmt.setString(2, product.getDescription());
			stmt.setDouble(3, product.getPrice());
			stmt.setBoolean(4, product.isActive());
			stmt.execute();
		}
	}

	public void update(Product product) throws SQLException {
		try (PreparedStatement stmt = connection.prepareStatement(
				"update `java_ee_shop`.`products` " +
						"set `title` = ?, `description` = ?, `price` = ?, `active` = ? " +
						"where `id` = ?;")) {
			stmt.setString(1, product.getTitle());
			stmt.setString(2, product.getDescription());
			stmt.setDouble(3, product.getPrice());
			stmt.setBoolean(4, product.isActive());
			stmt.setLong(5, product.getId());
			stmt.execute();
		}
	}

	public void delete(long id) throws SQLException {
		try (PreparedStatement stmt = connection.prepareStatement(
				"delete from `java_ee_shop`.`products` " +
						"where `id` = ?;")) {
			stmt.setLong(1, id);
			stmt.execute();
		}
	}

	public Optional<Product> findById(long id) throws SQLException {
		try (PreparedStatement stmt = connection.prepareStatement(
				"select `id`, `title`, `description`, `price`, `active` " +
						"from `java_ee_shop`.`products` " +
						"where `id` = ?")) {
			stmt.setLong(1, id);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return Optional.of(new Product(rs.getLong("id"), rs.getString("title"),
							rs.getString("description"), rs.getDouble("price"),
							rs.getBoolean("active")));
				}
			}
		}
		return Optional.empty();
	}

	public Optional<List<Product>> findAll() throws SQLException {
		List<Product> result = new ArrayList<>();
		try (Statement stmt = connection.createStatement()) {
			try (ResultSet rs = stmt.executeQuery(
					"select `id`, `title`, `description`, `price` " +
							"from `products`")) {
				while (rs.next()) {
					result.add(new Product(rs.getLong("id"), rs.getString("title"),
							rs.getString("description"), rs.getDouble("price"),
							rs.getBoolean("active")));
				}
			}
		}
		return Optional.of(result);
	}

	public Optional<List<Product>> findAllByActive() throws SQLException {
		List<Product> result = new ArrayList<>();
		try (Statement stmt = connection.createStatement()) {
			try (ResultSet rs = stmt.executeQuery(
					"select `id`, `title`, `description`, `price`, `active` " +
							"from `products`" +
							"where `active` = true")) {
				while (rs.next()) {
					result.add(new Product(rs.getLong("id"), rs.getString("title"),
							rs.getString("description"), rs.getDouble("price"),
							rs.getBoolean("active")));
				}
			}
		}
		return Optional.of(result);
	}

	private void createTableIfNotExists(Connection conn) throws SQLException {
		try (Statement stmt = conn.createStatement()) {
			stmt.execute("create table if not exists `products` (\n" +
					"\t	`id` bigint(20) unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,\n" +
					"\t	`title` varchar(255) NOT NULL,\n" +
					"\t	`description` varchar(255) DEFAULT NULL,\n" +
					"\t	`price` double unsigned NOT NULL,\n" +
					"\t `active` boolean not null default true)");
		}
	}
}
