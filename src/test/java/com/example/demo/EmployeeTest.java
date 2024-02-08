package com.example.demo;
import com.example.demo.model.Employee;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    void testNoArgsConstructor() {
        // Act
        Employee employee = new Employee();

        // Assert
        assertNull(employee.getId());
        assertNull(employee.getName());
        assertNull(employee.getDepartment());
    }

    @Test
    void testParameterizedConstructor() {
        // Arrange
        Long id = 1L;
        String name = "John Doe";
        String department = "IT";

        // Act
        Employee employee = new Employee(id, name, department);

        // Assert
        assertEquals(id, employee.getId());
        assertEquals(name, employee.getName());
        assertEquals(department, employee.getDepartment());
    }

    @Test
    void testSettersAndGetters() {
        // Arrange
        Employee employee = new Employee();
        Long id = 1L;
        String name = "John Doe";
        String department = "IT";

        // Act
        employee.setId(id);
        employee.setName(name);
        employee.setDepartment(department);

        // Assert
        assertEquals(id, employee.getId());
        assertEquals(name, employee.getName());
        assertEquals(department, employee.getDepartment());
    }

    @Test
    void testEqualsAndHashCode() {
        // Arrange
        Long id1 = 1L;
        Long id2 = 2L;
        String name = "John Doe";
        String department = "IT";

        // Act
        Employee employee1 = new Employee(id1, name, department);
        Employee employee2 = new Employee(id1, name, department);
        Employee employee3 = new Employee(id2, name, department);

        // Assert
        assertTrue(employee1.equals(employee2) && employee2.equals(employee1));
        assertEquals(employee1.hashCode(), employee2.hashCode());
        assertFalse(employee1.equals(employee3) || employee3.equals(employee1));
        assertNotEquals(employee1.hashCode(), employee3.hashCode());
    }
    @Test
    void testToString() {
        // Arrange
        Long id = 1L;
        String name = "John Doe";
        String department = "IT";
        Employee employee = new Employee(id, name, department);

        // Act
        String toStringResult = employee.toString();

        // Assert
        assertTrue(toStringResult.contains("id=" + id));
        assertTrue(toStringResult.contains("name=" + name));
        assertTrue(toStringResult.contains("department=" + department));
    }
}

