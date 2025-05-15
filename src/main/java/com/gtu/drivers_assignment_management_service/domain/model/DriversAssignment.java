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

    private int route;
    private String name;
    private List<Long> driversAssignedToRoute;

    public DriversAssignment() {
    }

    public DriversAssignment(Long id, int route, String name, List<Long> driversAssignedToRoute) {
        this.id = id;
        this.route = route;
        this.name = name;
        this.driversAssignedToRoute = driversAssignedToRoute;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getRoute() {
        return route;
    }
    public void setRoute(int route) {
        this.route = route;
    }
    public List<Long> getDriversAssignedToRoute() {
        return driversAssignedToRoute;
    }
    public void setRouteAssigned(List<Long> driversAssignedToRoute) {
        this.driversAssignedToRoute = driversAssignedToRoute;
    }
}
