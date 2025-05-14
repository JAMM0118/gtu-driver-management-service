package com.gtu.driver_management_service.application.mapper;

import org.springframework.stereotype.Component;
import com.gtu.driver_management_service.application.dto.DriverDTO;
import com.gtu.driver_management_service.domain.model.Driver;

@Component
public class DriverMapper {
    
    public Driver toDomain(DriverDTO driverDTO){
        return new Driver(
            driverDTO.getId(),
            driverDTO.getName(),
            driverDTO.getEmail(),
            driverDTO.getPhoneNumber(),
            driverDTO.getRouteAssigned()
        );
    }

    public DriverDTO toDTO(Driver driver){
        return new DriverDTO();
    }
    
}
