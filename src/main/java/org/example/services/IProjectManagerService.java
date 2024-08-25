package org.example.services;

import org.example.models.*;
import java.time.LocalDateTime;

/**
 * The IProjectManagerService interface extends the IUserService interface
 * and defines additional methods specifically for project manager-related
 * functionalities within the bug tracking system.
 *
 * This interface provides methods for project manager registration, login,
 * retrieving project manager information, managing projects, and assigning
 * team members (testers and developers) to projects and bugs. It is intended
 * to be implemented by a service class that handles the business logic
 * associated with project managers.
 */
public interface IProjectManagerService extends IUserService {

    /**
     * Registers a new project manager with the given details.
     *
     * @param name     The name of the project manager.
     * @param email    The email address of the project manager.
     * @param password The password for the project manager's account.
     * @return The newly registered ProjectManager object.
     *

     */
    public ProjectManager registerUser(String name, String email, String password);

    /**
     * Authenticates a project manager with the provided email and password.
     *
     * @param email    The email address of the project manager trying to log in.
     * @param password The password corresponding to the email.
     * @return The ProjectManager object if login is successful; otherwise, null.
     *

     */
    public ProjectManager login(String email, String password);

    /**
     * Retrieves the currently logged-in project manager.
     *
     * @return The ProjectManager object representing the currently authenticated project manager.
     *

     */
    public ProjectManager getCurrentProjectManager();

    /**
     * Retrieves a project manager by their unique project manager ID.
     *
     * @param projectManagerId The unique ID of the project manager.
     * @return The ProjectManager object corresponding to the given projectManagerId.
     *

     */
    public ProjectManager getProjectManager(int projectManagerId);

    /**
     * Creates a new project with the specified name and start date.
     *
     * @param name      The name of the project to be created.
     * @param startDate The start date of the project.
     * @return The newly created Project object.
     *

    Project createProject(String name, LocalDateTime startDate);

    /**
     * Assigns a tester to a project.
     *
     * @param project The Project object to which the tester will be assigned.
     * @param tester  The Tester object representing the tester to be assigned.
     *

     */
    public void assignTester(Project project, Tester tester);

    /**
     * Assigns a developer to a bug for resolution.
     *
     * @param bug       The Bug object representing the bug to be assigned.
     * @param developer The Developer object representing the developer who will resolve the bug.
     *

     */
    public void assignDeveloper(Bug bug, Developer developer);
}
