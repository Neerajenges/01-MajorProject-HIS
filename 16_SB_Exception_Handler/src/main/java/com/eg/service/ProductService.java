package com.eg.service;

import org.springframework.stereotype.Service;

import com.eg.exception.NoProductFoundException;

@Service
public class ProductService {
	
	public String getProductName(Integer pid) {
		
		if(pid==100) {
			return "Mouse";
		}else if(pid==101){
			return "keyboard";
		}else {
			throw new NoProductFoundException("Invalid product id ");
		}
		
	}

}
