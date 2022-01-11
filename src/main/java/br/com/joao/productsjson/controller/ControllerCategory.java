package br.com.joao.productsjson.controller;

import javax.transaction.Transactional;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.joao.productsjson.controller.dto.CategoryDto;

@RestController
@RequestMapping("/categories")
public class ControllerCategory {
	
	
    @GetMapping
	@Cacheable
	public Page<CategoryDto> returnsAvailableProductCategory(){
    	
    	return null;
    }
	
	
    
    
    
	

}
