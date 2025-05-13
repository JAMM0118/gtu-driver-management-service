/*package com.gtu.driver_management_service.assignment.domain.model;*/

import java.util.UUID;

public class RouteAssignment {
    private UUID driverId;
    private UUID routeId;

    public RouteAssignment(UUID driverId, UUID routeId) {
        this.driverId = driverId;
        this.routeId = routeId;
    }

    public UUID getDriverId() {
        return driverId;
    }

    public UUID getRouteId() {
        return routeId;
    }
}
