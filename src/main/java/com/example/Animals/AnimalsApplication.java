package com.example.Animals;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class AnimalsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnimalsApplication.class, args);
	}

}
