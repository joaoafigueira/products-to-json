package br.com.joao.productsjson.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Data
public class Category {

	public Category() {
	}
	
	public Category(String categoryName) {
		this.categoryName = categoryName;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String categoryName;
	@JsonManagedReference
	@OneToMany(mappedBy = "category")
	private List<Product> products = new ArrayList<>();

	
		
}
