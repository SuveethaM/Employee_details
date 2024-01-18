package com.example.demo.model;



//public class EmployeeDTO {
//    private Long id;
//    private String name;
//    private String department;
//
//    // Constructors
//    public EmployeeDTO() {
//    }
//
//    public EmployeeDTO(Long id, String name, String department) {
//        this.id = id;
//        this.name = name;
//        this.department = department;
//    }
//
//    // Getters and Setters
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getDepartment() {
//        return department;
//    }
//
//    public void setDepartment(String department) {
//        this.department = department;
//    }
//}



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeDTO {
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