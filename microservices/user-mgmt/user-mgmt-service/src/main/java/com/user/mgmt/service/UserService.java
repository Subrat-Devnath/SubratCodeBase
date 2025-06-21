package com.user.mgmt.service;

import com.user.mgmt.service.dto.UserDto;

public interface UserService {

	void addUser(UserDto userDto);

	void sendMessageUsingWorkflow();

}
