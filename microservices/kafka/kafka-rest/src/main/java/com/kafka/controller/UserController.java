package com.kafka.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1", consumes = "application/json", produces = "application/json")
public class UserController {


	@GetMapping("/{name}")
	public String pringName(@PathVariable String name) {
		return name;
	}


}
