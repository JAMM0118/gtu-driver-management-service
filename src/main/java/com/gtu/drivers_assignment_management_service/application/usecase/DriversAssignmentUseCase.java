package com.gtu.drivers_assignment_management_service.application.usecase;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.gtu.drivers_assignment_management_service.application.dto.DriversAssignmentDTO;
import com.gtu.drivers_assignment_management_service.application.mapper.DriverAssignmentMapper;
import com.gtu.drivers_assignment_management_service.domain.service.DriversAssignmentService;
import com.gtu.drivers_assignment_management_service.infrastructure.logs.LogPublisher;

@Service
public class DriversAssignmentUseCase {
    private final DriversAssignmentService driversAssignmentService;
    private final LogPublisher logPublisher;
    private final DriverAssignmentMapper driverAssignmentMapper;

    public DriversAssignmentUseCase(DriversAssignmentService driversAssignmentService, LogPublisher logPublisher,
            DriverAssignmentMapper driverAssignmentMapper) {
        this.driversAssignmentService = driversAssignmentService;
        this.logPublisher = logPublisher;
        this.driverAssignmentMapper = driverAssignmentMapper;
    }

    public DriversAssignmentDTO assignDriverToRoute(DriversAssignmentDTO driversAssignmentDTO) {
        logPublisher.sendLog(
                String.valueOf(System.currentTimeMillis()),
                "DriversAssignmentUseCase",
                "INFO",
                "Assigning driver to route",
                Map.of("DTO", driversAssignmentDTO));
        var driverAssignment = driversAssignmentService.assignmentDriver(driversAssignmentDTO.getDriverId(),
                driversAssignmentDTO.getRouteId());
        return driverAssignmentMapper.toDTO(driverAssignment);
    }

    public List<DriversAssignmentDTO> getAllAssignedDrivers() {
        var driverAssignments = driversAssignmentService.getAllAssignedDrivers();
        return driverAssignments.stream()
                .map(driverAssignmentMapper::toDTO)
                .toList();
    }

    public DriversAssignmentDTO getDriverAssignmentById(Long id) {
        var driverAssignment = driversAssignmentService.getDriverAssignmentById(id);
        return driverAssignmentMapper.toDTO(driverAssignment);
    }

    public List<DriversAssignmentDTO> getAllAssignedDriversByRouteId(Long routeId) {
        var driverAssignments = driversAssignmentService.getAllAssignedDriversByRouteId(routeId);
        return driverAssignments.stream()
                .map(driverAssignmentMapper::toDTO)
                .toList();
    }

    public DriversAssignmentDTO getDriverAssignmentByDriverId(Long driverId) {
        var driverAssignment = driversAssignmentService.getDriverAssignmentByDriverId(driverId);
        return driverAssignmentMapper.toDTO(driverAssignment);
    }

    public void deleteADriverAssignment(Long id) {
        driversAssignmentService.deleteDriverAssignmentById(id);
    }
}
