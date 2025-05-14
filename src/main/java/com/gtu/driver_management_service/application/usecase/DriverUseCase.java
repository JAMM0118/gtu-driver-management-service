package com.gtu.driver_management_service.application.usecase;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.gtu.driver_management_service.application.dto.DriverDTO;
import com.gtu.driver_management_service.application.mapper.DriverMapper;
import com.gtu.driver_management_service.domain.model.Driver;
import com.gtu.driver_management_service.domain.service.DriverService;


@Service
public class DriverUseCase {
    private final DriverService driverService;
    
    public DriverUseCase(DriverService driverService) {
        this.driverService = driverService;
       
    }

    public DriverDTO createDriver(DriverDTO driverDTO) {
        Driver driver = DriverMapper.toDomain(driverDTO);
        driverService.validateDriver(driver);
        Driver savedDriver = driverService.saveDriver(driver);
        return DriverMapper.toDTO(savedDriver);
    }

    

    public List<DriverDTO> getAllDrivers() {
        List<Driver> drivers = driverService.getAllDrivers();
        return drivers.stream()
                .map(DriverMapper::toDTO)
                .collect(Collectors.toList());
    }
}
