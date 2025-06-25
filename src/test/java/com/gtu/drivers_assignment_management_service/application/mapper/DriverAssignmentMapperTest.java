package com.gtu.drivers_assignment_management_service.application.mapper;

import com.gtu.drivers_assignment_management_service.application.dto.DriversAssignmentDTO;
import com.gtu.drivers_assignment_management_service.domain.model.DriverAssignment;
import com.gtu.drivers_assignment_management_service.infrastructure.logs.LogPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class DriverAssignmentMapperTest {

    @Mock
    private LogPublisher logPublisher;

    @InjectMocks
    private DriverAssignmentMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void toDomain_shouldMapDtoToDomainAndLogChange_WhenStopIdsDiffer() {
        DriversAssignmentDTO dto = new DriversAssignmentDTO(
                1L, 101L, 1001L, 2001L, 2002L, 2003L
        );

        DriverAssignment result = mapper.toDomain(dto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(101L, result.getDriverId());
        assertEquals(1001L, result.getRouteId());
        assertEquals(2001L, result.getCurrentStopId());
        assertEquals(2002L, result.getLatestStopId());
        assertEquals(2003L, result.getNextStopId());
        verify(logPublisher).sendLog(
                anyString(),
                eq("driver-management-service"),
                eq("INFO"),
                eq("Cambio de parada detectado en mapeo DTO -> Dominio"),
                argThat(map -> map.get("driverId").equals(101L) &&
                        map.get("fromStopId").equals(2001L) &&
                        map.get("toStopId").equals(2003L))
        );
    }

    @Test
    void toDomain_shouldMapDtoToDomainWithoutLogging_WhenStopIdsAreEqual() {
        DriversAssignmentDTO dto = new DriversAssignmentDTO(
                1L, 101L, 1001L, 2001L, 2002L, 2001L
        );

        DriverAssignment result = mapper.toDomain(dto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(101L, result.getDriverId());
        assertEquals(1001L, result.getRouteId());
        assertEquals(2001L, result.getCurrentStopId());
        assertEquals(2002L, result.getLatestStopId());
        assertEquals(2001L, result.getNextStopId());
        verify(logPublisher, never()).sendLog(anyString(), anyString(), anyString(), anyString(), anyMap());
    }

    @Test
    void toDomain_shouldMapDtoToDomainWithoutLogging_WhenNextStopIdIsNull() {
        DriversAssignmentDTO dto = new DriversAssignmentDTO(
                1L, 101L, 1001L, 2001L, 2002L, null
        );

        DriverAssignment result = mapper.toDomain(dto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(101L, result.getDriverId());
        assertEquals(1001L, result.getRouteId());
        assertEquals(2001L, result.getCurrentStopId());
        assertEquals(2002L, result.getLatestStopId());
        assertNull(result.getNextStopId());
        verify(logPublisher, never()).sendLog(anyString(), anyString(), anyString(), anyString(), anyMap());
    }

    @Test
    void toDTO_shouldMapDomainToDtoAndLogInitial_WhenLatestStopIdIsNull() {
        DriverAssignment domain = new DriverAssignment(
                1L, 101L, 1001L, 2001L, null, 2003L
        );

        DriversAssignmentDTO result = mapper.toDTO(domain);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(101L, result.getDriverId());
        assertEquals(1001L, result.getRouteId());
        assertEquals(2001L, result.getCurrentStopId());
        assertNull(result.getLatestStopId());
        assertEquals(2003L, result.getNextStopId());
        verify(logPublisher).sendLog(
                anyString(),
                eq("driver-management-service"),
                eq("INFO"),
                eq("Asignación inicial detectada en mapeo Dominio -> DTO"),
                argThat(map -> map.get("driverId").equals(101L) &&
                        map.get("initialStopId").equals(2001L))
        );
    }

    @Test
    void toDTO_shouldMapDomainToDtoWithoutLogging_WhenLatestStopIdIsNotNull() {
        DriverAssignment domain = new DriverAssignment(
                1L, 101L, 1001L, 2001L, 2002L, 2003L
        );

        DriversAssignmentDTO result = mapper.toDTO(domain);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(101L, result.getDriverId());
        assertEquals(1001L, result.getRouteId());
        assertEquals(2001L, result.getCurrentStopId());
        assertEquals(2002L, result.getLatestStopId());
        assertEquals(2003L, result.getNextStopId());
        verify(logPublisher, never()).sendLog(anyString(), anyString(), anyString(), anyString(), anyMap());
    }

    @Test
    void toDTO_shouldMapDomainToDtoWithoutLogging_WhenCurrentStopIdIsNull() {
        DriverAssignment domain = new DriverAssignment(
                1L, 101L, 1001L, null, 2002L, 2003L
        );

        DriversAssignmentDTO result = mapper.toDTO(domain);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(101L, result.getDriverId());
        assertEquals(1001L, result.getRouteId());
        assertNull(result.getCurrentStopId());
        assertEquals(2002L, result.getLatestStopId());
        assertEquals(2003L, result.getNextStopId());
        verify(logPublisher, never()).sendLog(anyString(), anyString(), anyString(), anyString(), anyMap());
    }
}