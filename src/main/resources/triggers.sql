-- Trigger to enforce Developer can be assigned to only one projectU
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

