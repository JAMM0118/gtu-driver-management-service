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

    private int driverId;
    private String name;
    private List<Long> routesAssigned;

    public DriversAssignment() {
    }

    public DriversAssignment(Long id, int driverId, String name, List<Long> routesAssigned) {
        this.id = id;
        this.driverId = driverId;
        this.name = name;
        this.routesAssigned = routesAssigned;
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
    public int getDriverId() {
        return driverId;
    }
    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }
    public List<Long> getRoutesAssigned() {
        return routesAssigned;
    }
    public void setRouteAssigned(List<Long> routesAssigned) {
        this.routesAssigned = routesAssigned;
    }
}
