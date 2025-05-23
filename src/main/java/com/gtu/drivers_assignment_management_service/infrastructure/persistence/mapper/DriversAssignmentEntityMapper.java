package com.gtu.drivers_assignment_management_service.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import com.gtu.drivers_assignment_management_service.domain.model.DriversAssignment;
import com.gtu.drivers_assignment_management_service.infrastructure.persistence.entity.DriversAssignmentEntity;

@Component
public class DriversAssignmentEntityMapper {
    
    public DriversAssignmentEntity toEntity(DriversAssignment driversAssignment) {
        return new DriversAssignmentEntity(
            driversAssignment.getId(),
            driversAssignment.getRoute(),
            driversAssignment.getName(),
            driversAssignment.getDriversAssignedToRoute()
        );
    }

    public DriversAssignment toDomain(DriversAssignmentEntity entity) {
        return new DriversAssignment(
            entity.getId(),
            entity.getRoute(),
            entity.getName(),
            entity.getDriversAssignedToRoute()
        );
    }
}
