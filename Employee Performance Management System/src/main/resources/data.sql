INSERT INTO department (id, name, budget) VALUES (1, 'IT', 500000);
INSERT INTO department (id, name, budget) VALUES (2, 'HR', 200000);
INSERT INTO department (id, name, budget) VALUES (3, 'Finance', 300000);

INSERT INTO project (id, name, start_date, end_date, department_id) VALUES
(1, 'Project okay', '2024-01-01', '2024-06-30', 1),
(2, 'Project Beta', '2024-02-01', '2024-07-31', 2);

INSERT INTO employee (id, name, email, department_id, date_of_joining, salary) VALUES
(1, 'John Doe', 'john.doe@example.com', 1, '2024-01-05', 50000),
(2, 'Jane Smith', 'jane.smith@example.com', 2, '2024-02-10', 60000);

INSERT INTO employee_project (id, employee_id, project_id, assigned_date, role) VALUES
(1, 1, 1, '2024-01-10', 'Developer'),
(2, 1, 2, '2024-02-05', 'Lead Developer'),
(3, 2, 2, '2024-01-15', 'Tester');

-- Performance Reviews for John Doe (Employee ID 1)
INSERT INTO performance_review (id, employee_id, review_date, score, review_comments) VALUES
(1, 1, '2024-01-15', 95, 'Excellent work on Project Alpha'),
(2, 1, '2024-02-15', 92, 'Strong problem-solving skills, reliable under pressure'),
(3, 1, '2024-03-15', 88, 'Good performance, but needs to improve documentation'),
(4, 1, '2024-04-15', 94, 'Great teamwork and leadership in sprints'),
(5, 1, '2024-05-15', 96, 'Consistently exceeds expectations, outstanding developer');

-- Performance Reviews for Jane Smith (Employee ID 2)
INSERT INTO performance_review (id, employee_id, review_date, score, review_comments) VALUES
(6, 2, '2024-01-15', 90, 'Strong leadership qualities'),
(7, 2, '2024-02-20', 87, 'Effective collaboration across teams'),
(8, 2, '2024-03-18', 91, 'Shows initiative in solving HR challenges'),
(9, 2, '2024-04-22', 89, 'Good adaptability to project needs'),
(10, 2, '2024-05-25', 93, 'Excellent mentor and motivator for new employees');
