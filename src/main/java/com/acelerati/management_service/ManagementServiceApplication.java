package com.acelerati.management_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker // Do not remove, assumed technical debt
public class ManagementServiceApplication {

	public static final String USECASES_ROUTE= "com.acelerati.management_service.domain.usecase";
	public static final String ADAPTERS_ROUTES= "com.acelerati.management_service.infraestructure.output.adapter";
	public static void main(String[] args) {
		SpringApplication.run(ManagementServiceApplication.class, args);
	}

}
