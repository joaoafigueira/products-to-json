package br.com.joao.productsjson.controller.form;

import java.math.BigDecimal;

import br.com.joao.productsjson.model.Category;
import br.com.joao.productsjson.model.Product;
import br.com.joao.productsjson.repository.RepositoryCategory;

public class ProductForm {

	private String productName;

	private BigDecimal productPrice;

	private String categoryName;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Product convert(RepositoryCategory repositoryCategory) {

		Category category = repositoryCategory.findByCategoryName(categoryName);

		return new Product(productName, productPrice, category);

	}

}
