package com.gtu.drivers_assignment_management_service.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import com.gtu.drivers_assignment_management_service.domain.model.DriverAssignment;
import com.gtu.drivers_assignment_management_service.infrastructure.persistence.entity.DriverAssignmentEntity;

@Component
public class DriverAssignmentEntityMapper {

    private DriverAssignmentEntityMapper() {
       
    }
    
    public static DriverAssignmentEntity toEntity(DriverAssignment driversAssignment) {
        return new DriverAssignmentEntity(
            driversAssignment.getId(),
            driversAssignment.getDriverId(),
            driversAssignment.getRouteId(),
            driversAssignment.getCurrentStopId(),
            driversAssignment.getLatestStopId(),
            driversAssignment.getNextStopId()
        );
    }

    public static DriverAssignment toDomain(DriverAssignmentEntity entity) {
        return new DriverAssignment(
            entity.getId(),
            entity.getDriverId(),
            entity.getRouteId(),
            entity.getCurrentStopId(),
            entity.getLatestStopId(),
            entity.getNextStopId()
        );
    }
}
