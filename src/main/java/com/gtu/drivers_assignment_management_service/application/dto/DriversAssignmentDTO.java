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

    @NotNull(message = "The route ID cannot be null")
    private int route;
    
    @NotEmpty(message = "The driver name cannot be empty")
    private String name;
    
    @NotEmpty(message = "The driver assigned to route list cannot be empty")
    @Size(min = 1, message = "The drivers assigned to route must have at least one driver")
    private List<Long> driversAssignedToRoute;

}
