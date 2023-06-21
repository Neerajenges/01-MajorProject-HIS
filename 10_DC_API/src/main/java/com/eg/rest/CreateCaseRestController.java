package com.eg.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.eg.binding.CreateCaseResponse;
import com.eg.service.DcService;

@RestController
public class CreateCaseRestController {
	
	@Autowired
	private DcService service;
	
	@GetMapping("/case/{AppId}")
	public ResponseEntity<CreateCaseResponse> createCase(@PathVariable Integer AppId){
		Long caseNum = service.loadCaseNum(AppId);
		Map<Integer, String> plansMap = service.getPlanNames();
		CreateCaseResponse response=new CreateCaseResponse();
		response.setCaseNum(caseNum);
		response.setPlanNames(plansMap);
		
		return new ResponseEntity<>(response,HttpStatus.OK);
		
	}
	
	

}
