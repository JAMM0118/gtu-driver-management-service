package com.gtu.driver_management_service.presentation;

import org.springframework.web.bind.annotation.RestController;

import com.gtu.driver_management_service.application.dto.DriverDTO;
import com.gtu.driver_management_service.application.mapper.DriverMapper;

import org.springframework.web.bind.annotation.RequestMapping;
import com.gtu.driver_management_service.domain.model.Driver;
import com.gtu.driver_management_service.domain.service.DriverService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;




@RestController
@RequestMapping("/drivers")
public class DriverController {
    private final DriverService driverService;
    private final DriverMapper driverMapper;

    public DriverController(DriverService driverService, DriverMapper driverMapper) {
        this.driverService = driverService;
        this.driverMapper = driverMapper;
    }

    @PostMapping    
    public String createDriver(@Valid DriverDTO driverDTO) {
        Driver driver = driverMapper.toDomain(driverDTO);
        driverService.validateDriver(driver);
        Driver saveDriver = driverService.saveDriver(driver);
        return "Driver Created sucesfully" + saveDriver.getName();
    }
    
}
