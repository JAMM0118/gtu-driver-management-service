package com.gtu.drivers_assignment_management_service.presentation.rest;

import com.gtu.drivers_assignment_management_service.application.dto.DriversAssignmentDTO;
import com.gtu.drivers_assignment_management_service.application.dto.ResponseDTO;
import com.gtu.drivers_assignment_management_service.application.usecase.DriversAssignmentUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DriversAssignmentControllerTest {
    @Mock
    private DriversAssignmentUseCase driversAssignmentUseCase;

    @InjectMocks
    private DriversAssignmentController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createDriver_shouldReturnCreatedResponse() {
        DriversAssignmentDTO dto = new DriversAssignmentDTO(1L, 101, "Ruta 1", Arrays.asList(10L, 20L));
        when(driversAssignmentUseCase.assignDriver(any())).thenReturn(dto);
        ResponseEntity<ResponseDTO<DriversAssignmentDTO>> response = controller.createDriver(dto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Driver assigned successfully", response.getBody().getMessage());
        assertEquals(dto, response.getBody().getData());
    }

    @Test
    void getAllAssignedDrivers_shouldReturnOkResponse() {
        List<DriversAssignmentDTO> dtos = Collections.singletonList(new DriversAssignmentDTO(1L, 101, "Ruta 1", Arrays.asList(10L)));
        when(driversAssignmentUseCase.getAllAssignedDrivers()).thenReturn(dtos);
        ResponseEntity<ResponseDTO<List<DriversAssignmentDTO>>> response = controller.getAllAssignedDrivers();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Drivers assigned retrieved successfully", response.getBody().getMessage());
        assertEquals(dtos, response.getBody().getData());
    }

    @Test
    void updateDriverAssignment_shouldReturnOkResponse() {
        DriversAssignmentDTO dto = new DriversAssignmentDTO(2L, 202, "Ruta 2", Arrays.asList(30L));
        when(driversAssignmentUseCase.updateADriversAssignment(eq(2L), any())).thenReturn(dto);
        ResponseEntity<ResponseDTO<DriversAssignmentDTO>> response = controller.updateDriverAssignment(2L, dto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Driver assignment updated successfully", response.getBody().getMessage());
        assertEquals(dto, response.getBody().getData());
    }

    @Test
    void deleteDriverAssignment_shouldReturnOkResponse() {
        doNothing().when(driversAssignmentUseCase).deleteADriverAssignment(5L);
        ResponseEntity<ResponseDTO<Void>> response = controller.deleteDriverAssignment(5L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Driver assignment deleted successfully", response.getBody().getMessage());
        assertNull(response.getBody().getData());
    }
}
