package com.gtu.drivers_assignment_management_service.application.dto;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriversAssignmentDTO {
       
    private Long id;

    @NotNull(message = "The driver ID cannot be null")
    private int driverId;
    
    @NotEmpty(message = "The driver name cannot be empty")
    private String name;
    
    @NotEmpty(message = "The routesAssigned list cannot be empty")
    @Size(min = 2, message = "The routes assigned must have at least two routes")
    private List<Long> routesAssigned;

}
