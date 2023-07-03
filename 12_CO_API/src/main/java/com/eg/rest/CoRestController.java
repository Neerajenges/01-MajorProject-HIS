package com.eg.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eg.binding.CoResponse;
import com.eg.service.CoService;

@RestController
public class CoRestController {
	@Autowired
	private CoService service;
	
	@GetMapping("/process")
	public CoResponse processTriggers() {
		return service.processPendingTriggers();
		
	}

}
