package com.cg.exception;

import java.util.List;

import org.springframework.validation.FieldError;


public class ValidationException extends RuntimeException {

	private List<FieldError> errors;

	public ValidationException(String message) {
		super(message);
	}

	public ValidationException(List<FieldError> errors) {
		super();
		this.errors = errors;
	}

	public List<FieldError> getErrors() {
		return errors;
	}

}
