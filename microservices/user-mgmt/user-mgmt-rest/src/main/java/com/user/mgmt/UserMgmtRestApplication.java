package com.user.mgmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UserMgmtRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserMgmtRestApplication.class, args);
	}

}
