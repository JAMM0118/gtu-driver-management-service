package com.gtu.drivers_assignment_management_service.infrastructure.persistence.repository;

import com.gtu.drivers_assignment_management_service.domain.model.DriverAssignment;
import com.gtu.drivers_assignment_management_service.infrastructure.persistence.entity.DriverAssignmentEntity;
import com.gtu.drivers_assignment_management_service.infrastructure.persistence.mapper.DriverAssignmentEntityMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DriverAssignmentRepositoryImplTest {

    private JpaDriverAssignmentRepository jpaRepository;
    private DriverAssignmentRepositoryImpl repository;

    @BeforeEach
    void setUp() {
        jpaRepository = mock(JpaDriverAssignmentRepository.class);
        repository = new DriverAssignmentRepositoryImpl(jpaRepository);
    }

    @Test
    void save_shouldReturnSavedDriverAssignment() {
        DriverAssignment domain = new DriverAssignment(1L, 1L, 100L, 1L, 2L, 3L); // ejemplo
        DriverAssignmentEntity entity = DriverAssignmentEntityMapper.toEntity(domain);

        when(jpaRepository.save(any())).thenReturn(entity);

        DriverAssignment result = repository.save(domain);

        assertNotNull(result);
        assertEquals(domain.getDriverId(), result.getDriverId());
        verify(jpaRepository, times(1)).save(any(DriverAssignmentEntity.class));
    }

    @Test
    void findAll_shouldReturnListOfDriverAssignments() {
        DriverAssignmentEntity entity = new DriverAssignmentEntity(1L, 1L, 100L, 1L, 2L, 3L);
        when(jpaRepository.findAll()).thenReturn(List.of(entity));

        List<DriverAssignment> result = repository.findAll();

        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getDriverId());
    }

    @Test
    void deleteById_shouldCallJpaRepositoryDelete() {
        repository.deleteById(1L);
        verify(jpaRepository, times(1)).deleteById(1L);
    }

    @Test
    void findById_shouldReturnOptionalDriverAssignment() {
        DriverAssignmentEntity entity = new DriverAssignmentEntity(1L, 1L, 100L, 1L, 2L, 3L);
        when(jpaRepository.findById(1L)).thenReturn(Optional.of(entity));

        Optional<DriverAssignment> result = repository.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getDriverId());
    }

    @Test
    void update_shouldSaveEntity() {
        DriverAssignment domain = new DriverAssignment(1L, 1L, 100L, 1L, 2L, 3L);

        repository.update(domain);

        verify(jpaRepository, times(1)).save(any(DriverAssignmentEntity.class));
    }

    @Test
    void getDriverAssignmentByDriverId_shouldReturnOptional() {
        DriverAssignmentEntity entity = new DriverAssignmentEntity(1L, 1L, 100L, 1L, 2L, 3L);
        when(jpaRepository.findByDriverId(100L)).thenReturn(Optional.of(entity));

        Optional<DriverAssignment> result = repository.getDriverAssignmentByDriverId(100L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getDriverId());
    }
}
