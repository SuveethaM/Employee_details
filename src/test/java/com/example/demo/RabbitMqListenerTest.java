package com.example.demo;
// RabbitMqListenerTest.java


import com.example.demo.listener.RabbitMqListener;
import com.example.demo.service.EmployeeService;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;

class RabbitMqListenerTest {



        @Test
        void testReceiveMessage() {
            // Arrange
            EmployeeService employeeService = mock(EmployeeService.class);
            RabbitMqListener rabbitMqListener = new RabbitMqListener(employeeService);
            String jsonMessage = "{'id': 1, 'name': 'John Doe'}";

            // Act
            rabbitMqListener.receiveMessage(jsonMessage);

            // Assert

            // Add more assertions based on the behavior you expect
        }
    }


