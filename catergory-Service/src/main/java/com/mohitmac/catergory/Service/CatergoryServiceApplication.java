package com.mohitmac.catergory.Service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CatergoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatergoryServiceApplication.class, args);
	}

}
