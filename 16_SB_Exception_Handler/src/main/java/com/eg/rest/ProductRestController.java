package com.eg.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.eg.exception.ExceptionBean;
import com.eg.exception.NoProductFoundException;
import com.eg.service.ProductService;

@RestController
public class ProductRestController {
	@Autowired
	private ProductService service ;
	
	@GetMapping("/product/{pid}")
	public String getProduct(@PathVariable Integer pid) {
		return service.getProductName(pid);
		
	}
	//Controller based exception handling ,it will not work for other controller because
//	we are writting this inside this controller 
	@ExceptionHandler(value=NoProductFoundException.class)
	public ResponseEntity<ExceptionBean> handleNoDataFoundException(NoProductFoundException ne){
		String message = ne.getMessage();
		ExceptionBean eb=new ExceptionBean();
		eb.setCode("ERRXX008080");
		eb.setMsg(message);
		return new ResponseEntity<ExceptionBean>(eb,HttpStatus.BAD_REQUEST);
		
	}

}
