package com.user.mgmt.client.dtos;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserDto {

	private String id;

	private String name;

	private String email;

	private String city;

	private String country;

	private String password;

	private String passwordSecrest;

	private boolean isActive;

	private int retryCount;

	private LocalDateTime lastLoginDate;

}
