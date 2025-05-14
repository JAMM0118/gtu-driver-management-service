package com.gtu.driver_management_service.application.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverDTO {
    
    
    @NotEmpty(message = "The driver name cannot be empty")
    private String name;
    
    private Long id;
    private String phoneNumber;
    
    @NotEmpty(message = "The driver email cannot be empty")
    private String email;
    
    @NotEmpty(message = "The routeAssigned list cannot be empty")
    @Size(min = 2, message = "The routes assigned must have at least two routes")
    private Integer[] routeAssigned;


    



}
