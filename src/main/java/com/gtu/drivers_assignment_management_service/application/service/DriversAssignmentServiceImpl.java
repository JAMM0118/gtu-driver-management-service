package com.gtu.drivers_assignment_management_service.application.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.gtu.drivers_assignment_management_service.domain.model.DriversAssignment;
import com.gtu.drivers_assignment_management_service.domain.repository.DriversAssignmentRepository;
import com.gtu.drivers_assignment_management_service.domain.service.DriversAssignmentService;

@Service
public class DriversAssignmentServiceImpl  implements DriversAssignmentService{
    private final DriversAssignmentRepository driversAssignmentRepository;
    
    public DriversAssignmentServiceImpl(DriversAssignmentRepository driversAssignmentRepository) {
        this.driversAssignmentRepository = driversAssignmentRepository;
    }

   @Override
   public void validateDriver(DriversAssignment driversAssignment){
    if (driversAssignment.getRouteName() == null || driversAssignment.getRouteName().isEmpty()) {
        throw new IllegalArgumentException("Driver name cannot be null or empty");
    }
    if (driversAssignment.getRouteId() <= 0) {
        throw new IllegalArgumentException("Driver ID must be greater than 0");
    }
    
   }

   @Override
   public DriversAssignment saveDriver(DriversAssignment driversAssignment){
    validateDriver(driversAssignment);
    return driversAssignmentRepository.save(driversAssignment);
   }

   @Override
   public List<DriversAssignment> getAllAssignedDrivers() {
    return driversAssignmentRepository.findAll();
   }

  
   @Override
   public DriversAssignment assignDriver(DriversAssignment driversAssignment) {
    return driversAssignmentRepository.save(driversAssignment);
   }

    @Override
    public DriversAssignment updateADriversAssignment(DriversAssignment driversAssignment) {
        DriversAssignment existingAssignment = driversAssignmentRepository.findById(driversAssignment.getId())
                .orElseThrow(() -> new IllegalArgumentException("Driver assignment not found"));

        existingAssignment.setRouteName(driversAssignment.getRouteName());
        existingAssignment.setRouteId(driversAssignment.getRouteId());
        existingAssignment.setRouteAssigned(driversAssignment.getDriversAssignedToRoute());

        return driversAssignmentRepository.save(existingAssignment);
    }

    @Override
    public void deleteADriverAssignment(Long id) {
        driversAssignmentRepository.deleteById(id);
    }

    



}
