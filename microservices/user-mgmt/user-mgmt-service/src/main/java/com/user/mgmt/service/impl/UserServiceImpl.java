package com.user.mgmt.service.impl;

import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.common.service.configuration.ObjectBuilder;
import com.common.service.dtos.LoginRequest;
import com.user.mgmt.client.dtos.UserDto;
import com.user.mgmt.repository.UserRepository;
import com.user.mgmt.repository.entity.UserEntity;
import com.user.mgmt.service.UserService;
import com.user.mgmt.service.utils.PasswordEncryptor;
import com.workflow.client.WorkflowClient;
import com.workflow.client.constants.WorkflowProcessDefinationKeys;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private WorkflowClient workflowClient;

	@Autowired
	private PasswordEncryptor passwordEncryptor;

	@Override
	public void addUser(UserDto userDto) {

		try {
			String userSalt = UUID.randomUUID().toString();
			userDto.setPasswordSecrest(userSalt);

			String encryptedPassword = passwordEncryptor.encrypt(userDto.getPassword(), userSalt);
			userDto.setPassword(encryptedPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}

		UserEntity userEntity = ObjectBuilder.buildDtoFromEntity(userDto, null, UserEntity.class);
		userRepository.addUser(userEntity);
	}

	@Override
	public UserDto getUserById(String id) {
		UserEntity userById = userRepository.getUserById(id);
		if (userById == null) {
			return null;
		}
		return ObjectBuilder.buildDtoFromEntity(userById, null, UserDto.class);
	}

	@Override
	public UserDto getUserByUserName(String userName) {

		if (StringUtils.isEmpty(userName)) {
			return null;
		}

		UserEntity userByUserName = userRepository.getUserByUserName(userName);

		if (userByUserName == null) {
			return null;
		}
		return ObjectBuilder.buildDtoFromEntity(userByUserName, null, UserDto.class);
	}

	@Override
	public UserDto validateUserAndGet(LoginRequest loginRequest) {
		UserDto userByUserName = getUserByUserName(loginRequest.getUserName());

		try {
			String encrypt = passwordEncryptor.encrypt(loginRequest.getPassword(), userByUserName.getPasswordSecrest());

			if (userByUserName.getPassword().equals(encrypt)) {
				return userByUserName;
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void sendMessageUsingWorkflow() {
		workflowClient.printHellowWorld(WorkflowProcessDefinationKeys.WORKFLOW_HELLO);
	}

}
