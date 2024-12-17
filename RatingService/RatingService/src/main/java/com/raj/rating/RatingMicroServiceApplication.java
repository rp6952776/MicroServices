package com.raj.rating;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RatingMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RatingMicroServiceApplication.class, args);
		System.out.println("RatingService Run");
	}

}
