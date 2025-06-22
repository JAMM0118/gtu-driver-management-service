package com.gtu.drivers_assignment_management_service.domain.service;


import java.util.List;

import com.gtu.drivers_assignment_management_service.domain.model.Route;
import com.gtu.drivers_assignment_management_service.domain.model.Stop;

public interface RouteService {

    Route getRouteById(Long id);
    List<Stop> getStopsByRouteId(Long routeId);
    Stop getStopById(Long stopId);
}
