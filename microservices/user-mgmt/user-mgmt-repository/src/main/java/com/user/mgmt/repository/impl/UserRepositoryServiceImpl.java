package com.user.mgmt.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.mgmt.repository.UserRepository;
import com.user.mgmt.repository.dao.UserDao;
import com.user.mgmt.repository.entity.UserEntity;

@Service
public class UserRepositoryServiceImpl implements UserRepository {

	@Autowired
	private UserDao userDao;

	@Override
	public void addUser(UserEntity userEntity) {
		userDao.save(userEntity);
	}

}
