package com.example.demo.service;

import com.example.demo.entity.Employee;
import java.time.LocalDate;
import java.util.List;

public interface EmployeeServiceInterface {

    public List<Employee> getEmployees(LocalDate reviewDate,
                                       List<String> departments, List<String> projects);

    public Employee getEmployeeDetails(Long id);


}
