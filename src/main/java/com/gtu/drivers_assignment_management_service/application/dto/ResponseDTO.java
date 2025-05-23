package com.gtu.drivers_assignment_management_service.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response DTO")
public class ResponseDTO<T> {
    
    @Schema(description = "Response message", example = "Success")
    private String message;
    @Schema(description = "Response data")
    private T data;
    @Schema(description = "Response status code", example = "200")
    private int status;

    
}
