package com.user.mgmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.mgmt.service.UserService;
import com.user.mgmt.service.dto.UserDto;

@RestController
@RequestMapping(path = "/api/v1", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/{name}")
	public String pringName(@PathVariable String name) {
		return name;
	}

	@PostMapping("/add")
	public boolean addUser(@RequestBody UserDto userDto) {
		userService.addUser(userDto);
		return true;
	}

	@GetMapping("/send-message")
	public void sendMessageUsingWorkflow() {
		userService.sendMessageUsingWorkflow();
	}

}
