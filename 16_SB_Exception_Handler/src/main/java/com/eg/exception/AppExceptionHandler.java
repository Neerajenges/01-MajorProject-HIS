package com.eg.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
//global exception ,it will be applied for all the classes available in the project 
@RestControllerAdvice
public class AppExceptionHandler {
	
	
	@ExceptionHandler(value=NoProductFoundException.class)
	public ResponseEntity<ExceptionBean> handleNoDataFoundException(NoProductFoundException ne){
		String message = ne.getMessage();
		ExceptionBean eb=new ExceptionBean();
		eb.setCode("ERRXX008080");
		eb.setMsg(message);
		return new ResponseEntity<ExceptionBean>(eb,HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(value=NullPointerException.class)
	public ResponseEntity<ExceptionBean> handleNPE(NullPointerException ne){
		String message = ne.getMessage();
		ExceptionBean eb=new ExceptionBean();
		eb.setCode("ERRXX008080");
		eb.setMsg(message);
		return new ResponseEntity<ExceptionBean>(eb,HttpStatus.BAD_REQUEST);
		
	}
	

}
