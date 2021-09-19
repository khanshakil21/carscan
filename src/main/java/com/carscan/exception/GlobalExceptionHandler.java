package com.carscan.exception;

import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.log4j.Log4j2;

@ControllerAdvice
@Log4j2
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request,
			HttpServletResponse response) {
		log.info("Converting ResourceNotFoundException to RestResponse : " + ex.getMessage());

		ErrorBody body = new ErrorBody("ERR-100", ex.getMessage());
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "problem+json"));
		return handleExceptionInternal(ex, body, headers, HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleException(Exception ex, WebRequest request, HttpServletResponse response) {
		log.info("Converting Exception to RestResponse : " + ex.getMessage());
		ex.printStackTrace();
		ErrorBody body = new ErrorBody("ERR-300", "Processing Error");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "problem+json"));
		return handleExceptionInternal(ex, body, headers, HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ex.printStackTrace();
		String message=	ex.getBindingResult().getAllErrors()
											.stream().map(err->err.getDefaultMessage())
											.collect(Collectors.joining(","));

		ErrorBody body = new ErrorBody("ERR-300", message);
		return handleExceptionInternal(ex, body, headers, HttpStatus.BAD_REQUEST, request);
	}
}