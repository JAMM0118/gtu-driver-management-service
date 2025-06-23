/*
package com.gtu.drivers_assignment_management_service.presentation.rest;

import com.gtu.drivers_assignment_management_service.application.dto.DriversAssignmentDTO;
import com.gtu.drivers_assignment_management_service.application.dto.ResponseDTO;
import com.gtu.drivers_assignment_management_service.application.usecase.DriversAssignmentUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gtu.drivers_assignment_management_service.presentation.exception.GlobalExceptionHandler;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class DriversAssignmentControllerTest {

    private MockMvc mockMvc;

    @Mock
    private DriversAssignmentUseCase driversAssignmentUseCase;

    @InjectMocks
    private DriversAssignmentController controller;

    private ObjectMapper objectMapper;

    private DriversAssignmentDTO dto;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new GlobalExceptionHandler())
                .setMessageConverters(new MappingJackson2HttpMessageConverter(objectMapper))
                .build();
        dto = new DriversAssignmentDTO(1L, 101, "Ruta 1", Arrays.asList(10L, 20L));
    }

    @Test
    void createDriver_shouldReturnCreatedResponse() throws Exception {
        when(driversAssignmentUseCase.assignDriver(any())).thenReturn(dto);

        mockMvc.perform(post("/drivers-assignment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Driver assigned successfully"))
                .andExpect(jsonPath("$.data.id").value(dto.getId()))
                .andExpect(jsonPath("$.data.routeId").value(dto.getRouteId()))
                .andExpect(jsonPath("$.data.routeName").value(dto.getRouteName()))
                .andExpect(jsonPath("$.data.driversAssignedToRoute[0]").value(10L))
                .andExpect(jsonPath("$.data.driversAssignedToRoute[1]").value(20L));
        verify(driversAssignmentUseCase, times(1)).assignDriver(any());
    }

    @Test
    void createDriver_shouldReturnBadRequestOnValidationError() throws Exception {
        DriversAssignmentDTO invalidDto = new DriversAssignmentDTO(null, 0, "", Collections.emptyList());
        mockMvc.perform(post("/drivers-assignment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Validation Error"));
    }

    @Test
    void createDriver_shouldReturnBadRequestOnServiceException() throws Exception {
        when(driversAssignmentUseCase.assignDriver(any())).thenThrow(new IllegalArgumentException("Invalid assignment"));
        mockMvc.perform(post("/drivers-assignment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Invalid Argument"))
                .andExpect(jsonPath("$.details").value("Invalid assignment"));
    }

    @Test
    void getAllAssignedDrivers_shouldReturnOkResponse() throws Exception {
        List<DriversAssignmentDTO> dtos = Collections.singletonList(dto);
        when(driversAssignmentUseCase.getAllAssignedDrivers()).thenReturn(dtos);

        mockMvc.perform(get("/drivers-assignment"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Drivers assigned retrieved successfully"))
                .andExpect(jsonPath("$.data[0].id").value(dto.getId()))
                .andExpect(jsonPath("$.data[0].routeId").value(dto.getRouteId()))
                .andExpect(jsonPath("$.data[0].routeName").value(dto.getRouteName()));
        verify(driversAssignmentUseCase, times(1)).getAllAssignedDrivers();
    }

    @Test
    void updateDriverAssignment_shouldReturnOkResponse() throws Exception {
        DriversAssignmentDTO updatedDto = new DriversAssignmentDTO(2L, 202, "Ruta 2", Arrays.asList(30L));
        when(driversAssignmentUseCase.updateADriversAssignment(eq(2L), any())).thenReturn(updatedDto);

        mockMvc.perform(put("/drivers-assignment/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Driver assignment updated successfully"))
                .andExpect(jsonPath("$.data.id").value(updatedDto.getId()))
                .andExpect(jsonPath("$.data.routeId").value(updatedDto.getRouteId()))
                .andExpect(jsonPath("$.data.routeName").value(updatedDto.getRouteName()));
        verify(driversAssignmentUseCase, times(1)).updateADriversAssignment(eq(2L), any());
    }

    @Test
    void updateDriverAssignment_shouldReturnBadRequestOnServiceException() throws Exception {
        when(driversAssignmentUseCase.updateADriversAssignment(eq(2L), any()))
                .thenThrow(new IllegalArgumentException("Assignment not found"));

        mockMvc.perform(put("/drivers-assignment/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Invalid Argument"))
                .andExpect(jsonPath("$.details").value("Assignment not found"));
    }

    @Test
    void deleteDriverAssignment_shouldReturnOkResponse() throws Exception {
        doNothing().when(driversAssignmentUseCase).deleteADriverAssignment(5L);

        mockMvc.perform(delete("/drivers-assignment/5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Driver assignment deleted successfully"))
                .andExpect(jsonPath("$.data").doesNotExist());
        verify(driversAssignmentUseCase, times(1)).deleteADriverAssignment(5L);
    }

    @Test
    void deleteDriverAssignment_shouldReturnBadRequestOnServiceException() throws Exception {
        doThrow(new IllegalArgumentException("Assignment does not exist")).when(driversAssignmentUseCase).deleteADriverAssignment(5L);

        mockMvc.perform(delete("/drivers-assignment/5"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Invalid Argument"))
                .andExpect(jsonPath("$.details").value("Assignment does not exist"));
        verify(driversAssignmentUseCase, times(1)).deleteADriverAssignment(5L);
    }

    // TEST NORMALITOS

    @Test
    void createDriver_unit_shouldReturnCreatedResponse() {
        when(driversAssignmentUseCase.assignDriver(any())).thenReturn(dto);
        ResponseEntity<ResponseDTO<DriversAssignmentDTO>> response = controller.createDriver(dto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Driver assigned successfully", response.getBody().getMessage());
        assertEquals(dto, response.getBody().getData());
    }

    @Test
    void getAllAssignedDrivers_unit_shouldReturnOkResponse() {
        List<DriversAssignmentDTO> dtos = Collections.singletonList(dto);
        when(driversAssignmentUseCase.getAllAssignedDrivers()).thenReturn(dtos);
        ResponseEntity<ResponseDTO<List<DriversAssignmentDTO>>> response = controller.getAllAssignedDrivers();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Drivers assigned retrieved successfully", response.getBody().getMessage());
        assertEquals(dtos, response.getBody().getData());
    }

    @Test
    void updateDriverAssignment_unit_shouldReturnOkResponse() {
        DriversAssignmentDTO updatedDto = new DriversAssignmentDTO(2L, 202, "Ruta 2", Arrays.asList(30L));
        when(driversAssignmentUseCase.updateADriversAssignment(eq(2L), any())).thenReturn(updatedDto);
        ResponseEntity<ResponseDTO<DriversAssignmentDTO>> response = controller.updateDriverAssignment(2L, updatedDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Driver assignment updated successfully", response.getBody().getMessage());
        assertEquals(updatedDto, response.getBody().getData());
    }

    @Test
    void deleteDriverAssignment_unit_shouldReturnOkResponse() {
        doNothing().when(driversAssignmentUseCase).deleteADriverAssignment(5L);
        ResponseEntity<ResponseDTO<Void>> response = controller.deleteDriverAssignment(5L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Driver assignment deleted successfully", response.getBody().getMessage());
        assertNull(response.getBody().getData());
    }
}










*/