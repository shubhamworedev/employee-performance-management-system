package com.example.demo.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class EmployeeDto {
    private Long id;
    private String name;
    private String email;
    private DepartmentDto department;
    private LocalDate dateOfJoining;
    private BigDecimal salary;
    private String managerName;
    private List<EmployeeProjectDto> employeeProjects;
}
