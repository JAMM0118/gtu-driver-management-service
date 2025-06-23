package com.gtu.drivers_assignment_management_service.infrastructure.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gtu.drivers_assignment_management_service.infrastructure.persistence.entity.DriverAssignmentEntity;



@Repository
public interface JpaDriverAssignmentRepository extends JpaRepository<DriverAssignmentEntity,Long> {

    Optional<DriverAssignmentEntity> findByDriverId(Long driverId);
}

