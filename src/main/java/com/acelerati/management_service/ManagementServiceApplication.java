package com.acelerati.management_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker // Do not remove, assumed technical debt
public class ManagementServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(ManagementServiceApplication.class, args);
	}

}
