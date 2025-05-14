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
    if (driversAssignment.getName() == null || driversAssignment.getName().isEmpty()) {
        throw new IllegalArgumentException("Driver name cannot be null or empty");
    }
    if (driversAssignment.getDriverId() <= 0) {
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

    



}
