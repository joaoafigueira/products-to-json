package br.com.joao.productsjson.controller;

import javax.transaction.Transactional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.joao.productsjson.controller.dto.CategoryDto;
import br.com.joao.productsjson.controller.dto.ProductDto;
import br.com.joao.productsjson.controller.form.ProductForm;
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
	@Transactional
	@CacheEvict(value="availableCategories", allEntries = true)
	public ResponseEntity<ProductDto> registerProducts(@RequestBody ProductForm productDataInsertedInTheRequestBody){
		
		return null;
	}
	
	
	
	
	
}
