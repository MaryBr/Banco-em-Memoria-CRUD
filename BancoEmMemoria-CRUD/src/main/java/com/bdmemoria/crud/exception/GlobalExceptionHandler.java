//package com.bdmemoria.crud.exception;
//
//import java.util.Date;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.context.request.WebRequest;
//
//
//
//@ControllerAdvice
//public class GlobalExceptionHandler {
//	
//	@ExceptionHandler(ResourceNotFoundException.class)
//	public ResponseEntity<?> resourceNotFoundException (ResourceNotFoundException notFoundException, WebRequest webRequest){
//		
//		ErroDetails erroDetails = new ErroDetails (new Date(), notFoundException.getMessage(), webRequest.getDescription(false));
//		
//		return new ResponseEntity<>(erroDetails, HttpStatus.NOT_FOUND);
//	}
//	
//	@ExceptionHandler(ResourceNotFoundException.class)
//	public ResponseEntity<?> globalExceptionHandler (ResourceNotFoundException notFoundException, WebRequest webRequest){
//		
//		ErroDetails erroDetails = new ErroDetails (new Date(), notFoundException.getMessage(), webRequest.getDescription(false));
//		
//		return new ResponseEntity<>(erroDetails, HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//
//}
