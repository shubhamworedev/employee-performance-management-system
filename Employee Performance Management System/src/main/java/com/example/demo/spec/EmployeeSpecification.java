package com.example.demo.spec;

import com.example.demo.entity.Employee;
import com.example.demo.entity.PerformanceReview;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeSpecification {

    public static Specification<Employee>   getFilteredEmployees(
            LocalDate reviewDate,
            List<String> departments,
            List<String> projects
    ) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            Join<Employee, PerformanceReview> reviews = null;

            if ( reviewDate != null) {
                reviews = root.join("reviews");
                predicates.add(cb.equal(reviews.get("reviewDate"), reviewDate));
            }

            if (departments != null && !departments.isEmpty()) {
                predicates.add(root.get("department").get("name").in(departments));
            }

            if (projects != null && !projects.isEmpty()) {
                Join<Object, Object> employeeProjects = root.join("employeeProjects");
                Join<Object, Object> project = employeeProjects.join("project");
                predicates.add(project.get("name").in(projects));
            }

            query.orderBy(cb.asc(root.get("name")));

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
