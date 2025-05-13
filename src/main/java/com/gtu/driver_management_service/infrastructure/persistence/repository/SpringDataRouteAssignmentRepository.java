package com.gtu.driver_management_service.infrastructure.persistence.repository;

import com.gtu.driver_management_service.infrastructure.persistence.entity.RouteAssignmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface SpringDataRouteAssignmentRepository extends JpaRepository<RouteAssignmentEntity, UUID> {
}
