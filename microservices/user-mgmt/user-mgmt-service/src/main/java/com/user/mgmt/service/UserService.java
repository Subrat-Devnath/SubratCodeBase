package com.user.mgmt.service;

import com.common.service.dtos.LoginRequest;
import com.user.mgmt.client.dtos.UserDto;

public interface UserService {

	void addUser(UserDto userDto);

	void sendMessageUsingWorkflow();

	UserDto getUserById(String id);

	UserDto getUserByUserName(String userName);

	UserDto validateUserAndGet(LoginRequest uerDetails);

}
