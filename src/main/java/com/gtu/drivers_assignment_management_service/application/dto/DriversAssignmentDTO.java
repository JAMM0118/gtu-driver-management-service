package com.gtu.drivers_assignment_management_service.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
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

    @Schema(description = "ID of the driver assigned to the route", example = "1001")
    @NotNull(message = "The driver ID cannot be null")
    private Long driverId;


    @Schema(description = "ID of the route assigned to the driver", example = "2001")
    @NotNull(message = "The route ID cannot be null")
    private Long routeId;


    @Schema(description = "ID of the current stop in the route", example = "3001" , accessMode = Schema.AccessMode.READ_ONLY)
    private Long currentStopId;

    @Schema(description = "ID of the latest stop in the route", example = "4001" , accessMode = Schema.AccessMode.READ_ONLY)
    private Long latestStopId;

    @Schema(description = "ID of the next stop in the route", example = "5001" , accessMode = Schema.AccessMode.READ_ONLY)
    private Long nextStopId;
}
