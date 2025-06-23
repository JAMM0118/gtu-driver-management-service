package com.gtu.drivers_assignment_management_service.presentation.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.gtu.drivers_assignment_management_service.application.dto.ErrorResponseDTO;
import com.gtu.drivers_assignment_management_service.infrastructure.logs.LogPublisher;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final LogPublisher logPublisher;

    public GlobalExceptionHandler(LogPublisher logPublisher) {
        this.logPublisher = logPublisher;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldError().getDefaultMessage();
        ErrorResponseDTO errorResponse = new ErrorResponseDTO("Validation Error", errorMessage);

         logPublisher.sendLog(
            LocalDateTime.now().toString(),
            "gtu-driver-management-service",
            "ERROR",
            "Validation Error",
            Map.of("details", errorMessage)
        );
        
        return ResponseEntity.badRequest().body(errorResponse);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGeneralExceptions(Exception ex) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO("Internal Server Error", ex.getMessage());

        logPublisher.sendLog(
            LocalDateTime.now().toString(),
            "gtu-driver-management-service",
            "ERROR",
            "Internal Server Error",
            Map.of("details", ex.getMessage())
        );

        return ResponseEntity.status(500).body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDTO> handleIllegalArgumentException(IllegalArgumentException ex) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO("Invalid Argument", ex.getMessage());

        logPublisher.sendLog(
            LocalDateTime.now().toString(),
            "gtu-driver-management-service",
            "ERROR",
            "Invalid Argument",
            Map.of("details", ex.getMessage())
        );

        return ResponseEntity.badRequest().body(errorResponse);
    }
}
