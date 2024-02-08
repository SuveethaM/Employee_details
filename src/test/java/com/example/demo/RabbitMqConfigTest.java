package com.example.demo;
// RabbitMqConfigMvcTest.java
import com.example.demo.config.RabbitMqConfig;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
class RabbitMqConfigTest {

    @Test
    void testQueueCreation() {
        RabbitTemplate rabbitTemplate = mock(RabbitTemplate.class);
        RabbitMqConfig rabbitMqConfig = new RabbitMqConfig(rabbitTemplate);

        Queue queue = rabbitMqConfig.queue();
        assertNotNull(queue, "Queue should not be null");
        assertEquals("myQueue", queue.getName(), "Unexpected queue name");
        // Add assertions as needed
    }

    @Test
    void testDirectExchangeCreation() {
        RabbitTemplate rabbitTemplate = mock(RabbitTemplate.class);
        RabbitMqConfig rabbitMqConfig = new RabbitMqConfig(rabbitTemplate);

        DirectExchange directExchange = rabbitMqConfig.directExchange();
        assertNotNull(directExchange, "DirectExchange should not be null");
        assertEquals("myDirectExchange" +
                "", directExchange.getName(), "Unexpected exchange name");

        // Add assertions as needed
    }

    @Test
    void testBindingCreation() {
        RabbitTemplate rabbitTemplate = mock(RabbitTemplate.class);
        RabbitMqConfig rabbitMqConfig = new RabbitMqConfig(rabbitTemplate);

        Queue queue = rabbitMqConfig.queue();
        DirectExchange directExchange = rabbitMqConfig.directExchange();
        Binding binding = rabbitMqConfig.binding(queue, directExchange);
        assertNotNull(binding, "Binding should not be null");

        // Add assertions as needed
    }
}


