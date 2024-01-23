package com.example.demo.service;
import com.example.demo.model.Employee;
import com.example.demo.dto.EmployeeDTO;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class EmployeeService {
    @Autowired
    private  EmployeeRepository employeeRepository;
    @Autowired
    private JavaMailSender javaMailSender;

    // Get all employees
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        sendEmailNotification("Display Employees", "Displaying all employees.");

        return employees.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Get employee by ID
    public Optional<EmployeeDTO> getEmployeeById(Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        sendEmailNotification("Getting Employeeby ID", "Retrieving employee by ID " );

        return employeeOptional.map(this::convertToDTO);
    }

    // Save employee
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = convertToEntity(employeeDTO);
        Employee savedEmployee = employeeRepository.save(employee);
        sendEmailNotification("New Employee Saved", "A new employee has been added. Employee Name: " + savedEmployee.getName());

        return convertToDTO(savedEmployee);
    }

    // Delete employee
    public void deleteEmployee(Long id) {

        employeeRepository.deleteById(id);
        sendEmailNotification("Employee Deleted", "Employee with ID " + id + " has been deleted.");

    }
    private void sendEmailNotification(String subject, String body) {
        // Replace with the actual recipient's email
        String emailTo = "fake@example.com";

        // Send email using JavaMailSender
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailTo);
        message.setSubject(subject);
        message.setText(body);

        javaMailSender.send(message);
    }

    private EmployeeDTO convertToDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setDepartment(employee.getDepartment());
        return employeeDTO;
    }

    private Employee convertToEntity(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setName(employeeDTO.getName());
        employee.setDepartment(employeeDTO.getDepartment());
        return employee;
    }
    }

