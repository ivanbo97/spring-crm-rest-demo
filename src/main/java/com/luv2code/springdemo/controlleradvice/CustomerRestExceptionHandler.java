package com.luv2code.springdemo.controlleradvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.filter.HiddenHttpMethodFilter;

import com.luv2code.springdemo.exception.CustomerNotFoundException;
import com.luv2code.springdemo.response.CustomerErrorResponse;

@ControllerAdvice
public class CustomerRestExceptionHandler {

	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<CustomerErrorResponse> handleCustomerNotFound(
			CustomerNotFoundException exc){
		
		CustomerErrorResponse error = new CustomerErrorResponse();
		
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exc.getMessage());
		error.setTimestamp(System.currentTimeMillis());
		
		return new ResponseEntity<CustomerErrorResponse>(error,HttpStatus.NOT_FOUND);
	}
	
	//Will handle all exceptions which do not have defined handler
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<CustomerErrorResponse> handleBadResquests(Exception exc){
		
		CustomerErrorResponse error = new CustomerErrorResponse(HttpStatus.BAD_REQUEST.value(),
																"You have provided a bad request, please check your URL!",
																System.currentTimeMillis());
		
		
		return new ResponseEntity<CustomerErrorResponse>(error,HttpStatus.BAD_REQUEST);
	}
	
	
}
