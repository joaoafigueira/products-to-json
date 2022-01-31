package br.com.joao.productsjson.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import br.com.joao.productsjson.model.Category;
import br.com.joao.productsjson.model.Product;

@DataJpaTest
@ActiveProfiles("test")
class RepositoryProductTest {

	@Autowired
	public RepositoryProduct repositoryProduct;

	@Autowired
	public RepositoryCategory repositoryCategory;

	@Test
	public void shouldNotUpdateProductIfDoesntExistInDatabase() {

		Category categoryName = new Category("CLOTHES");

		Assertions.assertNull(categoryName.getId());

		repositoryCategory.save(categoryName);

		Assertions.assertNotNull(categoryName.getId());

		Product product = new Product("tshirt", new BigDecimal(1.00), categoryName);

		repositoryProduct.save(product);
		Long productIdFromDataBase = product.getId();
		Assertions.assertNotNull(productIdFromDataBase);

		Long idThatDoesntExist = (long) 3;

		Assertions.assertNotEquals(productIdFromDataBase, idThatDoesntExist);

	}

}
