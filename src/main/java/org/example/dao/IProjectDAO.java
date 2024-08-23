package org.example.dao;


import org.example.exceptions.projects.MaxProjectsException;
import org.example.exceptions.projects.ProjectNotFoundException;
import org.example.exceptions.users.UserNotFoundException;
import org.example.models.Bug;
import org.example.models.Developer;
import org.example.models.Project;
import org.example.models.Tester;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * IProjectDAO defines the data access methods related to Project entities.
 * This interface abstracts the database operations for the Project model.
 *
 * Example methods include but are not limited to:
 * - Project save(Project project): Save a new project to the database.
 * - Project findById(int id): Retrieve a project by its ID.
 * - List<Project> findAll(): Retrieve a list of all projects.
 * - void deleteById(int id): Delete a project by its ID.
 */

public interface IProjectDAO {

    public Project saveProject(Project project) throws SQLException, MaxProjectsException;
    public Project findByID(int projectId) throws SQLException, ProjectNotFoundException;
    public List<Project> findProjectsByManager(int managerId) throws SQLException;
    public List<Project> findProjectsByTester(int testerId) throws SQLException;
    public List<Project> findProjectsByDeveloper(int developerId) throws SQLException;
    public Project assignTester(int projectId, int testerId) throws SQLException, MaxProjectsException;
    public Project assignDeveloper(int projectId, int developerId) throws SQLException, MaxProjectsException;
    public List<Developer> getAssignedDevelopers(int projectId) throws SQLException;
    public List<Tester> getAssignedTesters(int projectId) throws SQLException;
    public List<Bug> getProjectBugs(int projectId) throws SQLException;


}
