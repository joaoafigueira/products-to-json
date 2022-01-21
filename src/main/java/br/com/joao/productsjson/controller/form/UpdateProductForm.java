package br.com.joao.productsjson.controller.form;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import br.com.joao.productsjson.model.Category;
import br.com.joao.productsjson.model.Product;
import br.com.joao.productsjson.repository.RepositoryCategory;
import br.com.joao.productsjson.repository.RepositoryProduct;

public class UpdateProductForm {

	private String productName;

	@NotNull
	@PositiveOrZero
	private BigDecimal productPrice;

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

	public Product update(Long id, RepositoryProduct repositoryProduct) {

		Product product = repositoryProduct.getOne(id);

		product.setProductName(this.productName);
		product.setProductPrice(this.productPrice);

		return product;
	}

}
