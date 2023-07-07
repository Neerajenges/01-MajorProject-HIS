package com.eg.exception;

public class NoProductFoundException extends RuntimeException{
	
	public NoProductFoundException() {
		
	}
	public NoProductFoundException(String msg) {
		super(msg);
	}
	
	

}
