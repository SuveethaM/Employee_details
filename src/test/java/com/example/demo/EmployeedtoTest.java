package com.example.demo;
import com.example.demo.dto.EmployeeDTO;
import com.example.demo.model.Employee;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmployeedtoTest {

    @Test
    void testDefaultConstructor() {
        // When
        EmployeeDTO employeeDTO = new EmployeeDTO();

        // Then
        assertNull(employeeDTO.getId());
        assertNull(employeeDTO.getName());
        assertNull(employeeDTO.getDepartment());
    }

    @Test
    void testAllArgsConstructor() {
        // Given
        Long id = 1L;
        String name = "John Doe";
        String department = "IT";

        // When
        EmployeeDTO employeeDTO = new EmployeeDTO(id, name, department);

        // Then
        assertEquals(id, employeeDTO.getId());
        assertEquals(name, employeeDTO.getName());
        assertEquals(department, employeeDTO.getDepartment());
    }

    // Add more specific tests based on your application requirements

}
