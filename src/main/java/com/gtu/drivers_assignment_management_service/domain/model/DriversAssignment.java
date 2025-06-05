package com.gtu.drivers_assignment_management_service.domain.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "drivers_assignment")
public class DriversAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int routeId;
    private String routeName;
    private List<Long> driversAssignedToRoute;

    public DriversAssignment() {
    }

    public DriversAssignment(Long id, int route, String name, List<Long> driversAssignedToRoute) {
        this.id = id;
        this.routeId = route;
        this.routeName = name;
        this.driversAssignedToRoute = driversAssignedToRoute;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getRouteName() {
        return routeName;
    }
    public void setRouteName(String name) {
        this.routeName = name;
    }
    public int getRouteId() {
        return routeId;
    }
    public void setRouteId(int route) {
        this.routeId = route;
    }
    public List<Long> getDriversAssignedToRoute() {
        return driversAssignedToRoute;
    }
    public void setRouteAssigned(List<Long> driversAssignedToRoute) {
        this.driversAssignedToRoute = driversAssignedToRoute;
    }
}
