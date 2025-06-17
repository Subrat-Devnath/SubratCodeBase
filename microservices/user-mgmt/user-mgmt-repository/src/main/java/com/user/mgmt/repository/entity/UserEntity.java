package com.user.mgmt.repository.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "user")
public class UserEntity implements Serializable {

	private static final long serialVersionUID = -5649645038689214691L;

	@Id
	private Long id;

	private String name;

	private String city;

	private String email;

	private String country;

	private String passWord;

}
