package com.apexmotion.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Map<String,Object>> handleNotFound (ResourceNotFoundException ex) {
		return buildResponse(HttpStatus.NOT_FOUND,ex.getMessage());
	}
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<Map <String,Object>> handleNotFound (BadRequestException ex) {
		return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,Object>> handleNotValid (MethodArgumentNotValidException ex) {
		Map<String,String> fieldErrors = new HashMap<>();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			fieldErrors.put(error.getField(), error.getDefaultMessage());
		}
		Map<String,Object> body = new HashMap<>();
		body.put("status", 400);
		body.put("message", "Du lieu khong hop le");
		body.put("errors", fieldErrors);
		body.put("timestamp", LocalDateTime.now());
		return ResponseEntity.badRequest().body(body);
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String,Object>> handleGeneral (Exception ex) {
		return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR,"Loi he thong "  + ex.getMessage());
	}
	private ResponseEntity<Map<String,Object>> buildResponse (HttpStatus status, String message) {
		Map<String, Object> body = new HashMap<>();
		body.put("status", status.value());
		body.put("message", message);
		body.put("timestamp", LocalDateTime.now());
		return ResponseEntity.status(status).body(body);
	}
}
