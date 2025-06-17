package com.user.mgmt.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.mgmt.repository.entity.UserEntity;

@Repository
public interface UserDao extends JpaRepository<UserEntity, Long> {

}
