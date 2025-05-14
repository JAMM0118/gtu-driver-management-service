package com.gtu.drivers_assignment_management_service.domain.service;

import java.util.List;

import com.gtu.drivers_assignment_management_service.domain.model.DriversAssignment;

public interface DriversAssignmentService {
    void validateDriver(DriversAssignment driversAssignment);
    DriversAssignment saveDriver(DriversAssignment driversAssignment);
    List<DriversAssignment> getAllAssignedDrivers();
    DriversAssignment assignDriver(DriversAssignment driversAssignment);
}
