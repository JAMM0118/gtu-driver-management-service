package com.gtu.drivers_assignment_management_service.domain.repository;

import java.util.List;
import java.util.Optional;

import com.gtu.drivers_assignment_management_service.domain.model.DriversAssignment;

public interface DriversAssignmentRepository {
    Optional<DriversAssignment> findByRouteName(String routeName);
    DriversAssignment save(DriversAssignment driversAssignment);
    List<DriversAssignment> findAll();
    void deleteById(Long id);
    Optional<DriversAssignment> findById(Long id);
}
