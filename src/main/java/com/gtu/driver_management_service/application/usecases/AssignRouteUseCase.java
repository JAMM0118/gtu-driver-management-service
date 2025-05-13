package com.gtu.driver_management_service.application.usecases;

import com.gtu.driver_management_service.domain.model.RouteAssignment;
import com.gtu.driver_management_service.domain.repository.RouteAssignmentRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AssignRouteUseCase {

    private final RouteAssignmentRepository repository;

    public AssignRouteUseCase(RouteAssignmentRepository repository) {
        this.repository = repository;
    }

    public void execute(UUID driverId, UUID routeId) {
        // Validar existencia en otros microservicios aquí
        RouteAssignment assignment = new RouteAssignment(driverId, routeId);
        repository.save(assignment);
    }
}
