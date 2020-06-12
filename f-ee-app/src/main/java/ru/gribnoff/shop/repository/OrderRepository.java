package ru.gribnoff.shop.repository;

import ru.gribnoff.shop.config.DataSource;
import ru.gribnoff.shop.entities.CartRecord;
import ru.gribnoff.shop.entities.Order;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
public class OrderRepository {
	@Inject
	private DataSource dataSource;
	@Inject
	private ProductRepository productRepository;
	@Inject
	private CartRecordRepository cartRecordRepository;

	private Connection connection;

	@PostConstruct
	public void init() throws SQLException {
		this.connection = dataSource.getConnection();
		createTableIfNotExists(connection);
	}

	public void insert(Order order) throws SQLException {
		try (
				PreparedStatement insertOrder = connection.prepareStatement(
						"insert into `java_ee_shop`.`orders`(`price`) " +
								"values (?);");
				PreparedStatement insertCartRecords = connection.prepareStatement(
						"insert into `java_ee_shop`.`order_cart_record`(`order_id`, `cart_record_id`) " +
								"values (? ,?);"
				)) {
			insertOrder.setDouble(1, order.getPrice());
			insertOrder.execute();

			for (CartRecord cartRecord : order.getCartRecords()) {
				insertCartRecords.setLong(1, order.getId());
				insertCartRecords.setLong(2, cartRecord.getId());
				insertCartRecords.execute();
			}
		}
	}

	public void update(Order order) throws SQLException {
		try (
				PreparedStatement updateOrder = connection.prepareStatement(
						"update `java_ee_shop`.`orders` " +
								"set `price` = ? " +
								"where `id` = ?;");
				PreparedStatement deleteCartRecords = connection.prepareStatement(
						"delete from `java_ee_shop`.`order_cart_record` " +
								"where `order_id` = ?");
				PreparedStatement insertCartRecords = connection.prepareStatement(
						"insert into `java_ee_shop`.`order_cart_record`(`order_id`, `cart_record_id`) " +
								"values (? ,?);")) {

			updateOrder.setDouble(1, order.getPrice());
			updateOrder.execute();

			deleteCartRecords.setLong(1, order.getId());
			deleteCartRecords.execute();

			for (CartRecord cartRecord : order.getCartRecords()) {
				insertCartRecords.setLong(1, order.getId());
				insertCartRecords.setLong(2, cartRecord.getId());
				insertCartRecords.execute();
			}
		}
	}

	public void delete(long id) throws SQLException {
		try (
				PreparedStatement deleteCardRecords = connection.prepareStatement(
				"delete from `java_ee_shop`.`order_cart_record` " +
						"where `order_id` = ?;");
				PreparedStatement deleteOrder = connection.prepareStatement(
						"delete from `java_ee_shop`.`orders` " +
								"where `id` = ?;")) {
			deleteCardRecords.setLong(1, id);
			deleteCardRecords.execute();

			deleteOrder.setLong(1, id);
			deleteOrder.execute();
		}
	}

	public Order findById(long id) throws SQLException {
		try (
				PreparedStatement findOrder = connection.prepareStatement(
				"select `id`, `price` " +
						"from `java_ee_shop`.`orders` " +
						"where `id` = ?");
		PreparedStatement findCartRecords = connection.prepareStatement(
				"select `ocr`.`order_id`, `ocr`.`cart_record_id`, `cr`.`product_id`, `cr`.`quantity` " +
						"from `order_cart_record` `ocr` " +
						"join `cart_records` `cr` on `ocr`.`cart_record_id` = `cr`.`id` " +
						"where `ocr`.`order_id` = ?;")) {
			findOrder.setLong(1, id);
			findCartRecords.setLong(1, id);

			List<CartRecord> cartRecords = new ArrayList<>();
			Order order;
			try (ResultSet rs = findCartRecords.executeQuery()) {
				while (rs.next()) {
					cartRecords.add(new CartRecord(
							productRepository.findById(rs.getLong("product_id")),
							rs.getInt("quantity")));
				}
				order = new Order(cartRecords, Order.calculatePrice(cartRecords));
			}
			return order;
		}
	}

	public List<Order> findAll() throws SQLException {
		List<Order> result = new ArrayList<>();
		try (
				Statement findOrders = connection.createStatement();
				PreparedStatement findCartRecords = connection.prepareStatement(
						"select `ocr`.`order_id`, `ocr`.`cart_record_id`, `cr`.`product_id`, `cr`.`quantity` " +
								"from `order_cart_record` `ocr` " +
								"join `cart_records` `cr` on `ocr`.`cart_record_id` = `cr`.`id` " +
								"where `ocr`.`order_id` = ?;")) {

			try (ResultSet ordersRS = findOrders.executeQuery(
					"select `id`, `price` " +
							"from `java_ee_shop`.`orders` ")) {
				while (ordersRS.next()) {
					List<CartRecord> cartRecords = new ArrayList<>();

					findCartRecords.setLong(1, ordersRS.getLong("id"));
					try (ResultSet cartRecordsRS = findCartRecords.executeQuery()) {
						while (cartRecordsRS.next()) {
							cartRecords.add(new CartRecord(
									productRepository.findById(cartRecordsRS.getLong("product_id")),
									cartRecordsRS.getInt("quantity")));
						}
						result.add(new Order(cartRecords, Order.calculatePrice(cartRecords)));
					}
				}
			}
		}
		return result;
	}

	private void createTableIfNotExists(Connection conn) throws SQLException {
		try (Statement stmt = conn.createStatement()) {
			stmt.execute("create table if not exists `orders` (\n" +
					"\t	`id` bigint(20) unsigned NOT NULL,\n" +
					"\t	`price` double unsigned NOT NULL,\n" +
					"\t	PRIMARY KEY (`id`));");
		}
		try (Statement stmt = conn.createStatement()) {
			stmt.execute("create table if not exists `orders_cart_records` (\n" +
					"\t	`order_id` bigint(20) unsigned NOT NULL,\n" +
					"\t	`cart_record_id` bigint(20) unsigned NOT NULL,\n" +
					"\t	PRIMARY KEY (`order_id`,`cart_record_id`),\n" +
					"\t KEY `cart_record_fk_idx` (`cart_record_id`),\n" +
					"\t CONSTRAINT `cart_record_fk` FOREIGN KEY (`cart_record_id`) REFERENCES `cart_records` (`id`),\n" +
					"\t CONSTRAINT `order_fk` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`));");
		}
	}
}
