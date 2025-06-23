package com.gtu.drivers_assignment_management_service.domain.model;

import java.util.List;

public class Route {
    private Long id;
    private String name;
    private List<Long> stops;
    
    public Route() {
    }

    public Route(Long id, String name, List<Long> stops) {
        this.id = id;
        this.name = name;
        this.stops = stops;
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

    public List<Long> getStops() {
        return stops;
    }
    
    public void setStops(List<Long> stops) {
        this.stops = stops;
    }
}
