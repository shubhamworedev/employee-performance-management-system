package com.example.demo.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ProjectDto {
    private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long departmentId; // reference instead of full Department to avoid cycles
}
