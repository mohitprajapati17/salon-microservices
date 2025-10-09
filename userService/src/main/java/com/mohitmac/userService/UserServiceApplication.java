package com.mohitmac.userService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.mohitmac.userService", "com/mohitmac/userService/controller", "com/mohitmac/userService/exception", "com/mohitmac/userService/config"})
@EnableJpaRepositories(basePackages = {"com/mohitmac/userService/repository"})
@EntityScan(basePackages = {"com/mohitmac/userService/model"})
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

}
