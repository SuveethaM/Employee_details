
package com.example.demo;
import com.example.demo.dto.EmployeeDTO;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void testGetAllEmployees() {
        // Arrange
        Employee employee1 = new Employee(1L, "John", "IT");
        Employee employee2 = new Employee(2L, "Jane", "HR");
        List<Employee> mockEmployees = Arrays.asList(employee1, employee2);

        when(employeeRepository.findAll()).thenReturn(mockEmployees);

        // Act
        List<EmployeeDTO> result = employeeService.getAllEmployees();

        // Assert
        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getName());
        assertEquals("IT", result.get(0).getDepartment());
        assertEquals("Jane", result.get(1).getName());
        assertEquals("HR", result.get(1).getDepartment());

        // Verify that the repository method was called
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void testGetEmployeeById() {
        // Arrange
        Long employeeId = 1L;
        Employee mockEmployee = new Employee(employeeId, "John", "IT");

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(mockEmployee));

        // Act
        Optional<EmployeeDTO> result = employeeService.getEmployeeById(employeeId);

        // Assert
        assertEquals("John", result.orElseThrow().getName());
        assertEquals("IT", result.orElseThrow().getDepartment());

        // Verify that the repository method was called
        verify(employeeRepository, times(1)).findById(employeeId);
    }

    @Test
    void testSaveEmployee() {
        // Arrange
        EmployeeDTO employeeDTO = new EmployeeDTO(1L, "John", "IT");
        Employee employee = new Employee(1L, "John", "IT");

        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        // Act
        EmployeeDTO result = employeeService.saveEmployee(employeeDTO);

        // Assert
        assertEquals("John", result.getName());
        assertEquals("IT", result.getDepartment());

        // Verify that the repository method was called
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    void testDeleteEmployee() {
        // Arrange
        Long employeeId = 1L;
        Employee mockEmployee = new Employee(employeeId, "John", "IT");

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(mockEmployee));

        // Act
        employeeService.deleteEmployee(employeeId);

        // Verify that the repository method was called
        verify(employeeRepository, times(1)).delete(mockEmployee);
    }

//    @Test
//    void testDeleteEmployeeNotFound() {
//        // Arrange
//        Long employeeId = 1L;
//
//        when(employeeRepository.findById(employeeId)).thenReturn(Optional.empty());
//
//        // Act and Assert
//        assertThrows(IllegalArgumentException.class, () -> employeeService.deleteEmployee(employeeId),
//                "Expected deleteEmployee to throw IllegalArgumentException");
//
//        // Verify that the repository method was not called
//        verify(employeeRepository, never()).delete(any());
//    }



}
