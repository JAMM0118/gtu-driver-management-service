package com.gtu.drivers_assignment_management_service.application.service;

import com.gtu.drivers_assignment_management_service.domain.model.DriverAssignment;
import com.gtu.drivers_assignment_management_service.domain.model.Route;
import com.gtu.drivers_assignment_management_service.domain.model.Stop;
import com.gtu.drivers_assignment_management_service.domain.repository.DriverAssignmentRepository;
import com.gtu.drivers_assignment_management_service.domain.service.RouteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;





class DriversAssignmentServiceImplTest {

    private DriverAssignmentRepository driversAssignmentRepository;
    private RouteService routeService;
    private DriversAssignmentServiceImpl driversAssignmentService;

    @BeforeEach
    void setUp() {
        driversAssignmentRepository = mock(DriverAssignmentRepository.class);
        routeService = mock(RouteService.class);
        driversAssignmentService = new DriversAssignmentServiceImpl(driversAssignmentRepository, routeService);
    }

    @Test
    void assignmentDriver_shouldAssignDriverToRoute() {
        Long driverId = 1L;
        Long routeId = 100L;
        List<Long> stops = Arrays.asList(10L, 20L, 30L);
        Route route = new Route(routeId, "Route 1", stops);

        when(routeService.getRouteById(routeId)).thenReturn(route);
        when(driversAssignmentRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        DriverAssignment assignment = driversAssignmentService.assignmentDriver(driverId, routeId);

        assertEquals(driverId, assignment.getDriverId());
        assertEquals(routeId, assignment.getRouteId());
        assertNull(assignment.getCurrentStopId());
        assertNull(assignment.getLatestStopId());
        assertEquals(stops.get(0), assignment.getNextStopId());
        verify(driversAssignmentRepository).save(any(DriverAssignment.class));
    }

    @Test
    void assignmentDriver_shouldThrowIfRouteNotFound() {
        when(routeService.getRouteById(anyLong())).thenReturn(null);
        assertThrows(IllegalArgumentException.class, () -> driversAssignmentService.assignmentDriver(1L, 2L));
    }

    @Test
    void assignmentDriver_shouldThrowIfRouteHasNoStops() {
        Route route = new Route(1L, "Empty Route", Collections.emptyList());
        when(routeService.getRouteById(anyLong())).thenReturn(route);
        assertThrows(IllegalArgumentException.class, () -> driversAssignmentService.assignmentDriver(1L, 1L));
    }

    @Test
    void updateCurrentStopId_shouldUpdateAssignmentAtStop() {
        Long driverId = 1L;
        Long routeId = 100L;
        Long stopId = 10L;
        List<Long> stops = Arrays.asList(stopId, 20L);
        Route route = new Route(routeId, "Route", stops);

        DriverAssignment assignment = new DriverAssignment();
        assignment.setDriverId(driverId);
        assignment.setRouteId(routeId);

        Stop stop = new Stop(stopId, "Stop 1", 10.0, 20.0);

        when(driversAssignmentRepository.findAll()).thenReturn(List.of(assignment));
        when(routeService.getRouteById(routeId)).thenReturn(route);
        when(routeService.getStopById(stopId)).thenReturn(stop);

        driversAssignmentService.updateCurrentStopId(driverId, 10.0, 20.0);

        verify(driversAssignmentRepository).update(any(DriverAssignment.class));

        driversAssignmentService.updateCurrentStopId(driverId, 10.0, 20.0);
        assertEquals(stopId, assignment.getCurrentStopId());
        assertNull(assignment.getLatestStopId());
    }
    @Test
    void updateCurrentStopId_shouldUpdateAssignmentAtStopWithNullCurrentStop() {
        var driverAssignment = new DriverAssignment(1L, 2L, 2L, 3L, null, 4L);
        when(driversAssignmentRepository.findAll()).thenReturn(List.of(driverAssignment));
        driversAssignmentRepository.update(driverAssignment);
        var route = new Route(2L, "Test Route", List.of(3L, 4L, 5L));
        when(routeService.getRouteById(2L)).thenReturn(route);
        when(routeService.getStopById(3L)).thenReturn(new Stop(3L, "Stop 3", 50.0, 50.0));
        when(routeService.getStopById(4L)).thenReturn(new Stop(4L, "Stop 4", 20.0, 20.0));
        when(routeService.getStopById(5L)).thenReturn(new Stop(5L, "Stop 5", 0.0, 0.0));
        driversAssignmentService.updateCurrentStopId(1L, 0.0, 0.0);
        verify(driversAssignmentRepository, times(1)).update(any(DriverAssignment.class));
    }

     @Test
    void updateCurrentStopId_shouldUpdateAssignmentAtStopWithNullRoute() {
        var driverAssignment = new DriverAssignment(1L, 2L, 2L, 3L, null, 4L);
        when(driversAssignmentRepository.findAll()).thenReturn(List.of(driverAssignment));
        when(routeService.getRouteById(2L)).thenReturn(null);
        driversAssignmentService.updateCurrentStopId(1L, 0.0, 0.0);
        verify(driversAssignmentRepository, never()).update(any());
    }


    @Test
    void updateCurrentStopId_shouldNotUpdateIfNoAssignmentFound() {
        when(driversAssignmentRepository.findAll()).thenReturn(Collections.emptyList());
        driversAssignmentService.updateCurrentStopId(1L, 0.0, 0.0);
        verify(driversAssignmentRepository, never()).update(any());
    }

    @Test
    void getAllAssignedDriversByRouteId_shouldReturnAssignments() {
        DriverAssignment a1 = new DriverAssignment();
        a1.setRouteId(1L);
        DriverAssignment a2 = new DriverAssignment();
        a2.setRouteId(2L);
        when(driversAssignmentRepository.findAll()).thenReturn(List.of(a1, a2));

        List<DriverAssignment> result = driversAssignmentService.getAllAssignedDriversByRouteId(1L);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getRouteId());
    }

    @Test
    void getDriverAssignmentById_shouldReturnAssignment() {
        DriverAssignment assignment = new DriverAssignment();
        when(driversAssignmentRepository.findById(1L)).thenReturn(Optional.of(assignment));
        assertEquals(assignment, driversAssignmentService.getDriverAssignmentById(1L));
    }

    @Test
    void getDriverAssignmentById_shouldThrowIfNotFound() {
        when(driversAssignmentRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> driversAssignmentService.getDriverAssignmentById(1L));
    }

    @Test
    void deleteDriverAssignmentById_shouldDeleteAndReturnAssignment() {
        DriverAssignment assignment = new DriverAssignment();
        when(driversAssignmentRepository.findById(1L)).thenReturn(Optional.of(assignment));
        DriverAssignment result = driversAssignmentService.deleteDriverAssignmentById(1L);
        assertEquals(assignment, result);
        verify(driversAssignmentRepository).deleteById(1L);
    }

    @Test
    void deleteDriverAssignmentById_shouldThrowIfNotFound() {
        when(driversAssignmentRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> driversAssignmentService.deleteDriverAssignmentById(1L));
    }

    @Test
    void getAllAssignedDrivers_shouldReturnAllAssignments() {
        List<DriverAssignment> assignments = Arrays.asList(new DriverAssignment(), new DriverAssignment());
        when(driversAssignmentRepository.findAll()).thenReturn(assignments);
        assertEquals(assignments, driversAssignmentService.getAllAssignedDrivers());
    }

    @Test
    void getDriverAssignmentByDriverId_shouldReturnAssignment() {
        DriverAssignment assignment = new DriverAssignment();
        when(driversAssignmentRepository.getDriverAssignmentByDriverId(1L)).thenReturn(Optional.of(assignment));
        assertEquals(assignment, driversAssignmentService.getDriverAssignmentByDriverId(1L));
    }

    @Test
    void getDriverAssignmentByDriverId_shouldThrowIfNotFound() {
        when(driversAssignmentRepository.getDriverAssignmentByDriverId(1L)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> driversAssignmentService.getDriverAssignmentByDriverId(1L));
    }
}