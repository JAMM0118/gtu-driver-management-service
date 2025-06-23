package com.gtu.drivers_assignment_management_service.application.usecase;


import java.util.List;


import org.springframework.stereotype.Service;

import com.gtu.drivers_assignment_management_service.application.dto.DriversAssignmentDTO;
import com.gtu.drivers_assignment_management_service.application.mapper.DriverAssignmentMapper;
import com.gtu.drivers_assignment_management_service.domain.service.DriversAssignmentService;


@Service
public class DriversAssignmentUseCase {
    private final DriversAssignmentService driversAssignmentService;
    
    public DriversAssignmentUseCase(DriversAssignmentService driversAssignmentService) {
        this.driversAssignmentService = driversAssignmentService;
       
    }

    public DriversAssignmentDTO assignDriverToRoute(DriversAssignmentDTO driversAssignmentDTO) {
        var driverAssignment = driversAssignmentService.assignmentDriver(driversAssignmentDTO.getDriverId(), driversAssignmentDTO.getRouteId());
        return DriverAssignmentMapper.toDTO(driverAssignment);
    }

    

    public List<DriversAssignmentDTO> getAllAssignedDrivers() {
        var driverAssignments = driversAssignmentService.getAllAssignedDrivers();
        return driverAssignments.stream()
                .map(DriverAssignmentMapper::toDTO)
                .toList();
    }

    public DriversAssignmentDTO getDriverAssignmentById(Long id) {
        var driverAssignment = driversAssignmentService.getDriverAssignmentById(id);
        return DriverAssignmentMapper.toDTO(driverAssignment);
    }

    public List<DriversAssignmentDTO> getAllAssignedDriversByRouteId(Long routeId) {
        var driverAssignments = driversAssignmentService.getAllAssignedDriversByRouteId(routeId);
        return driverAssignments.stream()
                .map(DriverAssignmentMapper::toDTO)
                .toList();
    }

    public DriversAssignmentDTO getDriverAssignmentByDriverId(Long driverId) {
        var driverAssignment = driversAssignmentService.getDriverAssignmentByDriverId(driverId);
        return DriverAssignmentMapper.toDTO(driverAssignment);
    }
   

    public void deleteADriverAssignment(Long id) {
        driversAssignmentService.deleteDriverAssignmentById(id);
    }
}
