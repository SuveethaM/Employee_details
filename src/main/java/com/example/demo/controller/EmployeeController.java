package com.example.demo.controller;
import com.example.demo.dto.EmployeeDTO;
import com.example.demo.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
@Tag(name = "Employee Details", description = "The endpoints that handle the management of employee details")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Operation(summary = "Get all employees", description = "Get a list of all employees", tags = {"Employee Details"})
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }


    @Operation(summary = "Get an employee by ID", description = "Get an employee based on their ID", tags = {"Employee Details"})
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<EmployeeDTO> getEmployeeById(
            @PathVariable @Parameter(description = "ID of the employee") Long id) {
        Optional<EmployeeDTO> employeeDTO = employeeService.getEmployeeById(id);
        return employeeDTO.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @Operation(summary = "Save a new employee", description = "Save a new employee", tags = {"Employee Details"})
    @PostMapping()
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<EmployeeDTO> saveEmployee(
            @RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO savedEmployeeDTO = employeeService.saveEmployee(employeeDTO);
        return new ResponseEntity<>(savedEmployeeDTO, HttpStatus.CREATED);
    }

    @Operation(summary = "Delete an employee by ID", description = "Delete an employee based on their ID", tags = {"Employee Details"})

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteEmployee(
            @PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @Operation(summary = "Save a new employee", description = "Save a new employee", tags = {"Employee Details"})
    @PostMapping("/rabbirmqpost")
    public ResponseEntity<EmployeeDTO> rabbitEmployee(
            @RequestBody EmployeeDTO employeeDTO) {
        // Convert savedEmployeeDTO to JSON or any format you prefer
        String message = convertEmployeeDTOToJson(employeeDTO);

        // Send the details to RabbitMQ queue
        rabbitTemplate.convertAndSend("myQueue", message);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    private String convertEmployeeDTOToJson(EmployeeDTO employeeDTO) {
        try {
            // Use Jackson ObjectMapper to convert EmployeeDTO to JSON
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(employeeDTO);
        } catch (Exception e) {
            // Handle the exception appropriately (e.g., log it)
            e.printStackTrace();
            return ""; // Return an empty string or throw an exception based on your requirements
        }
    }
}
