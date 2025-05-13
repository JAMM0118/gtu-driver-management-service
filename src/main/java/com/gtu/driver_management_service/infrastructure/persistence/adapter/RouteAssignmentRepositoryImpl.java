package com.gtu.driver_management_service.infrastructure.persistence.adapter;

import com.gtu.driver_management_service.domain.model.RouteAssignment;
import com.gtu.driver_management_service.domain.repository.RouteAssignmentRepository;
import com.gtu.driver_management_service.infrastructure.persistence.entity.RouteAssignmentEntity;
import com.gtu.driver_management_service.infrastructure.persistence.repository.RouteAssignmentJpaRepository;
import org.springframework.stereotype.Component;

@Component
public class RouteAssignmentRepositoryImpl implements RouteAssignmentRepository {

    private final RouteAssignmentJpaRepository jpaRepository;

    public RouteAssignmentRepositoryImpl(RouteAssignmentJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void save(RouteAssignment assignment) {
        RouteAssignmentEntity entity = new RouteAssignmentEntity();
        entity.setDriverId(assignment.getDriverId());
        entity.setRouteId(assignment.getRouteId());
        jpaRepository.save(entity);
    }
}
