package com.gtu.drivers_assignment_management_service.domain.service;

import java.util.List;

import com.gtu.drivers_assignment_management_service.domain.model.DriverAssignment;

public interface DriversAssignmentService {

    DriverAssignment assignmentDriver(Long driverId, Long routeId);
    void updateCurrentStopId(Long driverId, double latitude, double longitude);
    List<DriverAssignment> getAllAssignedDriversByRouteId(Long routeId);
    DriverAssignment getDriverAssignmentById(Long id);
    DriverAssignment deleteDriverAssignmentById(Long id);
    DriverAssignment getDriverAssignmentByDriverId(Long driverId);
    List<DriverAssignment> getAllAssignedDrivers();

}
