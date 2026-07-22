package com.example.crud.amazon.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice

public class GlobalExceptionHandler {
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorResponse> handleRuntimeException (
			RuntimeException ex,
			HttpServletRequest request
			) {
		ErrorResponse error = new ErrorResponse(
			HttpStatus.NOT_FOUND.value(),
			"Unable to give the details of Customers",
			ex.getMessage(),
			request.getRequestURI(),
			LocalDateTime.now());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException (
			Exception ex, HttpServletRequest request
			) {
		ErrorResponse error = new ErrorResponse(
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"Something went wrong",
				ex.getMessage(),
				request.getRequestURI(),
				LocalDateTime.now());
		
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
				
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrorResponse> handlerNoHandlerFound(
			NoHandlerFoundException ex, 
			HttpServletRequest request) {
		ErrorResponse error = new ErrorResponse(
				404,
				"Requested URL not found",
				"No endpoint exists for"+ ex.getRequestURL(),
				request.getRequestURI(),
				LocalDateTime.now());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
}
