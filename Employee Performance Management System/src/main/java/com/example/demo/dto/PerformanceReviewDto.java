package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PerformanceReviewDto {
    private Long id;
    private LocalDate reviewDate;
    private Integer score;
    private String reviewComments;
}
