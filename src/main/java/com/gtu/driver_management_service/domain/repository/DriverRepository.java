package com.gtu.driver_management_service.domain.repository;

import java.util.List;
import java.util.Optional;

import com.gtu.driver_management_service.domain.model.Driver;

public interface DriverRepository {
    Optional<Driver> findByName(String name);
    Driver save(Driver driver);
    List<Driver> findAll();
}
