package com.gtu.driver_management_service.application.service;


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
    if(driverRepository.findById(driver.getId()).isPresent()){
           throw new IllegalArgumentException("The route name already exists: " + driver.getName());

    }
   }

   @Override
   public Driver saveDriver(Driver driver){
    validateDriver(driver);
    return driverRepository.save(driver);
   }

}
