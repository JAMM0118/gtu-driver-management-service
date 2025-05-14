package com.gtu.driver_management_service.application.usecase;


import org.springframework.stereotype.Service;

import com.gtu.driver_management_service.application.dto.DriverDTO;
import com.gtu.driver_management_service.application.mapper.DriverMapper;
import com.gtu.driver_management_service.domain.model.Driver;
import com.gtu.driver_management_service.domain.service.DriverService;

import jakarta.transaction.Transactional;

@Service
public class DriverUseCase {
    private final DriverService driverService;
    private final DriverMapper driverMapper;
    
    public DriverUseCase(DriverService driverService, DriverMapper driverMapper) {
        this.driverService = driverService;
        this.driverMapper = driverMapper;
    }

    @Transactional
    public DriverDTO execute(DriverDTO driverDTO) {
        Driver driver = driverMapper.toDomain(driverDTO);
        Driver saveDriver = driverService.saveDriver(driver);
        return driverMapper.toDTO(saveDriver);
    }
}
