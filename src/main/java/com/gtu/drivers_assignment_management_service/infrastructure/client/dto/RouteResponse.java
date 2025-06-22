package com.gtu.drivers_assignment_management_service.infrastructure.client.dto;

import java.util.List;

public record RouteResponse(Long id, String name, List<Long> stops) {
}
