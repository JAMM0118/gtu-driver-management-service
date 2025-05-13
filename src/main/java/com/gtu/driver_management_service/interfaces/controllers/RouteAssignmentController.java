package com.gtu.driver_management_service.interfaces.controllers;

import com.gtu.driver_management_service.application.dto.RouteAssignmentRequest;
import com.gtu.driver_management_service.application.usecases.AssignRouteUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assignments")
public class RouteAssignmentController {

    private final AssignRouteUseCase useCase;

    public RouteAssignmentController(AssignRouteUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<Void> assignRoute(@Valid @RequestBody RouteAssignmentRequest request) {
        useCase.execute(request.getDriverId(), request.getRouteId());
        return ResponseEntity.ok().build();
    }
}
