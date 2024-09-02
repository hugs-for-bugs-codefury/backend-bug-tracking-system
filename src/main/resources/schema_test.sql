
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS bugs;
DROP TABLE IF EXISTS developers_projects;
DROP TABLE IF EXISTS testers_projects;
DROP TABLE IF EXISTS testers;
DROP TABLE IF EXISTS developers;
DROP TABLE IF EXISTS projects;
DROP TABLE IF EXISTS project_managers;
DROP TABLE IF EXISTS users;
SET FOREIGN_KEY_CHECKS = 1;

-- Create Users table
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    last_login TIMESTAMP NULL,
    role ENUM('project_manager', 'developer', 'tester') NOT NULL
);

-- Create ProjectManagers table
CREATE TABLE project_managers (
    project_manager_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    max_projects INT NOT NULL DEFAULT 4,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- Create Projects table
CREATE TABLE projects (
    project_id INT AUTO_INCREMENT PRIMARY KEY,
    project_name VARCHAR(100) NOT NULL,
    start_date DATE NOT NULL,
    project_manager_id INT NOT NULL,
    status ENUM('in_progress', 'completed') DEFAULT 'in_progress',
    FOREIGN KEY (project_manager_id) REFERENCES project_managers(project_manager_id)
);

-- Create Developers table
CREATE TABLE developers (
    developer_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,

    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE

);

-- Create Testers table
CREATE TABLE testers (
    tester_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,

    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE

);



-- Create testers_projects table
CREATE TABLE testers_projects (
    project_id INT NOT NULL,
    tester_id INT NOT NULL,
    FOREIGN KEY (project_id) REFERENCES projects(project_id) ON DELETE CASCADE,
    FOREIGN KEY (tester_id) REFERENCES testers(tester_id) ON DELETE CASCADE,
    PRIMARY KEY (project_id, tester_id)
);

-- Crete developers_projects table
CREATE TABLE developers_projects (
    project_id INT NOT NULL,
    developer_id INT NOT NULL,
    FOREIGN KEY (project_id) REFERENCES projects(project_id) ON DELETE CASCADE,
    FOREIGN KEY (developer_id) REFERENCES developers(developer_id) ON DELETE CASCADE,
    PRIMARY KEY (project_id, developer_id)
);

-- Create Bugs table
CREATE TABLE bugs (
    bug_id INT AUTO_INCREMENT PRIMARY KEY,
    project_id INT NOT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    severity ENUM('low', 'medium', 'high', 'critical') NOT NULL,
    created_by INT NOT NULL,
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    assigned_to INT DEFAULT NULL,
    status ENUM('open', 'closed') DEFAULT 'open',
    FOREIGN KEY (project_id) REFERENCES projects(project_id) ON DELETE CASCADE,
    FOREIGN KEY (created_by) REFERENCES testers(tester_id),
    FOREIGN KEY (assigned_to) REFERENCES developers(developer_id)
);


