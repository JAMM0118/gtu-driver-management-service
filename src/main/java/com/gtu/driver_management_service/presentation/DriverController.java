package com.gtu.driver_management_service.presentation;

import com.gtu.driver_management_service.application.dto.DriverDTO;
import com.gtu.driver_management_service.application.dto.ResponseDTO;
import com.gtu.driver_management_service.application.usecase.DriverUseCase;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/drivers")
public class DriverController {
    private final DriverUseCase driverUseCase;

    public DriverController(DriverUseCase driverUseCase) {
        this.driverUseCase = driverUseCase;

    }

    @PostMapping
    public ResponseEntity<ResponseDTO<DriverDTO>> createDriver(@Valid @RequestBody DriverDTO driverDTO) {
        DriverDTO createDriver = driverUseCase.createDriver(driverDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseDTO<>(
                        "Driver created successfully",
                        createDriver,
                        201));
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<DriverDTO>>> getAllDrivers() {
        return ResponseEntity.ok(
                new ResponseDTO<>(
                        "Driver retrieved successfully",
                        driverUseCase.getAllDrivers(),
                        200));
    }
}
