package com.gtu.drivers_assignment_management_service.infrastructure.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.gtu.drivers_assignment_management_service.infrastructure.client.dto.ClientResponse;
import com.gtu.drivers_assignment_management_service.infrastructure.client.dto.RouteResponse;
import com.gtu.drivers_assignment_management_service.infrastructure.client.dto.StopResponse;

@FeignClient(name = "gtu-route-management-service")
public interface RouteClient {
    @GetMapping("/routes/{routeId}")
    ClientResponse<RouteResponse> getRouteById(@PathVariable("routeId")Long routeId);

    @GetMapping("/stops/{stopId}")
    ClientResponse<StopResponse> getStopById(@PathVariable("stopId") Long stopId);
}
