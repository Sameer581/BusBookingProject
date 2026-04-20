package com.cg.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponseDto handleValidation(ValidationException ex) {
		Map<String, List<String>> errMap = new HashMap<>();

		List<FieldError> err = ex.getErrors();

		for (FieldError fr : err) {
			if (errMap.containsKey(fr.getField())) {

				List<String> lst = errMap.get(fr.getField());
				lst.add(fr.getDefaultMessage());
				errMap.put(fr.getField(), lst);
			}

			else {
				List<String> lst = new ArrayList<>();
				lst.add(fr.getDefaultMessage());
				errMap.put(fr.getField(), lst);

			}
		}

		ErrorResponseDto dto = new ErrorResponseDto();
		dto.setErrormsg("validation failed");
		dto.setErrMap(errMap);
		dto.setTimeStamp(LocalDateTime.now());
		dto.setStatus(HttpStatus.NOT_FOUND.toString());
		return dto;
	}
}
