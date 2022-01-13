package br.com.joao.productsjson.controller.dto;

import org.springframework.data.domain.Page;

import br.com.joao.productsjson.model.Category;

public class CategoryDto {

	private Long id;

	private String categoryName;

	public CategoryDto(Category category) {
		this.id = category.getId();
		this.categoryName = category.getCategoryName();
	}

	public Long getId() {
		return id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public static Page<CategoryDto> converter(Page<Category> category) {

		return category.map(CategoryDto::new);
	}

}
