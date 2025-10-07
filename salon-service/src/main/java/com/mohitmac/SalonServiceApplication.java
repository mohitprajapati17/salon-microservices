package com.mohitmac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.mohitmac")
@EnableJpaRepositories(basePackages = "com.mohitmac.repository")
@EntityScan(basePackages = "com.mohitmac.model")
public class SalonServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalonServiceApplication.class, args);
	}

}
