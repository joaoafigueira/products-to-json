package br.com.joao.productsjson.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.joao.productsjson.model.Product;


public interface RepositoryProduct extends JpaRepository<Product,Long> {

}
