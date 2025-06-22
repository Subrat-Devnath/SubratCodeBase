package com.user.mgmt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.service.configuration.ObjectBuilderUtils;
import com.user.mgmt.repository.UserRepository;
import com.user.mgmt.repository.dto.UserDto;
import com.user.mgmt.repository.entity.UserEntity;
import com.user.mgmt.service.UserService;
import com.workflow.client.WorkflowClient;
import com.workflow.client.constants.WorkflowProcessDefinationKeys;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private WorkflowClient workflowClient;

	@Override
	public UserDto getUserById(Long id) {
		return userRepository.getUserById(id);
	}

	@Override
	public void addUser(UserDto userDto) {
		UserEntity userEntity = ObjectBuilderUtils.buildDtoToEntity(userDto, UserEntity.class);
		userRepository.addUser(userEntity);
	}

	@Override
	public void sendMessageUsingWorkflow() {
		workflowClient.printHellowWorld(WorkflowProcessDefinationKeys.WORKFLOW_HELLO);
	}

}
