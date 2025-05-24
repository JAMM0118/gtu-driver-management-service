package com.gtu.drivers_assignment_management_service.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.gtu.drivers_assignment_management_service.domain.model.DriversAssignment;
import com.gtu.drivers_assignment_management_service.domain.repository.DriversAssignmentRepository;
import com.gtu.drivers_assignment_management_service.infrastructure.persistence.entity.DriversAssignmentEntity;
import com.gtu.drivers_assignment_management_service.infrastructure.persistence.mapper.DriversAssignmentEntityMapper;

@Repository
public class DriversAssignmentRepositoryImpl implements DriversAssignmentRepository {
    private final JpaDriversAssignmentRepository jpaDriversAssignmentRepository;
    private final DriversAssignmentEntityMapper driversAssignmentEntityMapper;

    public DriversAssignmentRepositoryImpl(
            JpaDriversAssignmentRepository jpaDriversAssignmentRepository,
            DriversAssignmentEntityMapper driversAssignmentEntityMapper ) {
        this.jpaDriversAssignmentRepository = jpaDriversAssignmentRepository;
        this.driversAssignmentEntityMapper = driversAssignmentEntityMapper;
    }

    @Override
    public DriversAssignment save(DriversAssignment driversAssignment){
        DriversAssignmentEntity driversAssignmentEntity = driversAssignmentEntityMapper.toEntity(driversAssignment);
        DriversAssignmentEntity saveEntity = jpaDriversAssignmentRepository.save(driversAssignmentEntity);
        
        return driversAssignmentEntityMapper.toDomain(saveEntity);
    }

    @Override
    public Optional<DriversAssignment> findByName(String name) {
        return jpaDriversAssignmentRepository.findByName(name).map(driversAssignmentEntityMapper::toDomain);
    }

    
    @Override
    public List<DriversAssignment> findAll() {
        return jpaDriversAssignmentRepository.findAll().stream()
                .map(driversAssignmentEntityMapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        jpaDriversAssignmentRepository.deleteById(id);

    }

    @Override
    public Optional<DriversAssignment> findById(Long id) {
        return jpaDriversAssignmentRepository.findById(id)
                .map(driversAssignmentEntityMapper::toDomain);
    }
    

    

}
