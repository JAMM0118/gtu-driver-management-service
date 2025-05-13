package com.gtu.driver_management_service.infrastructure.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity

public class RouteAssignmentEntity {

    @Id
    private UUID id; 
    private UUID driverId;
    private UUID routeId;
    private LocalDateTime assignedAt;

    public RouteAssignmentEntity() {
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