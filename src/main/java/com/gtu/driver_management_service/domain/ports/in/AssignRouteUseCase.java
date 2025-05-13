package com.gtu.driver_management_service.domain.ports.in;

import java.util.UUID;

public interface AssignRouteUseCase {
    void assignRouteToDriver(UUID driverId, UUID routeId);
}
