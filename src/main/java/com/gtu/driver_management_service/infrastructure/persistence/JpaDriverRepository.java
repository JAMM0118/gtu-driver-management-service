package com.gtu.driver_management_service.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gtu.driver_management_service.infrastructure.persistence.entity.DriverEntity;

import java.util.Optional;

@Repository
public interface JpaDriverRepository extends JpaRepository<DriverEntity,Long> {
    Optional<DriverEntity> findByName(String name);



}
