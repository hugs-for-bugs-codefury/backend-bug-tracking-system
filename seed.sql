USE codefury;

-- Insert Users
INSERT INTO Users (username, email, password_hash, user_type) VALUES
('pm_john', 'john_pm@example.com', 'hashed_password_1', 'Project Manager'),
('dev_sara', 'sara_dev@example.com', 'hashed_password_2', 'Developer'),
('tester_mike', 'mike_tester@example.com', 'hashed_password_3', 'Tester'),
('dev_emily', 'emily_dev@example.com', 'hashed_password_4', 'Developer'),
('tester_jane', 'jane_tester@example.com', 'hashed_password_5', 'Tester');

-- Insert Project Managers
INSERT INTO ProjectManagers (id) VALUES
((SELECT id FROM Users WHERE username = 'pm_john'));

-- Insert Developers
INSERT INTO Developers (id) VALUES
((SELECT id FROM Users WHERE username = 'dev_sara')),
((SELECT id FROM Users WHERE username = 'dev_emily'));

-- Insert Testers
INSERT INTO Testers (id) VALUES
((SELECT id FROM Users WHERE username = 'tester_mike')),
((SELECT id FROM Users WHERE username = 'tester_jane'));

-- Insert Projects
INSERT INTO Projects (project_name, start_date, project_manager_id) VALUES
('Project Alpha', '2024-08-20', (SELECT id FROM ProjectManagers WHERE id = (SELECT id FROM Users WHERE username = 'pm_john'))),
('Project Beta', '2024-08-21', (SELECT id FROM ProjectManagers WHERE id = (SELECT id FROM Users WHERE username = 'pm_john')));


-- Insert Project Team Members
INSERT INTO Project_Team_Members (project_id, user_id, role) VALUESv
((SELECT id FROM Projects WHERE project_name = 'Project Alpha'), (SELECT id FROM Developers WHERE id = (SELECT id FROM Users WHERE username = 'dev_sara')), 'Developer'),
((SELECT id FROM Projects WHERE project_name = 'Project Alpha'), (SELECT id FROM Testers WHERE id = (SELECT id FROM Users WHERE username = 'tester_mike')), 'Tester'),
((SELECT id FROM Projects WHERE project_name = 'Project Beta'), (SELECT id FROM Developers WHERE id = (SELECT id FROM Users WHERE username = 'dev_emily')), 'Developer'),
((SELECT id FROM Projects WHERE project_name = 'Project Beta'), (SELECT id FROM Testers WHERE id = (SELECT id FROM Users WHERE username = 'tester_jane')), 'Tester');


-- Insert Bugs
INSERT INTO Bugs (project_id, title, description, severity, created_by, assigned_to) VALUES
((SELECT id FROM Projects WHERE project_name = 'Project Alpha'), 'Bug 1', 'Description for Bug 1', 'High', (SELECT id FROM Testers WHERE id = (SELECT id FROM Users WHERE username = 'tester_mike')), (SELECT id FROM Developers WHERE id = (SELECT id FROM Users WHERE username = 'dev_sara'))),
((SELECT id FROM Projects WHERE project_name = 'Project Beta'), 'Bug 2', 'Description for Bug 2', 'Medium', (SELECT id FROM Testers WHERE id = (SELECT id FROM Users WHERE username = 'tester_jane')), (SELECT id FROM Developers WHERE id = (SELECT id FROM Users WHERE username = 'dev_emily')));



-- Following queries should fire the triggers and throw exceptions

-- Attempt to assign 'dev_sara' to another project which should trigger an exception
INSERT INTO Project_Team_Members (project_id, user_id, role) VALUES
((SELECT id FROM Projects WHERE project_name = 'Project Beta'), (SELECT id FROM Developers WHERE id = (SELECT id FROM Users WHERE username = 'dev_sara')), 'Developer');


-- Insert a new project
INSERT INTO Projects (project_name, start_date, project_manager_id) VALUES
('Project Gamma', '2024-08-22', (SELECT id FROM ProjectManagers WHERE id = (SELECT id FROM Users WHERE username = 'pm_john')));

-- Attempt to assign 'tester_mike' to a third project which should trigger an exception
INSERT INTO Project_Team_Members (project_id, user_id, role) VALUES
((SELECT id FROM Projects WHERE project_name = 'Project Gamma'), (SELECT id FROM Testers WHERE id = (SELECT id FROM Users WHERE username = 'tester_mike')), 'Tester');



-- Insert additional projects to exceed the limit of four projects for the project manager
INSERT INTO Projects (project_name, start_date, project_manager_id) VALUES
('Project Delta', '2024-08-23', (SELECT id FROM ProjectManagers WHERE id = (SELECT id FROM Users WHERE username = 'pm_john'))),
('Project Epsilon', '2024-08-24', (SELECT id FROM ProjectManagers WHERE id = (SELECT id FROM Users WHERE username = 'pm_john'))),
('Project Zeta', '2024-08-25', (SELECT id FROM ProjectManagers WHERE id = (SELECT id FROM Users WHERE username = 'pm_john')));
