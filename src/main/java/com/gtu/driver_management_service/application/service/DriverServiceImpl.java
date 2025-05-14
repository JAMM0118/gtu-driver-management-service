package com.gtu.driver_management_service.application.service;


import java.util.List;

import org.springframework.stereotype.Service;
import com.gtu.driver_management_service.domain.model.Driver;
import com.gtu.driver_management_service.domain.repository.DriverRepository;
import com.gtu.driver_management_service.domain.service.DriverService;

@Service
public class DriverServiceImpl  implements DriverService{
    private final DriverRepository driverRepository;
    
    public DriverServiceImpl(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

   @Override
   public void validateDriver(Driver driver){
    if (driver.getName() == null || driver.getName().isEmpty()) {
        throw new IllegalArgumentException("Driver name cannot be null or empty");
    }
    if (driver.getDriverId() <= 0) {
        throw new IllegalArgumentException("Driver ID must be greater than 0");
    }
    
   }

   @Override
   public Driver saveDriver(Driver driver){
    validateDriver(driver);
    return driverRepository.save(driver);
   }

   @Override
   public List<Driver> getAllDrivers() {
    return driverRepository.findAll();
   }

  
   @Override
   public Driver createDriver(Driver driver) {
    return driverRepository.save(driver);
   }

    



}
