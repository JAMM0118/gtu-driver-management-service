package com.gtu.drivers_assignment_management_service.infrastructure.client;


import java.util.List;


import org.springframework.stereotype.Service;

import com.gtu.drivers_assignment_management_service.domain.model.Route;
import com.gtu.drivers_assignment_management_service.domain.model.Stop;
import com.gtu.drivers_assignment_management_service.domain.service.RouteService;

@Service
public class RouteClientAdapter implements RouteService {

    private final RouteClient routeClient;

    public RouteClientAdapter(RouteClient routeClient) {
        this.routeClient = routeClient;
    }



    @Override
    public Route getRouteById(Long id) {
       var routeResponse = routeClient.getRouteById(id);
         if (routeResponse == null) {
            return null; 
         }
        var route = routeResponse.data();
        return new Route(route.id(), route.name(), route.stops());
    }

    @Override
    public List<Stop> getStopsByRouteId(Long routeId) {
        var routeResponse = routeClient.getRouteById(routeId);
        if (routeResponse == null) {
            return List.of();
        }
        var route = routeResponse.data();
        return route.stops().stream()
                .map(stopId -> {
                    var stopResponse = routeClient.getStopById(stopId);
                    if (stopResponse == null) {
                        return null; 
                    }
                    var stop = stopResponse.data();
                    return new Stop(stop.id(), stop.name(), stop.latitude(), stop.longitude());
                })
                .toList();
    }

    @Override
    public Stop getStopById(Long stopId) {
        var stopResponse = routeClient.getStopById(stopId);
        if (stopResponse == null) {
            return null; 
        }
        var stop = stopResponse.data();
        return new Stop(stop.id(), stop.name(), stop.latitude(), stop.longitude());
    }
    
}
