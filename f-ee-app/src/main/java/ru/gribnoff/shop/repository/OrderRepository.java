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
import java.util.Optional;

@Named
@ApplicationScoped
public class OrderRepository {
	@Inject
	private DataSource dataSource;
	@Inject
	private CartRecordRepository cartRecordRepository;

	private Connection connection;

	@PostConstruct
	public void init() throws SQLException {
		this.connection = dataSource.getConnection();
		createTableIfNotExists(connection);
	}

	public long insert(Order order) throws SQLException {
		try (
				PreparedStatement insertOrder = connection.prepareStatement(
						"insert into `java_ee_shop`.`orders`(`price`) " +
								"values (?);");
				Statement selectMaxId = connection.createStatement()) {
			insertOrder.setDouble(1, order.getPrice());
			insertOrder.execute();
			long orderId;

			try (ResultSet maxIdRS = selectMaxId.executeQuery(
					"select MAX(`id`) max " +
							"from `orders`")) {
				maxIdRS.next();
				orderId = maxIdRS.getLong("max");
			}
			return orderId;
		}
	}

	public void update(Order order) throws SQLException {
		try (
				PreparedStatement updateOrder = connection.prepareStatement(
						"update `java_ee_shop`.`orders` " +
								"set `price` = ? " +
								"where `id` = ?;")) {

			updateOrder.setDouble(1, order.getPrice());
			updateOrder.execute();
		}
	}

	public void delete(long id) throws SQLException {
		try (
				PreparedStatement deleteCardRecords = connection.prepareStatement(
						"delete from `java_ee_shop`.`cart_records` " +
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

	public Optional<Order> findById(long id) throws SQLException {
		try (
				PreparedStatement findOrder = connection.prepareStatement(
						"select `id`, `price` " +
								"from `java_ee_shop`.`orders` " +
								"where `id` = ?")) {
			findOrder.setLong(1, id);

			try (ResultSet orderRS = findOrder.executeQuery()) {
				if (orderRS.next()) {
					List<CartRecord> cartRecords = cartRecordRepository
							.findAllByOrderId(orderRS.getLong("id"))
							.orElse(new ArrayList<>());
					return Optional.of(new Order(orderRS.getLong("id"), cartRecords, orderRS.getDouble("price")));
				}
			}
		}
		return Optional.empty();
	}

	public Optional<List<Order>> findAll() throws SQLException {
		List<Order> result = new ArrayList<>();
		try (Statement findOrders = connection.createStatement();
			 ResultSet ordersRS = findOrders.executeQuery(
					 "select `id`, `price` " +
							 "from `java_ee_shop`.`orders`")) {

			while (ordersRS.next()) {
				result.add(new Order(ordersRS.getLong("id"), new ArrayList<>(), ordersRS.getDouble("price")));
			}
		}
		return Optional.of(result);
	}

	private void createTableIfNotExists(Connection conn) throws SQLException {
		try (Statement stmt = conn.createStatement()) {
			stmt.execute(
					"create table if not exists `orders` (" +
							"`id` bigint(20) unsigned NOT NULL AUTO_INCREMENT, " +
							"`price` double unsigned NOT NULL, " +
							"PRIMARY KEY (`id`));");
		}
		try (Statement stmt = conn.createStatement()) {
			stmt.execute(
					"create table if not exists `order_cart_record` (" +
							"`order_id` bigint(20) unsigned NOT NULL, " +
							"`cart_record_id` bigint(20) unsigned NOT NULL, " +
							"PRIMARY KEY (`order_id`,`cart_record_id`), " +
							"KEY `cart_record_fk_idx` (`cart_record_id`), " +
							"CONSTRAINT `cart_record_fk` FOREIGN KEY (`cart_record_id`) REFERENCES `cart_records` (`id`), " +
							"CONSTRAINT `order_fk` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`));");
		}
	}
}
