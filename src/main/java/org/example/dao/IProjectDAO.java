package org.example.dao;

import org.example.exceptions.projects.MaxProjectsException;
import org.example.exceptions.projects.ProjectNotFoundException;
import org.example.models.Bug;
import org.example.models.Developer;
import org.example.models.Project;
import org.example.models.Tester;

import java.sql.SQLException;
import java.util.List;

/**
 * IProjectDAO defines the data access methods related to Project entities.
 * This interface abstracts the database operations for the Project model.
 */
public interface IProjectDAO {

    /**
     * Saves a new Project to the database.
     *
     * @param project the Project to be saved.
     * @return the saved Project instance.
     * @throws SQLException if a database access error occurs.
     * @throws MaxProjectsException if the maximum number of projects is exceeded.
     */
    public Project saveProject(Project project) throws SQLException, MaxProjectsException;

    /**
     * Finds a Project by its unique identifier (ID).
     *
     * @param projectId the unique identifier of the Project.
     * @return the Project with the specified ID.
     * @throws SQLException if a database access error occurs.
     * @throws ProjectNotFoundException if no Project is found with the specified ID.
     */
    public Project findByID(int projectId) throws SQLException, ProjectNotFoundException;

    /**
     * Retrieves a list of Projects managed by a specific ProjectManager.
     *
     * @param managerId the unique identifier of the ProjectManager.
     * @return a list of Projects managed by the specified ProjectManager.
     * @throws SQLException if a database access error occurs.
     */
    public List<Project> findProjectsByManager(int managerId) throws SQLException;

    /**
     * Retrieves a list of Projects associated with a specific Tester.
     *
     * @param testerId the unique identifier of the Tester.
     * @return a list of Projects associated with the specified Tester.
     * @throws SQLException if a database access error occurs.
     */
    public List<Project> findProjectsByTester(int testerId) throws SQLException;

    /**
     * Retrieves a list of Projects associated with a specific Developer.
     *
     * @param developerId the unique identifier of the Developer.
     * @return a list of Projects associated with the specified Developer.
     * @throws SQLException if a database access error occurs.
     */
    public List<Project> findProjectsByDeveloper(int developerId) throws SQLException;

    /**
     * Assigns a Tester to a Project.
     *
     * @param projectId the unique identifier of the Project.
     * @param testerId the unique identifier of the Tester.
     * @return the updated Project instance with the Tester assigned.
     * @throws SQLException if a database access error occurs.
     * @throws MaxProjectsException if the maximum number of Testers for the Project is exceeded.
     */
    public Project assignTester(int projectId, int testerId) throws SQLException, MaxProjectsException;

    /**
     * Assigns a Developer to a Project.
     *
     * @param projectId the unique identifier of the Project.
     * @param developerId the unique identifier of the Developer.
     * @return the updated Project instance with the Developer assigned.
     * @throws SQLException if a database access error occurs.
     * @throws MaxProjectsException if the maximum number of Developers for the Project is exceeded.
     */
    public Project assignDeveloper(int projectId, int developerId) throws SQLException, MaxProjectsException;

    /**
     * Retrieves a list of Developers assigned to a specific Project.
     *
     * @param projectId the unique identifier of the Project.
     * @return a list of Developers assigned to the specified Project.
     * @throws SQLException if a database access error occurs.
     */
    public List<Developer> getAssignedDevelopers(int projectId) throws SQLException;

    /**
     * Retrieves a list of Testers assigned to a specific Project.
     *
     * @param projectId the unique identifier of the Project.
     * @return a list of Testers assigned to the specified Project.
     * @throws SQLException if a database access error occurs.
     */
    public List<Tester> getAssignedTesters(int projectId) throws SQLException;

    /**
     * Retrieves a list of Bugs associated with a specific Project.
     *
     * @param projectId the unique identifier of the Project.
     * @return a list of Bugs associated with the specified Project.
     * @throws SQLException if a database access error occurs.
     */
    public List<Bug> getProjectBugs(int projectId) throws SQLException;
}
