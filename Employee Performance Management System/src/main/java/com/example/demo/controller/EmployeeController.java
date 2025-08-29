package com.example.demo.controller;

import com.example.demo.Response.UnifiedApiResponse;
import com.example.demo.dto.EmployeeDto;
import com.example.demo.entity.Employee;
import com.example.demo.entity.PerformanceReview;
import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.EmployeeServiceInterface;
import com.example.demo.service.PerformanceReviewService;
import com.example.demo.service.PerformanceReviewServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeServiceInterface employeeService;
    @Autowired
    private PerformanceReviewServiceInterface performanceReviewService;

    @GetMapping
    public ResponseEntity<UnifiedApiResponse> getEmployees(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate reviewDate,
            @RequestParam(required = false) List<String> departments,
            @RequestParam(required = false) List<String> projects) {
        UnifiedApiResponse response = new UnifiedApiResponse();
        List<Employee> employees= employeeService.getEmployees( reviewDate, departments, projects);
        response.setData(employees);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnifiedApiResponse>  getEmployeeDetails(@PathVariable Long id) {
        UnifiedApiResponse response = new UnifiedApiResponse();
        Employee employee = employeeService.getEmployeeDetails(id);
        List<PerformanceReview> lastThreePerformanceReview = performanceReviewService.getLastThreePerformanceReviewByEmployeeId(id);
        Map<String, Object> output = new HashMap<>();
        EmployeeDto employeeDto= EmployeeMapper.toDto(employee);
        output.put("employee", employeeDto);
        output.put("lastThreeReviews", lastThreePerformanceReview);
        response.setData(output);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

}

