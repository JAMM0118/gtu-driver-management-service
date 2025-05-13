package com.gtu.driver_management_service.domain.model;

import java.util.UUID;
import java.time.LocalDateTime;

public class RouteAssignment {
    private UUID id;
    private UUID driverId;
    private UUID routeId;
    private LocalDateTime assignedAt;

    public RouteAssignment(UUID id, UUID driverId, UUID routeId, LocalDateTime assignedAt) {
        this.id = id;
        this.driverId = driverId;
        this.routeId = routeId;
        this.assignedAt = assignedAt;
    }


    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public UUID getDriverId() {
        return driverId;
    }
    public void setDriverId(UUID driverId) {
        this.driverId = driverId;
    }
    public UUID getRouteId() {
        return routeId;
    }
    public void setRouteId(UUID routeId) {
        this.routeId = routeId;
    }
    public LocalDateTime getAssignedAt() {
        return assignedAt;
    }
    public void setAssignedAt(LocalDateTime assignedAt) {
        this.assignedAt = assignedAt;
    }
}
