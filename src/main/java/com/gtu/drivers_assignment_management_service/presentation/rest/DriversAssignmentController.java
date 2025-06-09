package com.gtu.drivers_assignment_management_service.presentation.rest;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.gtu.drivers_assignment_management_service.application.dto.DriversAssignmentDTO;
import com.gtu.drivers_assignment_management_service.application.dto.ResponseDTO;
import com.gtu.drivers_assignment_management_service.application.usecase.DriversAssignmentUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/drivers-assignment")
@Tag(name = "Drivers Assignment Management", description = "APIs for managing drivers assignment")
@CrossOrigin(origins = "*")
public class DriversAssignmentController {
    private final DriversAssignmentUseCase driversAssignmentUseCase;

    public DriversAssignmentController(DriversAssignmentUseCase driversAssignmentUseCase) {
        this.driversAssignmentUseCase = driversAssignmentUseCase;

    }

    @PostMapping
    @Operation(summary = "Assign a driver to a route", description = "Assign a driver to a specific route")
    public ResponseEntity<ResponseDTO<DriversAssignmentDTO>> createDriver(@Valid @RequestBody DriversAssignmentDTO driversAssignmentDTO) {
        DriversAssignmentDTO assignDriver = driversAssignmentUseCase.assignDriver(driversAssignmentDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseDTO<>(
                        "Driver assigned successfully",
                        assignDriver,
                        201));
    }

    @GetMapping
    @Operation(summary = "Get all assigned drivers", description = "Retrieve all drivers assigned to routes")
    public ResponseEntity<ResponseDTO<List<DriversAssignmentDTO>>> getAllAssignedDrivers() {
        return ResponseEntity.ok(
                new ResponseDTO<>(
                        "Drivers assigned retrieved successfully",
                        driversAssignmentUseCase.getAllAssignedDrivers(),
                        200));
    }


    @PutMapping("/{id}")
    @Operation(summary = "Update driver assignment", description = "Update the assignment of a driver to a route")
    public ResponseEntity<ResponseDTO<DriversAssignmentDTO>> updateDriverAssignment(
            @PathVariable Long id,
            @Valid @RequestBody DriversAssignmentDTO driversAssignmentDTO) {
        DriversAssignmentDTO updatedDriver = driversAssignmentUseCase.updateADriversAssignment(id, driversAssignmentDTO);
        return ResponseEntity.ok(
                new ResponseDTO<>(
                        "Driver assignment updated successfully",
                        updatedDriver,
                        200));
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Delete driver assignment", description = "Delete a driver assignment by ID")
    public ResponseEntity<ResponseDTO<Void>> deleteDriverAssignment(@PathVariable Long id) {
        driversAssignmentUseCase.deleteADriverAssignment(id);
        return ResponseEntity.ok(
                new ResponseDTO<>(
                        "Driver assignment deleted successfully",
                        null,
                        200));
    }
}
