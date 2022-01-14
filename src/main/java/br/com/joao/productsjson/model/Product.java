package br.com.joao.productsjson.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Data
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String productName;

	private BigDecimal productPrice;

	@ManyToOne
	@JsonBackReference
	private Category category;

	public Product() {
	}

	public Product(String productName, BigDecimal productPrice, Category category) {
		this.productName = productName;
		this.productPrice = productPrice;
		this.category = category;
	}

}
