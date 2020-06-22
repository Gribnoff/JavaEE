package ru.gribnoff.shop.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.gribnoff.shop.entities.common.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Order extends BaseEntity {
	private static final long serialVersionUID = 104119221352077L;

	@OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private List<CartRecord> cartRecords;
	private double price;

	public Order(Long id, List<CartRecord> cartRecords, double price) {
		super(id);
		this.cartRecords = cartRecords;
		this.price = price;
	}
}
