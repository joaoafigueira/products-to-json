	package br.com.joao.productsjson.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.joao.productsjson.controller.dto.ProductDto;
import br.com.joao.productsjson.controller.form.ProductForm;
import br.com.joao.productsjson.controller.form.UpdateProductForm;
import br.com.joao.productsjson.model.Category;
import br.com.joao.productsjson.model.Product;
import br.com.joao.productsjson.repository.RepositoryCategory;
import br.com.joao.productsjson.repository.RepositoryProduct;

@RestController
@RequestMapping("/products")
public class ControllerProduct {

	@Autowired
	private RepositoryCategory repositoryCategory;

	@Autowired
	private RepositoryProduct repositoryProduct;

	@GetMapping
	@Cacheable(value = "availableProducts")
	public Page<ProductDto> returnsAvailableProducts(
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable pagination) {

		Page<Product> products = repositoryProduct.findAll(pagination);

		return ProductDto.converter(products);
	}

	@PostMapping
	@Transactional
	@CacheEvict(value = { "availableCategories", "availableProducts" }, allEntries = true)
	public ResponseEntity<ProductDto> registerProducts(
			@RequestBody @Valid ProductForm productDataInsertedInTheRequestBody, UriComponentsBuilder uriBuilder) {

		String categoryName = productDataInsertedInTheRequestBody.getCategoryName();

		Category categoryExists = repositoryCategory.findByCategoryName(categoryName);

		if (categoryExists != null) {

			Product product = productDataInsertedInTheRequestBody.convert(repositoryCategory);
			repositoryProduct.save(product);

			URI uri = uriBuilder.path("/categories/{id}").buildAndExpand(product.getId()).toUri();

			return ResponseEntity.created(uri).body(new ProductDto(product));
		}

		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Filled category does not exist in our database");

	}

	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = { "availableCategories", "availableProducts" }, allEntries = true)
	public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id,
			@RequestBody @Valid UpdateProductForm productUpdated) {

		Optional<Product> productExist = repositoryProduct.findById(id);

		if (productExist.isPresent()) {

			Product product = productUpdated.update(id, repositoryProduct);

			return ResponseEntity.ok(new ProductDto(product));
		}

		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This product doesnt exist in our database");

	}

	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = { "availableCategories", "availableProducts" }, allEntries = true)
	public ResponseEntity<?> removeProduct(@PathVariable Long id) {

		Optional<Product> productIdExists = repositoryProduct.findById(id);

		if (productIdExists.isPresent()) {

			repositoryProduct.deleteById(id);
			return ResponseEntity.ok().build();
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This product doesnt exist in our database");
	}

}
