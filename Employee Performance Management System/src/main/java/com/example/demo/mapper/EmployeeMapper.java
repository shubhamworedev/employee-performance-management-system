package com.example.demo.mapper;

import com.example.demo.dto.DepartmentDto;
import com.example.demo.dto.EmployeeDto;
import com.example.demo.dto.EmployeeProjectDto;
import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;
import com.example.demo.entity.EmployeeProject;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeMapper {

    private EmployeeMapper() {
    }

    public static EmployeeDto toDto(Employee employee) {
        if (employee == null) {
            return null;
        }

        EmployeeDto dto = new EmployeeDto();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setEmail(employee.getEmail());
        dto.setDateOfJoining(employee.getDateOfJoining());
        dto.setSalary(employee.getSalary());

        dto.setDepartment(toDepartmentDto(employee.getDepartment()));

        if (employee.getManager() != null) {
            dto.setManagerName(employee.getManager().getName());
        }

        if (employee.getEmployeeProjects() != null) {
            List<EmployeeProjectDto> projectDtos = employee.getEmployeeProjects()
                    .stream()
                    .map(EmployeeMapper::toEmployeeProjectDto)
                    .collect(Collectors.toList());
            dto.setEmployeeProjects(projectDtos);
        }

        return dto;
    }

    public static Page<EmployeeDto> toDtoPage(Page<Employee> employees) {
        return employees.map(EmployeeMapper::toDto);
    }

    private static DepartmentDto toDepartmentDto(Department department) {
        if (department == null) return null;
        DepartmentDto dto = new DepartmentDto();
        dto.setId(department.getId());
        dto.setName(department.getName());
        dto.setBudget(department.getBudget());
        return dto;
    }

    private static EmployeeProjectDto toEmployeeProjectDto(EmployeeProject employeeProject) {
        if (employeeProject == null) return null;
        EmployeeProjectDto dto = new EmployeeProjectDto();
        dto.setId(employeeProject.getId());
        dto.setAssignedDate(employeeProject.getAssignedDate());
        dto.setRole(employeeProject.getRole());
        if (employeeProject.getProject() != null) {
            dto.setProjectId(employeeProject.getProject().getId());
            dto.setProjectName(employeeProject.getProject().getName());
        }
        return dto;
    }
}
