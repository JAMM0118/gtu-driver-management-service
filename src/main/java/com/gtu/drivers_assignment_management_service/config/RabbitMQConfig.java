package com.gtu.drivers_assignment_management_service.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String DRIVER_QUEUE = "drivers.queue";
    public static final String DRIVER_EXCHANGE = "drivers.exchange";
    public static final String DRIVER_ROUTING_KEY = "drivers.routingkey";


    @Bean
    public Queue driverQueue() {
        return new Queue(DRIVER_QUEUE, true);
    }

    @Bean
    public DirectExchange driverExchange() {
        return new DirectExchange(DRIVER_EXCHANGE, true, false);
    }

    @Bean
    public Binding driverBinding(Queue driverQueue, DirectExchange driverExchange) {
        return BindingBuilder.bind(driverQueue).to(driverExchange).with(DRIVER_ROUTING_KEY);
    }
}