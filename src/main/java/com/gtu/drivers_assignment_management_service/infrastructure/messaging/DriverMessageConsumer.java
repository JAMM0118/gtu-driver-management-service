package com.gtu.drivers_assignment_management_service.infrastructure.messaging;

import java.util.logging.Logger;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gtu.drivers_assignment_management_service.config.RabbitMQConfig;
import com.gtu.drivers_assignment_management_service.domain.service.DriversAssignmentService;
import com.gtu.drivers_assignment_management_service.infrastructure.messaging.event.LocationEvent;


@Component
public class DriverMessageConsumer {

    private final ObjectMapper objectMapper;
    private final Logger logger;
    private final DriversAssignmentService driversAssignmentService;

    public DriverMessageConsumer(DriversAssignmentService driversAssignmentService) {
        this.objectMapper = new ObjectMapper();
        this.logger = Logger.getLogger(DriverMessageConsumer.class.getName());
        this.driversAssignmentService = driversAssignmentService;
    }

    @RabbitListener(queues = RabbitMQConfig.DRIVER_QUEUE)
    public void receiveMessage(String message) {
        try {
            LocationEvent event = objectMapper.readValue(message, LocationEvent.class);
            driversAssignmentService.updateCurrentStopId(event.getDriverId(), event.getLatitude(), event.getLongitude());
        } catch (Exception e) {
            logger.severe("Error processing message: " + e.getMessage());
        }
    }

}
