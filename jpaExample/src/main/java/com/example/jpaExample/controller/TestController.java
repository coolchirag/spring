package com.example.jpaExample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpaExample.service.CompanyService;

@RestController
public class TestController {

	@Autowired
	private CompanyService cmpService;
	@GetMapping("/test")
	public ResponseEntity<String> testData()
	{
		String data = cmpService.insertCompany();
		return new ResponseEntity<String>(data, HttpStatus.OK);
	}
}
