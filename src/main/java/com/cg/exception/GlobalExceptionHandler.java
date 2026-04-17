package com.cg.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cg.dto.ErrorResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler
	public ResponseEntity<ErrorResponseDto> handleNotAvailable(NotAvailableException ex) {
		ErrorResponseDto error = new ErrorResponseDto();
		error.setErrormsg(ex.getMessage());
		error.setStatus(HttpStatus.NOT_FOUND.toString());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponseDto> handleAlreadyExist(AlreadyExistsException ex) {
		ErrorResponseDto error = new ErrorResponseDto();
		error.setErrormsg(ex.getMessage());
		error.setStatus(HttpStatus.CONFLICT.toString());
		
		return new ResponseEntity<>(error, HttpStatus.CONFLICT);
	}
}
