package com.gtu.drivers_assignment_management_service.infrastructure.messaging.event;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LocationEvent {
    private Long driverId;
    private double latitude;
    private double longitude;
}