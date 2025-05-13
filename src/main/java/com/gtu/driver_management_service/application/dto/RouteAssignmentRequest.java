package com.gtu.driver_management_service.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO for assigning a route to a driver")
public class RouteAssignmentRequest {

    @Schema(description = "Driver ID", required = true)
    @NotNull(message = "Driver ID cannot be null")
    private UUID driverId;

    @Schema(description = "Route ID", required = true)
    @NotNull(message = "Route ID cannot be null")
    private UUID routeId;
}
