package com.gtu.drivers_assignment_management_service.application.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Driver Assignment DTO")
public class DriversAssignmentDTO {
    
    @Schema(description = "Driver Assignment ID", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Route ID", example = "101")
    @NotNull(message = "The route ID cannot be null")
    private int routeId;
    
    @Schema(description = "Route Name", example = "Downtown to Uptown")
    @NotEmpty(message = "The route name cannot be empty")
    private String routeName;
    
    @Schema(description = "List of Drivers Assigned to Route", example = "[1, 2, 3]")
    @NotEmpty(message = "The driver assigned to route list cannot be empty")
    @Size(min = 1, message = "The drivers assigned to route must have at least one driver")
    private List<Long> driversAssignedToRoute;

}
