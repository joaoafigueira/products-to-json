package br.com.joao.productsjson.controller.form;

import java.math.BigDecimal;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import br.com.joao.productsjson.model.Category;
import br.com.joao.productsjson.model.Product;
import br.com.joao.productsjson.repository.RepositoryCategory;

public class ProductForm {

	@NotNull @NotEmpty
	private String productName;
	
//	@NotNull @NotEmpty
	private BigDecimal productPrice;
	
	@NotNull @NotEmpty 
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

		if(category!=null) {
			
			return new Product(productName, productPrice, category);
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Filled category does not exist in our database");
		
	}
	

}
