//package com.example.demo.controller;
//import com.example.demo.model.Employee;
//import com.example.demo.service.EmployeeService;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//@RestController
//@RequestMapping("/employees")
//public class EmployeeController {
//
//    @Autowired
//    private EmployeeService employeeService;
//
//    @GetMapping
//    public List<Employee> getAllEmployees() {
//        return employeeService.getAllEmployees();
//    }
//
//    @GetMapping("/{id}")
//    public Optional<Employee> getEmployeeById(@PathVariable Long id) {
//        return employeeService.getEmployeeById(id);
//    }
//
//    @PostMapping
//    public Employee saveEmployee(@RequestBody Employee employee) {
//        return employeeService.saveEmployee(employee);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteEmployee(@PathVariable Long id) {
//        employeeService.deleteEmployee(id);
//    }
//}
package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
@Tag(name = "Employee Details", description = "The endpoints that handle the management of employee details")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Operation(summary = "Get all employees", description = "Get a list of all employees", tags = {"Employee Details"})
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @Operation(summary = "Get an employee by ID", description = "Get an employee based on their ID", tags = {"Employee Details"})
    @GetMapping("/{id}")
    public Optional<Employee> getEmployeeById(
            @PathVariable @Parameter(description = "ID of the employee") Long id) {
        return employeeService.getEmployeeById(id);
    }

    @Operation(summary = "Save a new employee", description = "Save a new employee", tags = {"Employee Details"})
    @PostMapping
    public Employee saveEmployee(
            @RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @Operation(summary = "Delete an employee by ID", description = "Delete an employee based on their ID", tags = {"Employee Details"})
    @DeleteMapping("/{id}")
    public void deleteEmployee(
            @PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }
}
