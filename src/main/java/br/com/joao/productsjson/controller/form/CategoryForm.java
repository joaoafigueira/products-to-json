package br.com.joao.productsjson.controller.form;

import javax.validation.constraints.NotEmpty;
import br.com.joao.productsjson.model.Category;
import br.com.joao.productsjson.repository.RepositoryCategory;

public class CategoryForm {

	@NotEmpty
	private String categoryName;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Category convert(String categoryName) {

		return new Category(categoryName);
	}

	public Category update(Long id, RepositoryCategory repositoryCategory) {

		Category category = repositoryCategory.getOne(id);

		category.setCategoryName(this.categoryName);

		return category;
	}

}
