package com.sp.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = UserNotFoundException.class)
	public ResponseEntity<Map<String, String>> handleUserNotFoundException(UserNotFoundException ex){
		HashMap<String, String> errorMap = new HashMap<String, String>();
		errorMap.put("error", ex.getMessage());
		return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.NOT_FOUND);		
	}
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Map<String, String>> handleGenericException(Exception ex){
		HashMap<String, String> errorMap = new HashMap<String, String>();
		errorMap.put("error", ex.getMessage());
		return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.INTERNAL_SERVER_ERROR);		
	}
}
