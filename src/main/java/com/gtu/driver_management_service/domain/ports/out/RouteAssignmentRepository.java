package com.gtu.driver_management_service.domain.ports.out;

import com.gtu.driver_management_service.domain.model.RouteAssignment;

public interface RouteAssignmentRepository {
    void save(RouteAssignment assignment);
}