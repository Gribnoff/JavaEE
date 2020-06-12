package ru.gribnoff.shop.repository;

import ru.gribnoff.shop.config.DataSource;
import ru.gribnoff.shop.entities.CartRecord;
import ru.gribnoff.shop.entities.Product;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
public class CartRecordRepository {
	@Inject
	private DataSource dataSource;
	@Inject
	private ProductRepository productRepository;

	private Connection connection;

	@PostConstruct
	public void init() throws SQLException {
		this.connection = dataSource.getConnection();
		createTableIfNotExists(connection);
	}

	public void insert(CartRecord cartRecord) throws SQLException {
		try (PreparedStatement stmt = connection.prepareStatement(
				"insert into `java_ee_shop`.`cart_records`(`product_id`, `quantity`, `price`) " +
						"values (?, ?, ?);")) {
			stmt.setLong(1, cartRecord.getProduct().getId());
			stmt.setInt(2, cartRecord.getQuantity());
			stmt.setDouble(3, cartRecord.getPrice());
			stmt.execute();
		}
	}

	public void update(CartRecord cartRecord) throws SQLException {
		try (PreparedStatement stmt = connection.prepareStatement(
				"update `java_ee_shop`.`cart_records` " +
						"set `product_id` = ?, `quantity` = ?, `price` = ? " +
						"where `id` = ?;")) {
			stmt.setLong(1, cartRecord.getProduct().getId());
			stmt.setInt(2, cartRecord.getQuantity());
			stmt.setDouble(3, cartRecord.getPrice());
			stmt.setLong(4, cartRecord.getId());
			stmt.execute();
		}
	}

	public void delete(long id) throws SQLException {
		try (PreparedStatement stmt = connection.prepareStatement(
				"delete from `java_ee_shop`.`cart_records` " +
						"where `id` = ?;")) {
			stmt.setLong(1, id);
			stmt.execute();
		}
	}

	public CartRecord findById(long id) throws SQLException {
		try (PreparedStatement stmt = connection.prepareStatement(
				"select `id`, `product_id`, `quantity`, `price` " +
						"from `java_ee_shop`.`cart_records` " +
						"where `id` = ?")) {
			stmt.setLong(1, id);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return new CartRecord(
							productRepository.findById(rs.getLong("product_id")),
							rs.getInt("quantity"));
				}
			}
		}
		return new CartRecord(new Product(), 0);
	}

	public List<CartRecord> findAllByOrderId(long id) throws SQLException {
		List<CartRecord> result = new ArrayList<>();
		try (PreparedStatement stmt = connection.prepareStatement(
				"select `cr`.`product_id`, `cr`.`quantity`, `cr`.`price` " +
						"from `cart_records` `cr`" +
						"join `order_cart_record` `ocr` on `cr`.`id` = `ocr`.`cart_record_id`" +
						"where `ocr`.`order_id` = ?")) {
			try (ResultSet rs = stmt.executeQuery()) {
				stmt.setLong(1, id);

				while (rs.next()) {
					result.add(new CartRecord(
							productRepository.findById(rs.getLong("product_id")),
							rs.getInt("quantity")));
				}
			}
		}
		return result;
	}

	private void createTableIfNotExists(Connection conn) throws SQLException {
		try (Statement stmt = conn.createStatement()) {
			stmt.execute("create table if not exists `cart_records` (\n" +
					"\t	`id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,\n" +
					"\t	`product_id` bigint(20) unsigned NOT NULL,\n" +
					"\t	`quantity` smallint(5) unsigned NOT NULL DEFAULT '1',\n" +
					"\t	`price` double unsigned NOT NULL,\n" +
					"\t	PRIMARY KEY (`id`),\n" +
					"\t	KEY `cart_record_product_fk_idx` (`product_id`),\n" +
					"\t	CONSTRAINT `cart_record_product_fk` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`))");
		}
	}
}
