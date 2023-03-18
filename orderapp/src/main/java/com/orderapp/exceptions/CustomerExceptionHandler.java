package com.orderapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerExceptionHandler {

	@ExceptionHandler(value = EntityNotFoundException.class)
	public ResponseEntity<Object> exception(EntityNotFoundException exception) {

		ErrorResponse error = new ErrorResponse(exception.getErrorResponse().getMessage(),
				exception.getErrorResponse().getDetails(), exception.getErrorResponse().getStatus());

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = NullPointerCustomException.class)
	public ResponseEntity<Object> exception(NullPointerCustomException exception) {

		ErrorResponse error = new ErrorResponse(exception.getErrorResponse().getMessage(),
				exception.getErrorResponse().getDetails(), exception.getErrorResponse().getStatus());

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
}
