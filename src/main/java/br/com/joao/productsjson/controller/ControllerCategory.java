package br.com.joao.productsjson.controller;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.joao.productsjson.controller.dto.CategoryDto;
import br.com.joao.productsjson.controller.dto.ProductDto;
import br.com.joao.productsjson.controller.form.ProductForm;
import br.com.joao.productsjson.model.Category;
import br.com.joao.productsjson.model.Product;
import br.com.joao.productsjson.repository.RepositoryCategory;
import br.com.joao.productsjson.repository.RepositoryProduct;

@RestController
@RequestMapping("/categories")
public class ControllerCategory {

	@Autowired
	private RepositoryCategory repositoryCategory;

	@Autowired
	private RepositoryProduct repositoryProduct;

	@GetMapping
	@Cacheable(value = "availableCategories")
	public Page<CategoryDto> returnsAvailableCategories(
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable pagination) {

		Page<Category> categories = repositoryCategory.findAll(pagination);

		return CategoryDto.converter(categories);
	}

	@PostMapping("newProduct")
	@Transactional
	@CacheEvict(value = {"availableCategories", "availableProducts"}, allEntries = true)
	public ResponseEntity<ProductDto> registerProducts(@RequestBody @Valid ProductForm productDataInsertedInTheRequestBody,
			UriComponentsBuilder uriBuilder) {

		
		Product product = productDataInsertedInTheRequestBody.convert(repositoryCategory);
		repositoryProduct.save(product);

		URI uri = uriBuilder.path("/categories/{id}").buildAndExpand(product.getId()).toUri();

		return ResponseEntity.created(uri).body(new ProductDto(product));
	}

	@GetMapping("listRegisteredProducts")
	@Cacheable(value = "availableProducts")
	public Page<ProductDto> returnsAvailableProducts(
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable pagination) {

		Page<Product> products = repositoryProduct.findAll(pagination);

		return ProductDto.converter(products);
	}

	
	
	
	
	
	
}
