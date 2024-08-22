DROP DATABASE IF EXISTS codefury;
CREATE DATABASE codefury;
USE codefury;
-- Drop tables if they exist
DROP TABLE IF EXISTS Bugs;
DROP TABLE IF EXISTS Project_Team_Members;
DROP TABLE IF EXISTS Projects;
DROP TABLE IF EXISTS Testers;
DROP TABLE IF EXISTS Developers;
DROP TABLE IF EXISTS ProjectManagers;
DROP TABLE IF EXISTS users;

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

-- Trigger to enforce Developer can be assigned to only one project
DELIMITER $$
CREATE TRIGGER trg_check_developer_assignment
BEFORE INSERT ON developers_projects
FOR EACH ROW
BEGIN
    DECLARE project_count INT;
    SET project_count = (SELECT COUNT(*) FROM developers_projects WHERE developer_id = NEW.developer_id);
    IF project_count >= 1 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Developer can be assigned to only one project';
    END IF;
END$$
DELIMITER ;


-- Trigger to enforce Tester can be assigned to maximum of two projects
DELIMITER $$
CREATE TRIGGER trg_check_tester_assignment
BEFORE INSERT ON testers_projects
FOR EACH ROW
BEGIN
    DECLARE project_count INT;
    SET project_count = (SELECT COUNT(*) FROM testers_projects WHERE tester_id = NEW.tester_id);
    IF project_count >= 2 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Tester can be assigned to maximum of two projects';
    END IF;
END$$
DELIMITER ;



-- Trigger to enforce Project Manager can manage a maximum of four projects
DELIMITER $$
CREATE TRIGGER trg_check_project_manager_assignment
BEFORE INSERT ON Projects
FOR EACH ROW
BEGIN
    DECLARE project_count INT;
    SET project_count = (SELECT COUNT(*) FROM Projects WHERE project_manager_id = NEW.project_manager_id);
    IF project_count >= 4 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Project Manager can manage a maximum of four projects';
    END IF;
END$$
DELIMITER ;


