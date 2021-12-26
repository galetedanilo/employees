package com.galetedanilo.employee.controllers.exceptions;

import com.galetedanilo.employee.services.exceptions.DatabaseException;
import com.galetedanilo.employee.services.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFoundExceptionHandler(ResourceNotFoundException ex, HttpServletRequest req) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        StandardError standardError = new StandardError();

        standardError.setTimestamp(Instant.now());
        standardError.setStatus(httpStatus.value());
        standardError.setError("Resource not found");
        standardError.setMessage(ex.getMessage());
        standardError.setPath(req.getRequestURI());

        return ResponseEntity.status(httpStatus).body(standardError);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> databaseExceptionHandler(DatabaseException ex, HttpServletRequest req) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        StandardError standardError = new StandardError();

        standardError.setTimestamp(Instant.now());
        standardError.setStatus(httpStatus.value());
        standardError.setError("Bad request");
        standardError.setMessage(ex.getMessage());
        standardError.setPath(req.getRequestURI());

        return ResponseEntity.status(httpStatus).body(standardError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> validationExceptionHandler(MethodArgumentNotValidException ex, HttpServletRequest req) {
        HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;

        ValidationError validationError = new ValidationError();

        validationError.setTimestamp(Instant.now());
        validationError.setStatus(httpStatus.value());
        validationError.setError("Validation exception");
        validationError.setMessage("Validation failed");
        validationError.setPath(req.getRequestURI());

        for(FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            validationError.addFieldMessage(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return ResponseEntity.status(httpStatus).body(validationError);
    }
}
