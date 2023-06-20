package com.eg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.eg.entity.DummyData;
import com.eg.service.DummyDataService;

@RestController
public class DummyDataController {
	@Autowired
	private DummyDataService service ;
	
	@GetMapping("/getCitizen/{ssn}")
	public DummyData getCitizenBySsn(@PathVariable("ssn") Long ssn) {
		DummyData citizenBySsn = service.getCitizenBySsn(ssn);
//		return citizenBySsn.getStateName();
		return citizenBySsn;
		
	}
	
	

}
