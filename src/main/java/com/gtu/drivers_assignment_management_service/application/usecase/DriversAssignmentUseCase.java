package com.gtu.drivers_assignment_management_service.application.usecase;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.gtu.drivers_assignment_management_service.application.dto.DriversAssignmentDTO;
import com.gtu.drivers_assignment_management_service.application.mapper.DriversAssignmentMapper;
import com.gtu.drivers_assignment_management_service.domain.model.DriversAssignment;
import com.gtu.drivers_assignment_management_service.domain.service.DriversAssignmentService;


@Service
public class DriversAssignmentUseCase {
    private final DriversAssignmentService driversAssignmentService;
    
    public DriversAssignmentUseCase(DriversAssignmentService driversAssignmentService) {
        this.driversAssignmentService = driversAssignmentService;
       
    }

    public DriversAssignmentDTO assignDriver(DriversAssignmentDTO driversAssignmentDTO) {
        DriversAssignment driversAssignment = DriversAssignmentMapper.toDomain(driversAssignmentDTO);
        driversAssignmentService.validateDriver(driversAssignment);
        DriversAssignment savedDriver = driversAssignmentService.saveDriver(driversAssignment);
        return DriversAssignmentMapper.toDTO(savedDriver);
    }

    

    public List<DriversAssignmentDTO> getAllAssignedDrivers() {
        List<DriversAssignment> drivers = driversAssignmentService.getAllAssignedDrivers();
        return drivers.stream()
                .map(DriversAssignmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public DriversAssignmentDTO updateADriversAssignment(Long id, DriversAssignmentDTO driversAssignmentDTO) {
        DriversAssignment driversAssignment = DriversAssignmentMapper.toDomain(driversAssignmentDTO);
        driversAssignment.setId(id);
        DriversAssignment updatedDriver = driversAssignmentService.updateADriversAssignment(driversAssignment);
        return DriversAssignmentMapper.toDTO(updatedDriver);
    }

    public void deleteADriverAssignment(Long id) {
        driversAssignmentService.deleteADriverAssignment(id);
    }
}
