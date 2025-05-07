package com.gtu.driver_management_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DriverManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DriverManagementServiceApplication.class, args);
		System.out.println("Driver Management Service is running...");
	}

}
