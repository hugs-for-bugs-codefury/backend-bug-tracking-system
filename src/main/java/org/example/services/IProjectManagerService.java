package org.example.services;

import org.example.models.Project;
import org.example.models.ProjectManager;

import java.time.LocalDateTime;

/**
 * The {@code IProjectManagerService} interface extends the {@link IUserService} and provides
 * specific methods for managing project manager-related operations within the system. This includes
 * registration, login, project creation, and the assignment of testers and developers to projects and bugs.
 */
public interface IProjectManagerService extends IUserService {

    /**
     * Registers a new project manager in the system.
     *
     * @param name     The name of the project manager.
     * @param email    The email address of the project manager.
     * @param password The password for the project manager's account.
     * @return The newly registered {@link ProjectManager} object.
     */
    ProjectManager registerUser(String name, String email, String password);

    /**
     * Authenticates a project manager with the provided email and password.
     *
     * @param email    The email address of the project manager.
     * @param password The password for the project manager's account.
     * @return The authenticated {@link ProjectManager} object.
     */
    ProjectManager login(String email, String password);

    /**
     * Retrieves the currently logged-in project manager.
     *
     * @return The {@link ProjectManager} object representing the current project manager.
     */
    ProjectManager getCurrentProjectManager();

    /**
     * Retrieves a project manager by their unique ID.
     *
     * @param projectManagerId The unique ID of the project manager.
     * @return The {@link ProjectManager} object representing the project manager with the specified ID.
     */
    ProjectManager getProjectManager(int projectManagerId);

    /**
     * Creates a new project in the system.
     *
     * @param name      The name of the project.
     * @param startDate The start date of the project.
     * @return The newly created {@link Project} object.
     */
    Project createProject(String name, LocalDateTime startDate);

    /**
     * Assigns a tester to a project.
     *
     * @param projectId The unique ID of the project.
     * @param testerId  The unique ID of the tester to be assigned to the project.
     */
    void assignTesterToProject(int projectId, int testerId);

    /**
     * Assigns a developer to a project.
     *
     * @param projectId   The unique ID of the project.
     * @param developerId The unique ID of the developer to be assigned to the project.
     */
    void assignDeveloperToProject(int projectId, int developerId);

    /**
     * Assigns a developer to a specific bug.
     *
     * @param bugId       The unique ID of the bug.
     * @param developerId The unique ID of the developer to be assigned to the bug.
     */
    void assignDeveloperToBug(int bugId, int developerId);
}
