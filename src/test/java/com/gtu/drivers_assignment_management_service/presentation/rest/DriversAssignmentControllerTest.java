package com.gtu.drivers_assignment_management_service.presentation.rest;

import com.gtu.drivers_assignment_management_service.application.dto.DriversAssignmentDTO;
import com.gtu.drivers_assignment_management_service.application.usecase.DriversAssignmentUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
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
import com.gtu.drivers_assignment_management_service.infrastructure.logs.LogPublisher;

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
                LogPublisher logPublisher = mock(LogPublisher.class);
                mockMvc = MockMvcBuilders.standaloneSetup(controller)
                                .setControllerAdvice(new GlobalExceptionHandler(logPublisher))
                                .setMessageConverters(new MappingJackson2HttpMessageConverter(objectMapper))
                                .build();
                dto = new DriversAssignmentDTO(100L, 1L, 101L, 2L, 3L, 4L);
        }

        @Test
        void createDriver_shouldReturnCreatedResponse() throws Exception {
                when(driversAssignmentUseCase.assignDriverToRoute(any())).thenReturn(dto);

                mockMvc.perform(post("/assignments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.message").value("Driver assigned successfully"))
                        .andExpect(jsonPath("$.data.id").value(dto.getId()))
                        .andExpect(jsonPath("$.data.routeId").value(dto.getRouteId()))
                        .andExpect(jsonPath("$.data.driverId").value(dto.getDriverId()));
                verify(driversAssignmentUseCase, times(1)).assignDriverToRoute(any());
        }

        @Test
        void createDriver_shouldReturnBadRequestOnValidationError() throws Exception {
                DriversAssignmentDTO invalidDto = new DriversAssignmentDTO(1L, null, null, 2L, null, null);
                when(driversAssignmentUseCase.assignDriverToRoute(any()))
                        .thenThrow(new IllegalArgumentException("Validation Error"));
                mockMvc.perform(post("/assignments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidDto)))
                        .andExpect(status().isBadRequest());
        }

        @Test
        void createDriver_shouldReturnBadRequestOnServiceException() throws Exception {
                when(driversAssignmentUseCase.assignDriverToRoute(any()))
                        .thenThrow(new IllegalArgumentException("Invalid assignment"));
                mockMvc.perform(post("/assignments")
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

                mockMvc.perform(get("/assignments"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.message").value("Drivers assigned retrieved successfully"))
                        .andExpect(jsonPath("$.data[0].id").value(dto.getId()))
                        .andExpect(jsonPath("$.data[0].routeId").value(dto.getRouteId()));
                verify(driversAssignmentUseCase, times(1)).getAllAssignedDrivers();
        }

        @Test
        void getAllAssignedDrivers_shouldReturnEmptyList() throws Exception {
                when(driversAssignmentUseCase.getAllAssignedDrivers()).thenReturn(Collections.emptyList());

                mockMvc.perform(get("/assignments"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.message").value("Drivers assigned retrieved successfully"))
                        .andExpect(jsonPath("$.data").isEmpty());
                verify(driversAssignmentUseCase, times(1)).getAllAssignedDrivers();
        }

        @Test
        void getAllAssignedDriversByRouteId_shouldReturnOkResponse() throws Exception {
                List<DriversAssignmentDTO> dtos = Collections.singletonList(dto);
                when(driversAssignmentUseCase.getAllAssignedDriversByRouteId(1L)).thenReturn(dtos);

                mockMvc.perform(get("/assignments/route/1"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.message").value("Drivers assigned to route retrieved successfully"))
                        .andExpect(jsonPath("$.data[0].id").value(dto.getId()))
                        .andExpect(jsonPath("$.data[0].routeId").value(dto.getRouteId()));
                verify(driversAssignmentUseCase, times(1)).getAllAssignedDriversByRouteId(1L);
        }

        @Test
        void getAllAssignedDriversByRouteId_shouldReturnEmptyList() throws Exception {
                when(driversAssignmentUseCase.getAllAssignedDriversByRouteId(1L)).thenReturn(Collections.emptyList());

                mockMvc.perform(get("/assignments/route/1"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.message").value("Drivers assigned to route retrieved successfully"))
                        .andExpect(jsonPath("$.data").isEmpty());
                verify(driversAssignmentUseCase, times(1)).getAllAssignedDriversByRouteId(1L);
        }

        @Test
        void getAllAssignedDriversByRouteId_shouldReturnBadRequestOnException() throws Exception {
                when(driversAssignmentUseCase.getAllAssignedDriversByRouteId(1L))
                        .thenThrow(new IllegalArgumentException("Route not found"));

                mockMvc.perform(get("/assignments/route/1"))
                        .andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.message").value("Invalid Argument"))
                        .andExpect(jsonPath("$.details").value("Route not found"));
                verify(driversAssignmentUseCase, times(1)).getAllAssignedDriversByRouteId(1L);
        }

        @Test
        void getDriverAssignmentByDriverId_shouldReturnOkResponse() throws Exception {
                when(driversAssignmentUseCase.getDriverAssignmentByDriverId(2L)).thenReturn(dto);

                mockMvc.perform(get("/assignments/driver/2"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.message").value("Driver assignment retrieved successfully"))
                        .andExpect(jsonPath("$.data.id").value(dto.getId()))
                        .andExpect(jsonPath("$.data.driverId").value(dto.getDriverId()));
                verify(driversAssignmentUseCase, times(1)).getDriverAssignmentByDriverId(2L);
        }

        @Test
        void getDriverAssignmentByDriverId_shouldReturnBadRequestOnException() throws Exception {
                when(driversAssignmentUseCase.getDriverAssignmentByDriverId(2L))
                        .thenThrow(new IllegalArgumentException("Driver not found"));

                mockMvc.perform(get("/assignments/driver/2"))
                        .andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.message").value("Invalid Argument"))
                        .andExpect(jsonPath("$.details").value("Driver not found"));
                verify(driversAssignmentUseCase, times(1)).getDriverAssignmentByDriverId(2L);
        }

        @Test
        void getDriverAssignmentById_shouldReturnOkResponse() throws Exception {
                when(driversAssignmentUseCase.getDriverAssignmentById(100L)).thenReturn(dto);

                mockMvc.perform(get("/assignments/100"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.message").value("Driver assignment retrieved successfully"))
                        .andExpect(jsonPath("$.data.id").value(dto.getId()))
                        .andExpect(jsonPath("$.data.driverId").value(dto.getDriverId()));
                verify(driversAssignmentUseCase, times(1)).getDriverAssignmentById(100L);
        }

        @Test
        void getDriverAssignmentById_shouldReturnBadRequestOnException() throws Exception {
                when(driversAssignmentUseCase.getDriverAssignmentById(100L))
                        .thenThrow(new IllegalArgumentException("Assignment not found"));

                mockMvc.perform(get("/assignments/100"))
                        .andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.message").value("Invalid Argument"))
                        .andExpect(jsonPath("$.details").value("Assignment not found"));
                verify(driversAssignmentUseCase, times(1)).getDriverAssignmentById(100L);
        }

        @Test
        void deleteDriverAssignment_shouldReturnOkResponse() throws Exception {
                doNothing().when(driversAssignmentUseCase).deleteADriverAssignment(5L);

                mockMvc.perform(delete("/assignments/5"))
                        .andExpect(status().isNoContent())
                        .andExpect(jsonPath("$.message").value("Driver assignment deleted successfully"))
                        .andExpect(jsonPath("$.data").doesNotExist());
                verify(driversAssignmentUseCase, times(1)).deleteADriverAssignment(5L);
        }

        @Test
        void deleteDriverAssignment_shouldReturnBadRequestOnServiceException() throws Exception {
                doThrow(new IllegalArgumentException("Assignment does not exist")).when(driversAssignmentUseCase)
                        .deleteADriverAssignment(5L);

                mockMvc.perform(delete("/assignments/5"))
                        .andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.message").value("Invalid Argument"))
                        .andExpect(jsonPath("$.details").value("Assignment does not exist"));
                verify(driversAssignmentUseCase, times(1)).deleteADriverAssignment(5L);
        }
}