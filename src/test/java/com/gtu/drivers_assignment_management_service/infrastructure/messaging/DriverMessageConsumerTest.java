package com.gtu.drivers_assignment_management_service.infrastructure.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gtu.drivers_assignment_management_service.domain.service.DriversAssignmentService;
import com.gtu.drivers_assignment_management_service.infrastructure.messaging.event.LocationEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;



class DriverMessageConsumerTest {

    private DriversAssignmentService driversAssignmentService;

    private DriverMessageConsumer driverMessageConsumer;


    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        driversAssignmentService = mock(DriversAssignmentService.class);
        driverMessageConsumer = new DriverMessageConsumer(driversAssignmentService);
    }

    @Test
    void receiveMessage_shouldCallUpdateCurrentStopId_whenMessageIsValid() throws Exception {
        LocationEvent event = new LocationEvent(123L, 45.0, 90.0);
        doNothing().when(driversAssignmentService).updateCurrentStopId(123L, 45.0, 90.0);

     
        String message = objectMapper.writeValueAsString(event);
        driverMessageConsumer.receiveMessage(message);
        verify(driversAssignmentService, times(1)).updateCurrentStopId(123L, 45.0, 90.0);
    }

    @Test
    void receiveMessage_shouldLogError_whenMessageIsInvalid() {
        String invalidMessage = "not a json";

        driverMessageConsumer.receiveMessage(invalidMessage);

        verifyNoInteractions(driversAssignmentService);
    }
}