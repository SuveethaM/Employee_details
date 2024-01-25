package com.example.demo.listener;
import com.example.demo.dto.EmployeeDTO;
import com.example.demo.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqListener {
    @Autowired
    private EmployeeService employeeService;

    @RabbitListener(queues = "myQueue")
    public void receiveMessage(String message) {
        System.out.println("Received message: " + message);

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
            e.printStackTrace();
            return null; // Return null or throw an exception based on your requirements
        }
    }

    private void saveEmployeeToDatabase(EmployeeDTO employeeDTO) {
        if (employeeDTO != null) {
            // Save the received employeeDTO to the database using EmployeeService
            employeeService.saveEmployee(employeeDTO);
            System.out.println("Saved employee to the database: " + employeeDTO.toString());
            // Add your additional processing logic here if needed
        } else {
            System.out.println("Failed to save employee to the database. EmployeeDTO is null.");
            // Handle the case where deserialization failed or employeeDTO is null
        }
    }
}
