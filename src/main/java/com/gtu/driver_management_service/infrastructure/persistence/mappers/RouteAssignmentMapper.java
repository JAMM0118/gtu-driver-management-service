package com.gtu.driver_management_service.infrastructure.persistence.mappers;

import com.gtu.driver_management_service.domain.model.RouteAssignment;
import com.gtu.driver_management_service.infrastructure.persistence.entity.RouteAssignmentEntity;
import org.springframework.stereotype.Component;

@Component
public class RouteAssignmentMapper {

    public RouteAssignmentEntity toEntity(RouteAssignment assignment) {
        if (assignment == null) {
            return null;
        }
        RouteAssignmentEntity entity = new RouteAssignmentEntity();
        entity.setId(assignment.getId());
        entity.setDriverId(assignment.getDriverId());
        entity.setRouteId(assignment.getRouteId());
        entity.setAssignedAt(assignment.getAssignedAt());
        return entity;
    }

    public RouteAssignment toModel(RouteAssignmentEntity entity) {
        if (entity == null) {
            return null;
        }
        return new RouteAssignment(
                entity.getId(),
                entity.getDriverId(),
                entity.getRouteId(),
                entity.getAssignedAt());
    }
}
