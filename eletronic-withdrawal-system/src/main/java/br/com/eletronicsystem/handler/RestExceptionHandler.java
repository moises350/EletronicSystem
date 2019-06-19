package br.com.eletronicsystem.handler;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.eletronicsystem.error.ResourceNotFoundDetails;
import br.com.eletronicsystem.error.ResourceNotFoundException;
import br.com.eletronicsystem.error.ValidationErrorDetails;

@ControllerAdvice
public class RestExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rfnException)
	{
		ResourceNotFoundDetails rfn = ResourceNotFoundDetails.builder()
			.withTimestamp(new Date().getTime())
			.withStatus(HttpStatus.NOT_FOUND.value())
			.withTitle("Resource Not Found")
			.withDetail(rfnException.getMessage())
			.withDeveloperMessage(rfnException.getClass().getName())
			.build();
		
		return new ResponseEntity<Object> (rfn,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException manvf)
	{
		List<FieldError> fieldErrors = manvf.getBindingResult().getFieldErrors();
		
		String fields = fieldErrors.stream().map(p -> p.getField()).collect(Collectors.joining(","));
		String fieldMessages = fieldErrors.stream().map(p -> p.getDefaultMessage()).collect(Collectors.joining(","));
		
		ValidationErrorDetails rfn = ValidationErrorDetails.builder()
			.withTimestamp(new Date().getTime())
			.withStatus(HttpStatus.BAD_REQUEST.value())
			.withTitle("Field Validation Error")
			.withDetail("Field Validation Error")
			.withDeveloperMessage(manvf.getClass().getName())
			.withField(fields)
			.withFieldMessage(fieldMessages)
			.build();
		
		return new ResponseEntity<Object> (rfn,HttpStatus.BAD_REQUEST);
	}
}
