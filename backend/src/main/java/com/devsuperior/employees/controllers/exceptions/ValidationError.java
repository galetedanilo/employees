package com.devsuperior.employees.controllers.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{
	
	private static final long serialVersionUID = 1L;
	
	List<FieldMessage> error = new ArrayList<>();
	
	public List<FieldMessage> getErrors() {
		return error;
	}
	
	public void addError(String fieldName, String message) {
		this.error.add(new FieldMessage(fieldName, message));
	}

}
