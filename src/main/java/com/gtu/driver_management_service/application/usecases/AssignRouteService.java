package com.gtu.driver_management_service.application.usecases;

import com.gtu.driver_management_service.domain.ports.in.AssignRouteUseCase;
import com.gtu.driver_management_service.domain.ports.out.RouteAssignmentRepository;
import com.gtu.driver_management_service.domain.model.RouteAssignment;

import java.time.LocalDateTime;
import java.util.UUID;

public class AssignRouteService implements AssignRouteUseCase {

    private final RouteAssignmentRepository repository;

    public AssignRouteService(RouteAssignmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public void assignRouteToDriver(UUID driverId, UUID routeId) {
        // lógica para validar que driverId y routeId existan en sus microservicios
        RouteAssignment assignment = new RouteAssignment(UUID.randomUUID(), driverId, routeId, LocalDateTime.now());
        repository.save(assignment);
    }
}
