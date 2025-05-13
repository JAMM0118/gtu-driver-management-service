package com.gtu.driver_management_service.domain.repository;

import com.gtu.driver_management_service.domain.model.RouteAssignment;

public interface RouteAssignmentRepository {
    void save(RouteAssignment assignment);
}