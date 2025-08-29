package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeProjectDto {
    private Long id;
    private Long projectId;
    private String projectName;
    private LocalDate assignedDate;
    private String role;
}
