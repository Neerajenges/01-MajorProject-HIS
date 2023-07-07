package com.eg.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eg.binding.CoResponse;
import com.eg.service.BiService;

@RestController
public class CoRestController {
	@Autowired
	private BiService service;
	
	@GetMapping("/createCsv")
	public void createCsv() {
		service.createCsv();
		
	}

}
