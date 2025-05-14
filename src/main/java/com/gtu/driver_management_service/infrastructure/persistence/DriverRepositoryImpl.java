package com.gtu.driver_management_service.infrastructure.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.gtu.driver_management_service.domain.model.Driver;
import com.gtu.driver_management_service.domain.repository.DriverRepository;
import com.gtu.driver_management_service.infrastructure.persistence.entity.DriverEntity;
import com.gtu.driver_management_service.infrastructure.persistence.mapper.DriverEntityMapper;

@Repository
public class DriverRepositoryImpl implements DriverRepository {
    private final JpaDriverRepository jpaDriverRepository;
    private final DriverEntityMapper driverEntityMapper;

    public DriverRepositoryImpl(
            JpaDriverRepository jpaDriverRepository,
            DriverEntityMapper driverEntityMapper) {
        this.jpaDriverRepository = jpaDriverRepository;
        this.driverEntityMapper = driverEntityMapper;
    }

    @Override
    public Driver save(Driver driver){
        DriverEntity driverEntity = driverEntityMapper.toEntity(driver);
        DriverEntity saveEntity = jpaDriverRepository.save(driverEntity);
        return driverEntityMapper.toDomain(saveEntity);
    }

    @Override
    public Optional<Driver> findByName(String name) {
        return jpaDriverRepository.findByName(name).map(driverEntityMapper::toDomain);
    }

    
    @Override
    public List<Driver> findAll() {
        return jpaDriverRepository.findAll().stream()
                .map(driverEntityMapper::toDomain)
                .toList();
    }

    

}
