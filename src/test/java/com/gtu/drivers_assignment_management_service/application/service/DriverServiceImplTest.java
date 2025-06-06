package com.gtu.drivers_assignment_management_service.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.gtu.drivers_assignment_management_service.domain.model.DriversAssignment;
import com.gtu.drivers_assignment_management_service.domain.repository.DriversAssignmentRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DriversAssignmentServiceImplTest {

    @Mock
    private DriversAssignmentRepository driversAssignmentRepository;

    @InjectMocks
    private DriversAssignmentServiceImpl driversAssignmentService;

    private DriversAssignment assignment;

    @BeforeEach
    void setUp() {
        assignment = new DriversAssignment();
        assignment.setId(1L);
        assignment.setName("John");
        assignment.setRoute(1);
        // assignment.setDriversAssignedToRoute(5);
    }

    @Test
    void validateDriver_Success() {
        assertDoesNotThrow(() -> driversAssignmentService.validateDriver(assignment));
    }

    @Test
    void validateDriver_NullName_ThrowsException() {
        assignment.setName(null);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            driversAssignmentService.validateDriver(assignment);
        });
        assertEquals("Driver name cannot be null or empty", ex.getMessage());
    }

    @Test
    void validateDriver_EmptyName_ThrowsException() {
        assignment.setName("");

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            driversAssignmentService.validateDriver(assignment);
        });
        assertEquals("Driver name cannot be null or empty", ex.getMessage());
    }

    @Test
    void validateDriver_InvalidRoute_ThrowsException() {
        assignment.setRoute(0);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            driversAssignmentService.validateDriver(assignment);
        });
        assertEquals("Driver ID must be greater than 0", ex.getMessage());
    }

    @Test
    void saveDriver_Success() {
        when(driversAssignmentRepository.save(assignment)).thenReturn(assignment);

        DriversAssignment result = driversAssignmentService.saveDriver(assignment);

        assertNotNull(result);
        assertEquals("John", result.getName());
        verify(driversAssignmentRepository, times(1)).save(assignment);
    }

    @Test
    void getAllAssignedDrivers_ReturnsList() {
        DriversAssignment a2 = new DriversAssignment();
        a2.setId(2L);
        a2.setName("Jane");
        a2.setRoute(2);

        List<DriversAssignment> assignments = Arrays.asList(assignment, a2);
        when(driversAssignmentRepository.findAll()).thenReturn(assignments);

        List<DriversAssignment> result = driversAssignmentService.getAllAssignedDrivers();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(assignment));
        assertTrue(result.contains(a2));
        verify(driversAssignmentRepository, times(1)).findAll();
    }

    @Test
    void getAllAssignedDrivers_EmptyList() {
        when(driversAssignmentRepository.findAll()).thenReturn(Collections.emptyList());

        List<DriversAssignment> result = driversAssignmentService.getAllAssignedDrivers();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(driversAssignmentRepository, times(1)).findAll();
    }

    @Test
    void assignDriver_Success() {
        when(driversAssignmentRepository.save(assignment)).thenReturn(assignment);

        DriversAssignment result = driversAssignmentService.assignDriver(assignment);

        assertNotNull(result);
        assertEquals("John", result.getName());
        verify(driversAssignmentRepository, times(1)).save(assignment);
    }

    @Test
    void updateADriversAssignment_Success() {
        DriversAssignment updated = new DriversAssignment();
        updated.setId(1L);
        updated.setName("Updated Name");
        updated.setRoute(2);
        updated.setRouteAssigned(Arrays.asList(3L, 4L));

        when(driversAssignmentRepository.findById(1L)).thenReturn(Optional.of(assignment));
        when(driversAssignmentRepository.save(any(DriversAssignment.class))).thenReturn(updated);

        DriversAssignment result = driversAssignmentService.updateADriversAssignment(updated);

        assertNotNull(result);
        assertEquals("Updated Name", result.getName());
        assertEquals(2, result.getRoute());
        assertEquals(Arrays.asList(3L, 4L), result.getDriversAssignedToRoute());
        verify(driversAssignmentRepository, times(1)).findById(1L);
        verify(driversAssignmentRepository, times(1)).save(any(DriversAssignment.class));
    }

    @Test
    void updateADriversAssignment_NotFound_ThrowsException() {
        DriversAssignment updated = new DriversAssignment();
        updated.setId(99L);

        when(driversAssignmentRepository.findById(99L)).thenReturn(Optional.empty());

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            driversAssignmentService.updateADriversAssignment(updated);
        });
        assertEquals("Driver assignment not found", ex.getMessage());
        verify(driversAssignmentRepository, times(1)).findById(99L);
        verify(driversAssignmentRepository, never()).save(any());
    }

    @Test
    void deleteADriverAssignment_Success() {
        driversAssignmentService.deleteADriverAssignment(10L);
        verify(driversAssignmentRepository, times(1)).deleteById(10L);
    }
}