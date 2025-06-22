package com.user.mgmt.service;

import com.user.mgmt.repository.dto.UserDto;

public interface UserService {

	void addUser(UserDto userDto);

	void sendMessageUsingWorkflow();

	UserDto getUserById(Long id);

}
