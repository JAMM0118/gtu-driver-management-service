package com.gtu.drivers_assignment_management_service;

import org.springframework.beans.factory.annotation.Value;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class StartupCheck {

    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @PostConstruct
    public void check() {
        System.out.println("DATASOURCE URL: " + datasourceUrl);
        System.out.println("DATASOURCE USER: " + username);
    }
}
