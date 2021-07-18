package com.originalroutes.crm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testes")
public class RequestController {
	
	@GetMapping
	public String testes() {
		return "Testes OK!";
	}

}


