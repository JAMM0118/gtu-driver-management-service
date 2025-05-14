package com.gtu.driver_management_service.application.mapper;

import com.gtu.driver_management_service.application.dto.DriverDTO;
import com.gtu.driver_management_service.domain.model.Driver;

import lombok.experimental.UtilityClass;

@UtilityClass
public class DriverMapper {
    
    public Driver toDomain(DriverDTO driverDTO){
        return new Driver(
            driverDTO.getId(),
            driverDTO.getDriverId(),
            driverDTO.getName(),
            driverDTO.getRouteAssigned()
        );
    }

    public DriverDTO toDTO(Driver driver){
        return new DriverDTO(
            driver.getId(),
            driver.getDriverId(),
            driver.getName(),
            driver.getRouteAssigned()
        );
    }
    
}
