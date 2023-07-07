package com.eg.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoRestController {
	@GetMapping("/demo")
	public String getMsg() {
		String s=null;
		s.length();
		return "Hi";
	}

}
