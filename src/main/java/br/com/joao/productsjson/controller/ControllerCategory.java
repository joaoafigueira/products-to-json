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

import br.com.joao.productsjson.controller.dto.CategoryDto;
import br.com.joao.productsjson.controller.form.CategoryForm;
import br.com.joao.productsjson.model.Category;
import br.com.joao.productsjson.repository.RepositoryCategory;

@RestController
@RequestMapping("/categories")
public class ControllerCategory {

	@Autowired
	private RepositoryCategory repositoryCategory;

	@GetMapping
	@Cacheable(value = "availableCategories")
	public Page<CategoryDto> returnsAvailableCategories(
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable pagination) {

		Page<Category> categories = repositoryCategory.findAll(pagination);

		return CategoryDto.converter(categories);
	}

	@PostMapping
	@CacheEvict(value = { "availableCategories", "availableProducts" }, allEntries = true)
	@Transactional
	public ResponseEntity<CategoryDto> registerCategories(
			@RequestBody @Valid CategoryForm categoryDataInsertedInTheRequestBody, UriComponentsBuilder uriBuilder) {

		String categoryName = categoryDataInsertedInTheRequestBody.getCategoryName();

		Category categoryNameExistsInDataBase = repositoryCategory.findByCategoryName(categoryName);

		if (categoryNameExistsInDataBase == null) {

			Category category = categoryDataInsertedInTheRequestBody.convert(categoryName);

			repositoryCategory.save(category);

			URI uri = uriBuilder.path("/categories/{id}").buildAndExpand(category.getId()).toUri();

			return ResponseEntity.created(uri).body(new CategoryDto(category));
		}
		throw new ResponseStatusException(HttpStatus.CONFLICT, "This category already exists in our database");

	}

	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = { "availableCategories", "availableProducts" }, allEntries = true)
	public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long id,
			@RequestBody @Valid CategoryForm categoryUpdated) {

		Optional<Category> categoryExist = repositoryCategory.findById(id);

		if (categoryExist.isPresent()) {

			Category category = categoryUpdated.update(id, repositoryCategory);

			return ResponseEntity.ok(new CategoryDto(category));

		}

		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This category doesnt exist in our database");
	}

	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = { "availableCategories", "availableProducts" }, allEntries = true)
	public ResponseEntity<?> removeCategory(@PathVariable Long id) {

		Optional<Category> categoryExist = repositoryCategory.findById(id);

		if (categoryExist.isPresent()) {

			repositoryCategory.deleteById(id);

			return ResponseEntity.ok().build();
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This category doesnt exist in our database");
	}
	
	
	
	
	
	

}
