package com.gtu.drivers_assignment_management_service.application.usecase;

import com.gtu.drivers_assignment_management_service.application.dto.DriversAssignmentDTO;
import com.gtu.drivers_assignment_management_service.domain.model.DriverAssignment;
import com.gtu.drivers_assignment_management_service.domain.service.DriversAssignmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;





class DriversAssignmentUseCaseTest {

    private DriversAssignmentService driversAssignmentService;
    private DriversAssignmentUseCase driversAssignmentUseCase;

    @BeforeEach
    void setUp() {
        driversAssignmentService = mock(DriversAssignmentService.class);
        driversAssignmentUseCase = new DriversAssignmentUseCase(driversAssignmentService);
    }

    @Test
    void assignDriverToRoute_shouldReturnDTO() {
        DriversAssignmentDTO dto = new DriversAssignmentDTO();
        dto.setDriverId(1L);
        dto.setRouteId(2L);

        DriverAssignment assignment = new DriverAssignment();
        assignment.setId(10L);
        assignment.setDriverId(1L);
        assignment.setRouteId(2L);

        when(driversAssignmentService.assignmentDriver(1L, 2L)).thenReturn(assignment);

        DriversAssignmentDTO result = driversAssignmentUseCase.assignDriverToRoute(dto);

        assertNotNull(result);
        assertEquals(assignment.getDriverId(), result.getDriverId());
        assertEquals(assignment.getRouteId(), result.getRouteId());
    }

    @Test
    void getAllAssignedDrivers_shouldReturnListOfDTOs() {
        DriverAssignment assignment1 = new DriverAssignment();
        assignment1.setId(1L);
        assignment1.setDriverId(1L);
        assignment1.setRouteId(2L);

        DriverAssignment assignment2 = new DriverAssignment();
        assignment2.setId(2L);
        assignment2.setDriverId(3L);
        assignment2.setRouteId(4L);

        when(driversAssignmentService.getAllAssignedDrivers()).thenReturn(List.of(assignment1, assignment2));

        List<DriversAssignmentDTO> result = driversAssignmentUseCase.getAllAssignedDrivers();

        assertEquals(2, result.size());
        assertEquals(assignment1.getDriverId(), result.get(0).getDriverId());
        assertEquals(assignment2.getDriverId(), result.get(1).getDriverId());
    }

    @Test
    void getDriverAssignmentById_shouldReturnDTO() {
        DriverAssignment assignment = new DriverAssignment();
        assignment.setId(5L);
        assignment.setDriverId(6L);
        assignment.setRouteId(7L);

        when(driversAssignmentService.getDriverAssignmentById(5L)).thenReturn(assignment);

        DriversAssignmentDTO result = driversAssignmentUseCase.getDriverAssignmentById(5L);

        assertNotNull(result);
        assertEquals(assignment.getId(), result.getId());
        assertEquals(assignment.getDriverId(), result.getDriverId());
    }

    @Test
    void getAllAssignedDriversByRouteId_shouldReturnListOfDTOs() {
        DriverAssignment assignment = new DriverAssignment();
        assignment.setId(8L);
        assignment.setDriverId(9L);
        assignment.setRouteId(10L);

        when(driversAssignmentService.getAllAssignedDriversByRouteId(10L)).thenReturn(List.of(assignment));

        List<DriversAssignmentDTO> result = driversAssignmentUseCase.getAllAssignedDriversByRouteId(10L);

        assertEquals(1, result.size());
        assertEquals(assignment.getRouteId(), result.get(0).getRouteId());
    }

    @Test
    void getDriverAssignmentByDriverId_shouldReturnDTO() {
        DriverAssignment assignment = new DriverAssignment();
        assignment.setId(11L);
        assignment.setDriverId(12L);
        assignment.setRouteId(13L);

        when(driversAssignmentService.getDriverAssignmentByDriverId(12L)).thenReturn(assignment);

        DriversAssignmentDTO result = driversAssignmentUseCase.getDriverAssignmentByDriverId(12L);

        assertNotNull(result);
        assertEquals(assignment.getDriverId(), result.getDriverId());
    }

    @Test
    void deleteADriverAssignment_shouldCallService() {

        driversAssignmentUseCase.deleteADriverAssignment(15L);

        verify(driversAssignmentService, times(1)).deleteDriverAssignmentById(15L);
    }
}