package com.gtu.drivers_assignment_management_service.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.gtu.drivers_assignment_management_service.domain.model.DriverAssignment;
import com.gtu.drivers_assignment_management_service.domain.repository.DriverAssignmentRepository;
import com.gtu.drivers_assignment_management_service.infrastructure.persistence.entity.DriverAssignmentEntity;
import com.gtu.drivers_assignment_management_service.infrastructure.persistence.mapper.DriverAssignmentEntityMapper;

@Repository
public class DriverAssignmentRepositoryImpl implements DriverAssignmentRepository {
    private final JpaDriverAssignmentRepository jpaDriversAssignmentRepository;

    public DriverAssignmentRepositoryImpl(
            JpaDriverAssignmentRepository jpaDriversAssignmentRepository ) {
        this.jpaDriversAssignmentRepository = jpaDriversAssignmentRepository;
    }

    @Override
    public DriverAssignment save(DriverAssignment driversAssignment){
        DriverAssignmentEntity driversAssignmentEntity = DriverAssignmentEntityMapper.toEntity(driversAssignment);
        DriverAssignmentEntity saveEntity = jpaDriversAssignmentRepository.save(driversAssignmentEntity);

        return DriverAssignmentEntityMapper.toDomain(saveEntity);
    }

    
    @Override
    public List<DriverAssignment> findAll() {
        return jpaDriversAssignmentRepository.findAll().stream()
                .map(DriverAssignmentEntityMapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        jpaDriversAssignmentRepository.deleteById(id);

    }

    @Override
    public Optional<DriverAssignment> findById(Long id) {
        return jpaDriversAssignmentRepository.findById(id)
                .map(DriverAssignmentEntityMapper::toDomain);
    }

    @Override
    public void update(DriverAssignment driversAssignment) {
        DriverAssignmentEntity driversAssignmentEntity = DriverAssignmentEntityMapper.toEntity(driversAssignment);
        jpaDriversAssignmentRepository.save(driversAssignmentEntity);
        
    }

    @Override
    public Optional<DriverAssignment> getDriverAssignmentByDriverId(Long driverId) {
        return jpaDriversAssignmentRepository.findByDriverId(driverId)
                .map(DriverAssignmentEntityMapper::toDomain);
    }

}
