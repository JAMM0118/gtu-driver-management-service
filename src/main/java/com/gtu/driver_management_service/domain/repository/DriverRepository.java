package com.gtu.driver_management_service.domain.repository;

import java.util.Optional;

import com.gtu.driver_management_service.domain.model.Driver;

public interface DriverRepository {
    Optional<Driver> findById(Long id);
    Driver save(Driver driver);
}
