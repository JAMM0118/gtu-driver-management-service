package com.gtu.driver_management_service.infrastructure.persistence.repository;

import com.gtu.driver_management_service.infrastructure.persistence.entity.RouteAssignmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteAssignmentJpaRepository extends JpaRepository<RouteAssignmentEntity, Long> {}
