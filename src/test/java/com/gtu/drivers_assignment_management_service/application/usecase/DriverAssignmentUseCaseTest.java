/* 
package com.gtu.drivers_assignment_management_service.application.usecase;

import com.gtu.drivers_assignment_management_service.application.dto.DriversAssignmentDTO;
import com.gtu.drivers_assignment_management_service.domain.model.DriverAssignment;
import com.gtu.drivers_assignment_management_service.domain.service.DriversAssignmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DriverAssignmentUseCaseTest {
    @Mock
    private DriversAssignmentService driversAssignmentService;

    @InjectMocks
    private DriversAssignmentUseCase driversAssignmentUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void assignDriver_shouldReturnSavedDriverDTO() {
        DriversAssignmentDTO dto = new DriversAssignmentDTO(1L, 101, "Ruta 1", Arrays.asList(10L, 20L));
        DriverAssignment domain = new DriverAssignment(1L, 101, "Ruta 1", Arrays.asList(10L, 20L));
        when(driversAssignmentService.saveDriver(any())).thenReturn(domain);
        DriversAssignmentDTO result = driversAssignmentUseCase.assignDriver(dto);
        assertEquals(dto.getId(), result.getId());
        assertEquals(dto.getRouteId(), result.getRouteId());
        verify(driversAssignmentService).validateDriver(any());
        verify(driversAssignmentService).saveDriver(any());
    }

    @Test
    void getAllAssignedDrivers_shouldReturnListOfDTOs() {
        DriverAssignment domain = new DriverAssignment(1L, 101, "Ruta 1", Collections.singletonList(10L));
        when(driversAssignmentService.getAllAssignedDrivers()).thenReturn(Collections.singletonList(domain));
        List<DriversAssignmentDTO> result = driversAssignmentUseCase.getAllAssignedDrivers();
        assertEquals(1, result.size());
        assertEquals(domain.getId(), result.get(0).getId());
    }

    @Test
    void updateADriversAssignment_shouldReturnUpdatedDTO() {
        DriversAssignmentDTO dto = new DriversAssignmentDTO(2L, 202, "Ruta 2", Arrays.asList(30L));
        DriverAssignment domain = new DriverAssignment(2L, 202, "Ruta 2", Arrays.asList(30L));
        when(driversAssignmentService.updateADriversAssignment(any())).thenReturn(domain);
        DriversAssignmentDTO result = driversAssignmentUseCase.updateADriversAssignment(2L, dto);
        assertEquals(dto.getId(), result.getId());
        assertEquals(dto.getRouteName(), result.getRouteName());
    }

    @Test
    void deleteADriverAssignment_shouldCallService() {
        Long id = 5L;
        driversAssignmentUseCase.deleteADriverAssignment(id);
        verify(driversAssignmentService).deleteADriverAssignment(id);
    }
}
*/