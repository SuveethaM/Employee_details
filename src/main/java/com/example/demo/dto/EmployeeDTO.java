package com.example.demo.dto;
import com.example.demo.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeDTO implements Serializable {
    private Long id;
    private String name;
    private String department;



    public static EmployeeDTO fromEntity(Employee employee) {
    return new EmployeeDTO(
            employee.getId(),
            employee.getName(),
            employee.getDepartment()
    );
}
public static Employee toEntity(EmployeeDTO employeeDTO) {
    Employee employee = new Employee();
    employee.setId(employeeDTO.getId());
    employee.setDepartment(employeeDTO.getDepartment());
    return employee;
}
}