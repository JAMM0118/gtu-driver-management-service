package com.gtu.drivers_assignment_management_service.infrastructure.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class DataSourceLogger implements CommandLineRunner {

    @Autowired
    private Environment env;

    @Override
    public void run(String... args) {
        System.out.println("🔧 [DataSourceLogger] Primary JDBC URL: " + env.getProperty("spring.datasource.jdbc-url"));
        System.out.println("🔧 [DataSourceLogger] Secondary JDBC URL: " + env.getProperty("secondary.datasource.jdbc-url"));
        System.out.println("🔧 [DataSourceLogger] DB User: " + env.getProperty("DB_USER"));
    }
}
