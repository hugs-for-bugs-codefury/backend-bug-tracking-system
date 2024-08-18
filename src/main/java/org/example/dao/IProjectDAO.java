package org.example.dao;


import org.example.models.Project;
import java.util.Date;

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
    /**
     * Create a new project with the given name, start date, and project manager ID.
     * @param name The name of the project.
     * @param startDate The start date of the project.
     * @param projectManagerId The ID of the project manager.
     * @return The created project.
     */
    Project createProject(String name, Date startDate, int projectManagerId);

    /**
     * Assign a team member to a project with the given project ID, user ID, and role.
     * @param projectId The ID of the project.
     * @param userId The ID of the user.
     * @param role The role of the user in the project.
     */
    void assignTeamMember(int projectId, int userId, String role);

    /**
     * Get a project by its ID.
     * @param projectId The ID of the project.
     * @return The project with the given ID.
     */
    Project getProject(int projectId);

}
