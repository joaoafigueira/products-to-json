package br.com.joao.productsjson.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.joao.productsjson.model.Category;

public interface RepositoryCategory extends JpaRepository<Category, Long>{


	Category findByCategoryName(String categoryName);

}
