package com.gtu.drivers_assignment_management_service.config;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import static org.junit.jupiter.api.Assertions.*;




class RabbitMQConfigTest {

    private final RabbitMQConfig rabbitMQConfig = new RabbitMQConfig();

    @Test
    void driverQueue_shouldReturnDurableQueueWithCorrectName() {
        Queue queue = rabbitMQConfig.driverQueue();
        assertNotNull(queue);
        assertEquals(RabbitMQConfig.DRIVER_QUEUE, queue.getName());
        assertTrue(queue.isDurable());
    }

    @Test
    void driverExchange_shouldReturnDurableDirectExchangeWithCorrectName() {
        DirectExchange exchange = rabbitMQConfig.driverExchange();
        assertNotNull(exchange);
        assertEquals(RabbitMQConfig.DRIVER_EXCHANGE, exchange.getName());
        assertTrue(exchange.isDurable());
        assertFalse(exchange.isAutoDelete());
    }

    @Test
    void driverBinding_shouldBindQueueToExchangeWithCorrectRoutingKey() {
        Queue queue = rabbitMQConfig.driverQueue();
        DirectExchange exchange = rabbitMQConfig.driverExchange();
        Binding binding = rabbitMQConfig.driverBinding(queue, exchange);

        assertNotNull(binding);
        assertEquals(queue.getName(), binding.getDestination());
        assertEquals(Binding.DestinationType.QUEUE, binding.getDestinationType());
        assertEquals(exchange.getName(), binding.getExchange());
        assertEquals(RabbitMQConfig.DRIVER_ROUTING_KEY, binding.getRoutingKey());
    }
}