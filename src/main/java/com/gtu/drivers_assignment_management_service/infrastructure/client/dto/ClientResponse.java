package com.gtu.drivers_assignment_management_service.infrastructure.client.dto;

public record ClientResponse<T>(String message, T data, int status) {
}