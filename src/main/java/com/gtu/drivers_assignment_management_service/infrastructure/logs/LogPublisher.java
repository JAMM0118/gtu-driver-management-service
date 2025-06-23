package com.gtu.drivers_assignment_management_service.infrastructure.logs;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Component
public class LogPublisher {

    private final AmqpTemplate amqpTemplate;

    @Value("${rabbitmq.exchange.log}")
    private String exchange;

    @Value("${rabbitmq.routingkey.log}")
    private String routingKey;

    private final ObjectMapper objectMapper;

    public LogPublisher(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
        this.objectMapper = new ObjectMapper();
    }

    public void sendLog(String timestamp, String service, String level, String message, Map<String, Object> details) {
        try {
            Map<String, Object> log = Map.of(
                    "timestamp", timestamp,
                    "service", service,
                    "level", level,
                    "message", message,
                    "details", details);

            String logJson = objectMapper.writeValueAsString(log);
            amqpTemplate.convertAndSend(exchange, routingKey, logJson);

            System.out.println("✅ LOG SENT: " + logJson);
        } catch (Exception e) {
            System.err.println("❌ ERROR SENDING LOG: " + e.getMessage());
        }
    }

}