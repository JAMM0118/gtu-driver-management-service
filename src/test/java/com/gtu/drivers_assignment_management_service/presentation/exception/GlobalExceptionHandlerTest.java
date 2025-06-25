package com.gtu.drivers_assignment_management_service.presentation.exception;

import com.gtu.drivers_assignment_management_service.application.dto.ErrorResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import com.gtu.drivers_assignment_management_service.infrastructure.logs.LogPublisher;

class GlobalExceptionHandlerTest {

    LogPublisher logPublisher = mock(LogPublisher.class);
    private final GlobalExceptionHandler handler = new GlobalExceptionHandler(logPublisher);

    @Test
    void handleValidationExceptions_shouldReturnBadRequestWithFieldErrorMessage() {
        BindingResult bindingResult = mock(BindingResult.class);
        FieldError fieldError = new FieldError("object", "field", "must not be null");
        when(bindingResult.getFieldError()).thenReturn(fieldError);

        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        when(ex.getBindingResult()).thenReturn(bindingResult);

        ResponseEntity<ErrorResponseDTO> response = handler.handleValidationExceptions(ex);

        assertEquals(400, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals("Validation Error", response.getBody().getMessage());
        assertEquals("must not be null", response.getBody().getDetails());
        verify(logPublisher).sendLog(anyString(), anyString(), anyString(), eq("Validation Error"), anyMap());
    }

    @Test
    void handleValidationExceptions_shouldReturnBadRequestWithDefaultMessageWhenNoFieldError() {
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.getFieldError()).thenReturn(null);

        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        when(ex.getBindingResult()).thenReturn(bindingResult);

        ResponseEntity<ErrorResponseDTO> response = handler.handleValidationExceptions(ex);

        assertEquals(400, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals("Validation Error", response.getBody().getMessage());
        assertEquals("Invalid request data", response.getBody().getDetails());    
    }

    @Test
    void handleGeneralExceptions_shouldReturnInternalServerError() {
        Exception ex = new Exception("Something went wrong");

        ResponseEntity<ErrorResponseDTO> response = handler.handleGeneralExceptions(ex);

        assertEquals(500, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals("Internal Server Error", response.getBody().getMessage());
        assertEquals("Something went wrong", response.getBody().getDetails());
        verify(logPublisher).sendLog(anyString(), anyString(), anyString(), eq("Internal Server Error"), anyMap());
    }

    @Test
    void handleIllegalArgumentException_shouldReturnBadRequest() {
        IllegalArgumentException ex = new IllegalArgumentException("Invalid argument");

        ResponseEntity<ErrorResponseDTO> response = handler.handleIllegalArgumentException(ex);

        assertEquals(400, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals("Invalid Argument", response.getBody().getMessage());
        assertEquals("Invalid argument", response.getBody().getDetails());
        verify(logPublisher).sendLog(anyString(), anyString(), anyString(), eq("Invalid Argument"), anyMap());
    }

    @Test
    void handleValidationExceptions_shouldLogCorrectly() {
        BindingResult bindingResult = mock(BindingResult.class);
        FieldError fieldError = new FieldError("object", "field", "must not be null");
        when(bindingResult.getFieldError()).thenReturn(fieldError);

        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        when(ex.getBindingResult()).thenReturn(bindingResult);

        handler.handleValidationExceptions(ex);

        verify(logPublisher).sendLog(anyString(), anyString(), anyString(), eq("Validation Error"),
                argThat(map -> map.containsValue("must not be null")));
    }

}