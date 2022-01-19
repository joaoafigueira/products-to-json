package br.com.joao.productsjson.config.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerValidationError {

	@Autowired
	private MessageSource messageSource;
	
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<DtoFormError> handler(MethodArgumentNotValidException exception){
		
		List<DtoFormError> dtoFormError = new ArrayList<>();
		
		
		
		return dtoFormError;
		
	}
	
	
}
