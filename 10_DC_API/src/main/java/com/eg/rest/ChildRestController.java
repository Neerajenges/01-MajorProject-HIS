package com.eg.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eg.binding.ChildRequest;
import com.eg.binding.DcSummary;
import com.eg.service.DcService;

@RestController
public class ChildRestController {
	
	private DcService service ;
	
	@PostMapping("/children")
	public ResponseEntity<DcSummary> saveChilds(@RequestBody ChildRequest request){
		Long caseNum = service.saveChildren(request);
		DcSummary summary = service.getSummary(caseNum);
		
		return new ResponseEntity<>(summary,HttpStatus.OK);
		
		
	}

}
