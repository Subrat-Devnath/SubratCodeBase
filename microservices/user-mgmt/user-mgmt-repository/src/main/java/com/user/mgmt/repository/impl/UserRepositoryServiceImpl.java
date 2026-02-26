package com.user.mgmt.repository.impl;

import java.time.LocalDateTime;
import java.util.Optional;

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
		userEntity.setActive(true);
		userEntity.setLastLoginDate(LocalDateTime.now());
		userDao.save(userEntity);
	}

	@Override
	public UserEntity getUserById(String id) {
		Optional<UserEntity> userEntity = userDao.findById(id);
		boolean present = userEntity.isPresent();
		if (!present) {
			return null;
		}
		return userEntity.get();
	}

	@Override
	public UserEntity getUserByUserName(String userName) {
		return userDao.findByName(userName);
	}

}
