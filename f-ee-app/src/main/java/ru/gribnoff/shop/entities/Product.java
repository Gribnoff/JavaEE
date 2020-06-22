package ru.gribnoff.shop.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.gribnoff.shop.entities.common.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "products")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity {
	private static final long serialVersionUID = 10419221207L;

	private String title;
	private String description;
	private double price;

	private boolean active;
}
