package com.gtu.driver_management_service.infrastructure.persistence.repository;

import com.gtu.driver_management_service.domain.model.RouteAssignment;
import com.gtu.driver_management_service.domain.ports.out.RouteAssignmentRepository;
import com.gtu.driver_management_service.infrastructure.persistence.entity.RouteAssignmentEntity;
import com.gtu.driver_management_service.infrastructure.persistence.mappers.RouteAssignmentMapper;
import org.springframework.stereotype.Repository;

@Repository
public class JpaRouteAssignmentRepository implements RouteAssignmentRepository {

    private final SpringDataRouteAssignmentRepository jpaRepository;
    private final RouteAssignmentMapper mapper;

    public JpaRouteAssignmentRepository(SpringDataRouteAssignmentRepository jpaRepository,
                                        RouteAssignmentMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public void save(RouteAssignment assignment) {
        RouteAssignmentEntity entity = mapper.toEntity(assignment);
        jpaRepository.save(entity);
    }
}
