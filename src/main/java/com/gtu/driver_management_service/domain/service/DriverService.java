package com.gtu.driver_management_service.domain.service;

import java.util.List;

import com.gtu.driver_management_service.domain.model.Driver;

public interface DriverService {
    void validateDriver(Driver driver);
    Driver saveDriver(Driver driver);
    List<Driver> getAllDrivers();
    Driver createDriver(Driver driver);
}
