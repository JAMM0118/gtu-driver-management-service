package com.gtu.drivers_assignment_management_service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;


@Configuration
@OpenAPIDefinition(
    info = @Info(title = "Assign Drivers Management API", version = "1.0", description = "API for managing drivers assignment"),
    servers = {
        @Server(url = "${SWAGGER_SERVER_URL:http://localhost/api/assign_driver}", description = "Server URL")
    }
)
public class OpenAPIConfig {
    @Bean
    public OpenAPI customOpenAPI() {
       return new OpenAPI()
           .components(new Components()
               .addSecuritySchemes("bearer-jwt",
                   new SecurityScheme()
                       .type(SecurityScheme.Type.HTTP)
                       .scheme("bearer")
                       .bearerFormat("JWT")))
           .addSecurityItem(new SecurityRequirement().addList("bearer-jwt"));
   }
}