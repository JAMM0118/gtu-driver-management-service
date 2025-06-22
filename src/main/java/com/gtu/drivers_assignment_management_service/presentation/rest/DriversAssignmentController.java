package com.gtu.drivers_assignment_management_service.presentation.rest;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.gtu.drivers_assignment_management_service.application.dto.DriversAssignmentDTO;
import com.gtu.drivers_assignment_management_service.application.dto.ResponseDTO;
import com.gtu.drivers_assignment_management_service.application.usecase.DriversAssignmentUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/assignments")
@Tag(name = "Drivers Assignment Management", description = "APIs for managing drivers assignment")
@CrossOrigin(origins = "*")
public class DriversAssignmentController {
    private final DriversAssignmentUseCase driversAssignmentUseCase;

    public DriversAssignmentController(DriversAssignmentUseCase driversAssignmentUseCase) {
        this.driversAssignmentUseCase = driversAssignmentUseCase;

    }

    @PostMapping
    @Operation(summary = "Assign a driver to a route", description = "Assign a driver to a specific route")
    public ResponseDTO<DriversAssignmentDTO> createDriver(@Valid @RequestBody DriversAssignmentDTO driversAssignmentDTO) {
        DriversAssignmentDTO assignDriver = driversAssignmentUseCase.assignDriverToRoute(driversAssignmentDTO);

        return new ResponseDTO<>(
                        "Driver assigned successfully",
                        assignDriver,
                        201);
    }


    @GetMapping("/route/{routeId}")
    @Operation(summary = "Get all assigned drivers by route ID", description = "Retrieve all drivers assigned to a specific route")
    public ResponseDTO<List<DriversAssignmentDTO>> getAllAssignedDriversByRouteId(@PathVariable Long routeId) {
        return new ResponseDTO<>(
                "Drivers assigned to route retrieved successfully",
                driversAssignmentUseCase.getAllAssignedDriversByRouteId(routeId),
                200);
    }

    @GetMapping("/driver/{driverId}")
    @Operation(summary = "Get driver assignment by driver ID", description = "Retrieve a driver assignment by driver ID")
    public ResponseDTO<DriversAssignmentDTO> getDriverAssignmentByDriverId(@PathVariable Long driverId) {
        DriversAssignmentDTO driverAssignment = driversAssignmentUseCase.getDriverAssignmentByDriverId(driverId);
        return new ResponseDTO<>(
                "Driver assignment retrieved successfully",
                driverAssignment,
                200);
    }

    @GetMapping
    @Operation(summary = "Get all assigned drivers", description = "Retrieve all drivers assigned to routes")
    public ResponseDTO<List<DriversAssignmentDTO>> getAllAssignedDrivers() {
        return 
                new ResponseDTO<>(
                        "Drivers assigned retrieved successfully",
                        driversAssignmentUseCase.getAllAssignedDrivers(),
                        200);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get driver assignment by ID", description = "Retrieve a driver assignment by its ID")
    public ResponseDTO<DriversAssignmentDTO> getDriverAssignmentById(@PathVariable Long id) {
        DriversAssignmentDTO driverAssignment = driversAssignmentUseCase.getDriverAssignmentById(id);
        return  new ResponseDTO<>(
                        "Driver assignment retrieved successfully",
                        driverAssignment,
                        200);
    }



    @DeleteMapping("/{id}")
    @Operation(summary = "Delete driver assignment", description = "Delete a driver assignment by ID")
    public ResponseDTO<Void> deleteDriverAssignment(@PathVariable Long id) {
        driversAssignmentUseCase.deleteADriverAssignment(id);
        return new ResponseDTO<>(
                "Driver assignment deleted successfully",
                null,
                200
        );
    }
}
