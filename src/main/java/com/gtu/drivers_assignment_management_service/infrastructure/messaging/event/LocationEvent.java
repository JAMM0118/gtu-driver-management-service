package com.gtu.drivers_assignment_management_service.infrastructure.messaging.event;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LocationEvent {
    private Long driverId;
    private double latitude;
    private double longitude;
}