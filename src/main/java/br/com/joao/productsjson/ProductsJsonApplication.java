package br.com.joao.productsjson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ProductsJsonApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductsJsonApplication.class, args);
	}

}
