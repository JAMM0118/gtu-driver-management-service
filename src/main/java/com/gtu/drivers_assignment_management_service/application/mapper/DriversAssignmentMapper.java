package com.gtu.drivers_assignment_management_service.application.mapper;

import com.gtu.drivers_assignment_management_service.application.dto.DriversAssignmentDTO;
import com.gtu.drivers_assignment_management_service.domain.model.DriversAssignment;

import lombok.experimental.UtilityClass;

@UtilityClass
public class DriversAssignmentMapper {
    
    public DriversAssignment toDomain(DriversAssignmentDTO driversAssignmentDTO){
        return new DriversAssignment(
            driversAssignmentDTO.getId(),
            driversAssignmentDTO.getRoute(),
            driversAssignmentDTO.getName(),
            driversAssignmentDTO.getDriversAssignedToRoute()
        );
    }

    public DriversAssignmentDTO toDTO(DriversAssignment driversAssignment){
        return new DriversAssignmentDTO(
            driversAssignment.getId(),
            driversAssignment.getRoute(),
            driversAssignment.getName(),
            driversAssignment.getDriversAssignedToRoute()
        );
    }
    
}
