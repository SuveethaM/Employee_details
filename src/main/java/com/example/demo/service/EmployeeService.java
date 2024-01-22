//package com.example.demo.service;
////package com.example.demo.service;
//
//import com.example.demo.model.EmployeeDTO;
//import com.example.demo.model.Employee;
//import com.example.demo.repository.EmployeeRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//public class EmployeeService {
//    @Autowired
//    private EmployeeRepository employeeRepository;
//
//    // Get all employees
//    public List<EmployeeDTO> getAllEmployees() {
//        List<Employee> employees = employeeRepository.findAll();
//        return employees.stream()
//                .map(this::convertToDTO)
//                .collect(Collectors.toList());
//    }
//
//    // Get employee by ID
//    public Optional<EmployeeDTO> getEmployeeById(Long id) {
//        Optional<Employee> employeeOptional = employeeRepository.findById(id);
//        return employeeOptional.map(this::convertToDTO);
//    }
//
//    // Save employee
//    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
//        Employee employee = convertToEntity(employeeDTO);
//        Employee savedEmployee = employeeRepository.save(employee);
//        return convertToDTO(savedEmployee);
//    }
//
//    // Delete employee
//    public void deleteEmployee(Long id) {
//        employeeRepository.deleteById(id);
//    }
//
//    // Helper method to convert Employee to EmployeeDTO
//    private EmployeeDTO convertToDTO(Employee employee) {
//        return new EmployeeDTO(employee.getId(), employee.getName(), employee.getDepartment());
//    }
//
//    // Helper method to convert EmployeeDTO to Employee
//    private Employee convertToEntity(EmployeeDTO employeeDTO) {
//        return new Employee(employeeDTO.getName(), employeeDTO.getDepartment());
//    }
//}
//import com.example.demo.model.EmployeeDTO;
//import com.example.demo.model.Employee;
//import com.example.demo.repository.EmployeeRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//public class EmployeeService {
//    @Autowired
//    private EmployeeRepository employeeRepository;
//
//    // Get all employees
//    public List<EmployeeDTO> getAllEmployees() {
//        List<Employee> employees = employeeRepository.findAll();
//        return employees.stream()
//                .map(this::convertToDTO)
//                .collect(Collectors.toList());
//    }
//
//    // Get employee by ID
//    public Optional<EmployeeDTO> getEmployeeById(Long id) {
//        Optional<Employee> employeeOptional = employeeRepository.findById(id);
//        return employeeOptional.map(this::convertToDTO);
//    }
//
//    // Save employee
//    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
//        Employee employee = convertToEntity(employeeDTO);
//        Employee savedEmployee = employeeRepository.save(employee);
//        return convertToDTO(savedEmployee);
//    }
//
//    // Delete employee
//    public void deleteEmployee(Long id) {
//        employeeRepository.deleteById(id);
//    }
//
//    // Helper method to convert Employee to EmployeeDTO
//    private EmployeeDTO convertToDTO(Employee employee) {
//        return new EmployeeDTO(employee.getId(), employee.getName(), employee.getDepartment());
//    }
//
//    // Helper method to convert EmployeeDTO to Employee
//    private Employee convertToEntity(EmployeeDTO employeeDTO) {
//        return new Employee(employeeDTO.getName(), employeeDTO.getDepartment());
//    }
//}
package com.example.demo.service;
import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeDTO;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class EmployeeService {
    @Autowired
    private  EmployeeRepository employeeRepository;

    // Get all employees
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Get employee by ID
    public Optional<EmployeeDTO> getEmployeeById(Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        return employeeOptional.map(this::convertToDTO);
    }

    // Save employee
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = convertToEntity(employeeDTO);
        Employee savedEmployee = employeeRepository.save(employee);
        return convertToDTO(savedEmployee);
    }

    // Delete employee
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
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
