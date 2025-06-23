package com.gtu.drivers_assignment_management_service.application.mapper;

import com.gtu.drivers_assignment_management_service.application.dto.DriversAssignmentDTO;
import com.gtu.drivers_assignment_management_service.domain.model.DriverAssignment;
import com.gtu.drivers_assignment_management_service.infrastructure.logs.LogPublisher;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DriverAssignmentMapper {

    private final LogPublisher logPublisher;

    public DriverAssignmentMapper(LogPublisher logPublisher) {
        this.logPublisher = logPublisher;
    }

    public DriverAssignment toDomain(DriversAssignmentDTO driversAssignmentDTO) {
        logPublisher.sendLog(
                String.valueOf(System.currentTimeMillis()),
                "DriverAssignmentMapper",
                "INFO",
                "Mapping DTO to Domain",
                Map.of("DTO", driversAssignmentDTO));
        return new DriverAssignment(
                driversAssignmentDTO.getId(),
                driversAssignmentDTO.getDriverId(),
                driversAssignmentDTO.getRouteId(),
                driversAssignmentDTO.getCurrentStopId(),
                driversAssignmentDTO.getLatestStopId(),
                driversAssignmentDTO.getNextStopId());
    }

    public DriversAssignmentDTO toDTO(DriverAssignment driversAssignment) {
        logPublisher.sendLog(
                String.valueOf(System.currentTimeMillis()),
                "DriverAssignmentMapper",
                "INFO",
                "Mapping Domain to DTO",
                Map.of("Domain", driversAssignment));
        return new DriversAssignmentDTO(
                driversAssignment.getId(),
                driversAssignment.getDriverId(),
                driversAssignment.getRouteId(),
                driversAssignment.getCurrentStopId(),
                driversAssignment.getLatestStopId(),
                driversAssignment.getNextStopId());
    }
}
