package com.example.demo.listener;
import com.example.demo.dto.EmployeeDTO;
import com.example.demo.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqListener.class);

    private final EmployeeService employeeService;
    public RabbitMqListener(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @RabbitListener(queues = "myQueue")
    public void receiveMessage(String message) {
        LOGGER.info("Received message: {}", message);

        // Convert JSON message to EmployeeDTO
        EmployeeDTO receivedEmployee = convertJsonToEmployeeDTO(message);

        // Process the received EmployeeDTO by saving it to the database
        saveEmployeeToDatabase(receivedEmployee);
    }

    public static EmployeeDTO convertJsonToEmployeeDTO(String json) {
        try {
            // Use Jackson ObjectMapper to convert JSON to EmployeeDTO
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, EmployeeDTO.class);
        } catch (Exception e) {
            // Handle the exception appropriately (e.g., log it)
            LOGGER.error("Failed to convert JSON to EmployeeDTO", e);
            return null; // Return null or throw an exception based on your requirements
        }
    }

    private void saveEmployeeToDatabase(EmployeeDTO employeeDTO) {
        if (employeeDTO != null) {
            // Save the received employeeDTO to the database using EmployeeService
            employeeService.saveEmployee(employeeDTO);
            LOGGER.info("Saved employee to the database: {}", employeeDTO);
            // Add your additional processing logic here if needed
        } else {
            LOGGER.error("Failed to save employee to the database. EmployeeDTO is null.");
            // Handle the case where deserialization failed or employeeDTO is null
        }
    }

}
