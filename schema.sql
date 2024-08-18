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
DROP TABLE IF EXISTS Users;

-- Create Users table
CREATE TABLE Users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    last_login TIMESTAMP NULL,
    user_type ENUM('Project Manager', 'Developer', 'Tester') NOT NULL
);

-- Create ProjectManagers table
CREATE TABLE ProjectManagers (
    id INT PRIMARY KEY,
    max_projects INT NOT NULL DEFAULT 4,
    FOREIGN KEY (id) REFERENCES Users(id) ON DELETE CASCADE
);

-- Create Projects table
CREATE TABLE Projects (
    id INT AUTO_INCREMENT PRIMARY KEY,
    project_name VARCHAR(100) NOT NULL UNIQUE,
    start_date DATE NOT NULL,
    project_manager_id INT NOT NULL,
    status ENUM('In Progress', 'Completed') DEFAULT 'In Progress',
    FOREIGN KEY (project_manager_id) REFERENCES ProjectManagers(id)
);

-- Create Developers table
CREATE TABLE Developers (
    id INT PRIMARY KEY,
    project_id INT UNIQUE,
    FOREIGN KEY (id) REFERENCES Users(id) ON DELETE CASCADE,
    FOREIGN KEY (project_id) REFERENCES Projects(id) ON DELETE SET NULL
);

-- Create Testers table
CREATE TABLE Testers (
    id INT PRIMARY KEY,
    project_id INT,
    FOREIGN KEY (id) REFERENCES Users(id) ON DELETE CASCADE,
    FOREIGN KEY (project_id) REFERENCES Projects(id) ON DELETE SET NULL
);



-- Create Project_Team_Members table
CREATE TABLE Project_Team_Members (
    id INT AUTO_INCREMENT PRIMARY KEY,
    project_id INT NOT NULL,
    user_id INT NOT NULL,
    role ENUM('Developer', 'Tester') NOT NULL,
    FOREIGN KEY (project_id) REFERENCES Projects(id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE CASCADE
);

-- Create Bugs table
CREATE TABLE Bugs (
    id INT AUTO_INCREMENT PRIMARY KEY,
    project_id INT NOT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    severity ENUM('Low', 'Medium', 'High', 'Critical') NOT NULL,
    created_by INT NOT NULL,
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    assigned_to INT,
    status ENUM('Open', 'Closed') DEFAULT 'Open',
    FOREIGN KEY (project_id) REFERENCES Projects(id) ON DELETE CASCADE,
    FOREIGN KEY (created_by) REFERENCES Testers(id),
    FOREIGN KEY (assigned_to) REFERENCES Developers(id)
);

-- Trigger to enforce Developer can be assigned to only one project and Tester can be assigned to only two projects
DELIMITER $$
CREATE TRIGGER trg_check_developer_tester_assignment
BEFORE INSERT ON Project_Team_Members
FOR EACH ROW
BEGIN
    DECLARE project_count INT;
    IF NEW.role = 'Developer' THEN
        SET project_count = (SELECT COUNT(*) FROM Project_Team_Members WHERE user_id = NEW.user_id AND role = 'Developer');
        IF project_count >= 1 THEN
            SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Developer can be assigned to only one project';
        END IF;
    ELSEIF NEW.role = 'Tester' THEN
        SET project_count = (SELECT COUNT(*) FROM Project_Team_Members WHERE user_id = NEW.user_id AND role = 'Tester');
        IF project_count >= 2 THEN
            SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Tester can be assigned to only two projects';
        END IF;
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
