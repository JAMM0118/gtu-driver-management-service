package com.gtu.driver_management_service.infrastructure.persistence.adapter;

import com.gtu.driver_management_service.domain.model.RouteAssignment;
import com.gtu.driver_management_service.domain.repository.RouteAssignmentRepository;
import com.gtu.driver_management_service.infrastructure.persistence.repository.JpaRouteAssignmentRepository;
import org.springframework.stereotype.Component;

@Component
public class RouteAssignmentRepositoryImpl implements RouteAssignmentRepository {

    private final JpaRouteAssignmentRepository jpaRepository;

    public RouteAssignmentRepositoryImpl(JpaRouteAssignmentRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void save(RouteAssignment assignment) {
        jpaRepository.save(assignment);
    }
}
