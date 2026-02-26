package com.user.mgmt.repository.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class UserEntity implements Serializable {

	private static final long serialVersionUID = -5649645038689214691L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "id", nullable = false)
	private String id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "city")
	private String city;

	@Column(name = "country")
	private String country;

	@Column(name = "password")
	private String password;

	@Column(name = "password_secret")
	private String passwordSecrest;

	@Column(name = "is_active")
	private boolean isActive;

	@Column(name = "retry_count")
	private int retryCount;

	@Column(name = "last_login_date")
	private LocalDateTime lastLoginDate;
}
