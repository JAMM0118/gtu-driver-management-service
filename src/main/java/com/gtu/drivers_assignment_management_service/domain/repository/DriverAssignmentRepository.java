package com.gtu.drivers_assignment_management_service.domain.repository;

import java.util.List;
import java.util.Optional;

import com.gtu.drivers_assignment_management_service.domain.model.DriverAssignment;

public interface DriverAssignmentRepository {
   
    DriverAssignment save(DriverAssignment driversAssignment);
    List<DriverAssignment> findAll();
    void deleteById(Long id);
    Optional<DriverAssignment> findById(Long id);
    void update(DriverAssignment driversAssignment);
    Optional<DriverAssignment> getDriverAssignmentByDriverId(Long driverId);
}
