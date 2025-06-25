package com.gtu.drivers_assignment_management_service.application.mapper;

import com.gtu.drivers_assignment_management_service.application.dto.DriversAssignmentDTO;
import com.gtu.drivers_assignment_management_service.domain.model.DriverAssignment;
import com.gtu.drivers_assignment_management_service.infrastructure.logs.LogPublisher;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Map;

@Component
public class DriverAssignmentMapper{

    private final LogPublisher logPublisher;

    public DriverAssignmentMapper(LogPublisher logPublisher) {
        this.logPublisher = logPublisher;
    }

    public DriverAssignment toDomain(DriversAssignmentDTO driversAssignmentDTO) {
        DriverAssignment domain = new DriverAssignment(
                driversAssignmentDTO.getId(),
                driversAssignmentDTO.getDriverId(),
                driversAssignmentDTO.getRouteId(),
                driversAssignmentDTO.getCurrentStopId(),
                driversAssignmentDTO.getLatestStopId(),
                driversAssignmentDTO.getNextStopId()
        );

        if (driversAssignmentDTO.getCurrentStopId() != null && driversAssignmentDTO.getNextStopId() != null &&
                !driversAssignmentDTO.getCurrentStopId().equals(driversAssignmentDTO.getNextStopId())) {
            logPublisher.sendLog(
                    Instant.now().toString(),
                    "driver-management-service",
                    "INFO",
                    "Cambio de parada detectado en mapeo DTO -> Dominio",
                    Map.of(
                            "driverId", driversAssignmentDTO.getDriverId(),
                            "fromStopId", driversAssignmentDTO.getCurrentStopId(),
                            "toStopId", driversAssignmentDTO.getNextStopId()
                    )
            );
        }

        return domain;
    }

    public DriversAssignmentDTO toDTO(DriverAssignment driversAssignment) {
        if (driversAssignment.getLatestStopId() == null && driversAssignment.getCurrentStopId() != null) {
            logPublisher.sendLog(
                    Instant.now().toString(),
                    "driver-management-service",
                    "INFO",
                    "Asignación inicial detectada en mapeo Dominio -> DTO",
                    Map.of(
                            "driverId", driversAssignment.getDriverId(),
                            "initialStopId", driversAssignment.getCurrentStopId()
                    )
            );
        }
        
        return new DriversAssignmentDTO(
                driversAssignment.getId(),
                driversAssignment.getDriverId(),
                driversAssignment.getRouteId(),
                driversAssignment.getCurrentStopId(),
                driversAssignment.getLatestStopId(),
                driversAssignment.getNextStopId());
    }
}
