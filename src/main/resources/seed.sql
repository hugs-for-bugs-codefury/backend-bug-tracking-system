SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE bugs;
TRUNCATE TABLE developers_projects;
TRUNCATE TABLE testers_projects;
TRUNCATE TABLE testers;
TRUNCATE TABLE developers;
TRUNCATE TABLE projects;
TRUNCATE TABLE project_managers;
TRUNCATE TABLE users;



-- Insert random data into users table
INSERT INTO users (name, email, password_hash, last_login, role) VALUES
('Alice Smith', 'alice.smith@example.com', 'password123', NULL, 'project_manager'),
('Bob Johnson', 'bob.johnson@example.com', 'password123', NULL, 'developer'),
('Charlie Brown', 'charlie.brown@example.com', 'password123', NULL, 'tester'),
('David Williams', 'david.williams@example.com', 'password123', NULL, 'project_manager'),
('Eva Green', 'eva.green@example.com', 'password123', NULL, 'developer'),
('Frank Wright', 'frank.wright@example.com', 'password123', NULL, 'tester'),
('Grace Adams', 'grace.adams@example.com', 'password123', NULL, 'developer'),
('Henry Clark', 'henry.clark@example.com', 'password123', NULL, 'tester'),
('Isabella Turner', 'isabella.turner@example.com', 'password123', NULL, 'project_manager'),
('Jack Lewis', 'jack.lewis@example.com', 'password123', NULL, 'developer'),
('Karen Hall', 'karen.hall@example.com', 'password123', NULL, 'tester');

-- Insert data into project_managers table
INSERT INTO project_managers (user_id, max_projects) VALUES
(1, 4),
(4, 4),
(9, 4);

-- Insert data into projects table
INSERT INTO projects (project_name, start_date, project_manager_id, status) VALUES
('Project Alpha', '2024-01-01', 1, 'in_progress'),
('Project Beta', '2024-02-01', 4, 'in_progress'),
('Project Gamma', '2024-03-01', 9, 'in_progress'),
('Project Delta', '2024-04-01', 1, 'completed'),
('Project Epsilon', '2024-05-01', 4, 'in_progress');

-- Insert data into developers table
INSERT INTO developers (user_id) VALUES
(2),
(5),
(7),
(10);

-- Insert data into testers table
INSERT INTO testers (user_id) VALUES
(3),
(6),
(8),
(11);

-- Insert data into testers_projects table
INSERT INTO testers_projects (project_id, tester_id) VALUES
(1, 1),
(1, 2),
(2, 2),
(3, 3),
(4, 3),
(4, 4),
(5, 4);

-- Insert data into developers_projects table
INSERT INTO developers_projects (project_id, developer_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 2);

-- Insert data into bugs table
INSERT INTO bugs (project_id, title, description, severity, created_by, assigned_to, status) VALUES
(1, 'Bug 1', 'Description of bug 1', 'low', 1, 1, 'open'),
(1, 'Bug 2', 'Description of bug 2', 'medium', 2, 1, 'open'),
(2, 'Bug 3', 'Description of bug 3', 'high', 2, 2, 'open'),
(3, 'Bug 4', 'Description of bug 4', 'critical', 3, 3, 'open'),
(4, 'Bug 5', 'Description of bug 5', 'low', 3, 4, 'open'),
(5, 'Bug 6', 'Description of bug 6', 'medium', 4, 2, 'closed');



SET FOREIGN_KEY_CHECKS = 1;