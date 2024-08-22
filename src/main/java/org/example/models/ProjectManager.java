package org.example.models;


import java.time.LocalDateTime;
import java.util.List;

/**
 * The ProjectManager class extends the User class and represents a project manager in the system.
 * A Project Manager is responsible for managing projects and overseeing the work of developers and testers.
 *
 * Example properties:
 * - List<Project> projects: The list of projects managed by the Project Manager.
 */

public class ProjectManager extends User {
    private int managerId;  // Unique identifier for the project manager
    private List<Integer> projectIds;  // List of project IDs managed by this project manager


    /**
     * Constructor with all fields.
     * Initializes a new instance of the ProjectManager class with the specified values.
     *
     * @param id           The unique identifier for the user.
     * @param name         The name of the user.
     * @param email        The email address of the user.
     * @param passwordHash The hashed password for authentication.
     * @param role         The role of the user (e.g., Developer, Admin).
     * @param lastLogin    The last login time of the user.
     * @param managerId    The unique identifier for the project manager.
     */
    public ProjectManager(int id,  int managerId, String name, String email, String passwordHash) {
        super(id, name, email, passwordHash, "project_manager");
        this.managerId = managerId;
    }




    /**
     * Retrieves the unique identifier of the project manager.
     *
     * @return the manager ID as a String.
     */
    public int getManagerId() {
        return managerId;
    }

    /**
     * Sets or updates the unique identifier of the project manager.
     *
     * @param managerId the new manager ID to set.
     */
    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    /**
     * Retrieves the list of project IDs that the project manager is responsible for.
     *
     * @return a list of project IDs as Strings.
     */
    public List<Integer> getProjectIds() {
        return projectIds;
    }

    /**
     * Sets or updates the list of project IDs that the project manager is responsible for.
     *
     * @param projectIds the new list of project IDs to assign to this manager.
     */
    public void setProjectIds(List<Integer> projectIds) {
        this.projectIds = projectIds;
    }
}
