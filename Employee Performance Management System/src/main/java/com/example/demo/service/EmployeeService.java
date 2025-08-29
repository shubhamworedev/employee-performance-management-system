package com.example.demo.service;

import com.example.demo.entity.Employee;
import com.example.demo.exception.custom.BadRequestException;
import com.example.demo.exception.custom.NotFoundException;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.spec.EmployeeSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmployeeService implements EmployeeServiceInterface {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getEmployees( LocalDate reviewDate,
                                       List<String> departments, List<String> projects) {
        Specification<Employee> spec = EmployeeSpecification.getFilteredEmployees(reviewDate, departments, projects
        );
        return employeeRepository.findAll(spec);
    }

    @Override
    public Employee getEmployeeDetails(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new NotFoundException("Employee not found"));
    }
}

