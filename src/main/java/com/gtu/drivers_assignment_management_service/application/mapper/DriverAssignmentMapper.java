package com.gtu.drivers_assignment_management_service.application.mapper;

import com.gtu.drivers_assignment_management_service.application.dto.DriversAssignmentDTO;
import com.gtu.drivers_assignment_management_service.domain.model.DriverAssignment;

public class DriverAssignmentMapper {

    private DriverAssignmentMapper() {
        
    }

    public static DriverAssignment toDomain(DriversAssignmentDTO driversAssignmentDTO){
        return new DriverAssignment(
            driversAssignmentDTO.getId(),
            driversAssignmentDTO.getDriverId(),
            driversAssignmentDTO.getRouteId(),
            driversAssignmentDTO.getCurrentStopId(),
            driversAssignmentDTO.getLatestStopId(),
            driversAssignmentDTO.getNextStopId()
        );
    }

    public static DriversAssignmentDTO toDTO(DriverAssignment driversAssignment){
        return new DriversAssignmentDTO(
            driversAssignment.getId(),
            driversAssignment.getDriverId(),
            driversAssignment.getRouteId(),
            driversAssignment.getCurrentStopId(),
            driversAssignment.getLatestStopId(),
            driversAssignment.getNextStopId()
        );
    }
    
}
