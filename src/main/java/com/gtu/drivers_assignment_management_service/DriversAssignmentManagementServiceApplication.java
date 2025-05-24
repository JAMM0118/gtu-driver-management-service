package com.gtu.drivers_assignment_management_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableDiscoveryClient
public class DriversAssignmentManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DriversAssignmentManagementServiceApplication.class, args);
		System.out.println("Drivers Assignment Management Service is running...");
	}

}
