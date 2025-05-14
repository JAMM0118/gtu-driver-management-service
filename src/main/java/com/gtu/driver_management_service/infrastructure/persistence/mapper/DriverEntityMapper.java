package com.gtu.driver_management_service.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import com.gtu.driver_management_service.domain.model.Driver;
import com.gtu.driver_management_service.infrastructure.persistence.entity.DriverEntity;

@Component
public class DriverEntityMapper {
    public DriverEntity toEntity(Driver driver) {
        return new DriverEntity(driver.getName(), driver.getEmail(), driver.getPhoneNumber(), driver.getRouteAssigned());
    }

    public Driver toDomain(DriverEntity entity) {
        return new Driver(entity.getId(), entity.getName(), entity.getEmail(), entity.getPhoneNumber(), entity.getRouteAssigned());
    }
}
