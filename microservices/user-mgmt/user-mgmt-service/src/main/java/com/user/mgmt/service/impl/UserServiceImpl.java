package com.user.mgmt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.mgmt.repository.UserRepository;
import com.user.mgmt.repository.entity.UserEntity;
import com.user.mgmt.service.UserService;
import com.user.mgmt.service.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void addUser(UserDto userDto) {
		ObjectMapper objectMapper = new ObjectMapper();
		UserEntity userEntity = objectMapper.convertValue(userDto, UserEntity.class);
		userRepository.addUser(userEntity);
	}

}
