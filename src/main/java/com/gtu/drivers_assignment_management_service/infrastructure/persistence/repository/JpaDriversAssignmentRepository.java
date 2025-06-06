package com.gtu.drivers_assignment_management_service.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gtu.drivers_assignment_management_service.infrastructure.persistence.entity.DriversAssignmentEntity;

import java.util.Optional;

@Repository
public interface JpaDriversAssignmentRepository extends JpaRepository<DriversAssignmentEntity,Long> {
    Optional<DriversAssignmentEntity> findByRouteName(String routeName);
    




}
