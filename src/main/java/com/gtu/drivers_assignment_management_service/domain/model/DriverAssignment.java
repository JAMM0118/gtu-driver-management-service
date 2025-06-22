package com.gtu.drivers_assignment_management_service.domain.model;


public class DriverAssignment {
    private Long id;

    private Long driverId;

    private Long routeId;

    private Long currentStopId;

    private Long latestStopId;

    private Long nextStopId;

    public DriverAssignment() {
    }

    public DriverAssignment(Long id, Long driverId, Long routeId, Long currentStopId, Long latestStopId, Long nextStopId) {
        this.id = id;
        this.driverId = driverId;
        this.routeId = routeId;
        this.currentStopId = currentStopId;
        this.latestStopId = latestStopId;
        this.nextStopId = nextStopId;
    }
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    public Long getDriverId() {
        return driverId;
    }
    
    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }   

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }
    
    public Long getCurrentStopId() {
        return currentStopId;
    }
    
    public void setCurrentStopId(Long currentStopId) {
        this.currentStopId = currentStopId;
    }   

    public Long getLatestStopId() {
        return latestStopId;
    }
    
    public void setLatestStopId(Long latestStopId) {
        this.latestStopId = latestStopId;
    }
    public Long getNextStopId() {
        return nextStopId;
    }
    
    public void setNextStopId(Long nextStopId) {
        this.nextStopId = nextStopId;
    }
}
