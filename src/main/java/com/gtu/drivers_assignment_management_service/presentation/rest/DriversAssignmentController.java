package com.gtu.drivers_assignment_management_service.presentation.rest;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.gtu.drivers_assignment_management_service.application.dto.DriversAssignmentDTO;
import com.gtu.drivers_assignment_management_service.application.dto.ResponseDTO;
import com.gtu.drivers_assignment_management_service.application.usecase.DriversAssignmentUseCase;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/drivers-assignment")
public class DriversAssignmentController {
    private final DriversAssignmentUseCase driversAssignmentUseCase;

    public DriversAssignmentController(DriversAssignmentUseCase driversAssignmentUseCase) {
        this.driversAssignmentUseCase = driversAssignmentUseCase;

    }

    @PostMapping
    public ResponseEntity<ResponseDTO<DriversAssignmentDTO>> createDriver(@Valid @RequestBody DriversAssignmentDTO driversAssignmentDTO) {
        DriversAssignmentDTO assignDriver = driversAssignmentUseCase.assignDriver(driversAssignmentDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseDTO<>(
                        "Driver assigned successfully",
                        assignDriver,
                        201));
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<DriversAssignmentDTO>>> getAllAssignedDrivers() {
        return ResponseEntity.ok(
                new ResponseDTO<>(
                        "Drivers assigned retrieved successfully",
                        driversAssignmentUseCase.getAllAssignedDrivers(),
                        200));
    }


    @PutMapping("/{id}")
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
    public ResponseEntity<ResponseDTO<Void>> deleteDriverAssignment(@PathVariable Long id) {
        driversAssignmentUseCase.deleteADriverAssignment(id);
        return ResponseEntity.ok(
                new ResponseDTO<>(
                        "Driver assignment deleted successfully",
                        null,
                        200));
    }
}
