package br.com.joao.productsjson.controller.dto;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import br.com.joao.productsjson.model.Category;
import br.com.joao.productsjson.model.Product;

public class ProductDto {

	private Long id;

	private String productName;

	private BigDecimal productPrice;
	
	private Category category;

	public ProductDto(Product product) {
		this.id = product.getId();
		this.productName = product.getProductName();
		this.productPrice = product.getProductPrice();
		this.category = product.getCategory();
	}

	public Long getId() {
		return id;
	}

	public String getProductName() {
		return productName;
	}

	public BigDecimal getProductPrice() {
		return productPrice;
	}

	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public static Page<ProductDto> converter(Page<Product> product) {

		return product.map(ProductDto::new);

	}

}
