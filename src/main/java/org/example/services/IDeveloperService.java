package org.example.services;

import org.example.models.Bug;
import org.example.models.Developer;
import org.example.models.Project;

import java.util.List;

/**
 * The {@code IDeveloperService} interface extends the {@link IUserService} and provides
 * specific methods for managing developer-related operations within the system. This includes
 * registration, login, retrieving developer details, closing bugs, and accessing assigned projects and bugs.

 */
public interface IDeveloperService extends IUserService {

    /**
     * Registers a new developer in the system.
     *
     * @param name     The name of the developer.
     * @param email    The email address of the developer.
     * @param password The password for the developer's account.
     * @return The newly registered {@link Developer} object.
     */
    Developer registerUser(String name, String email, String password);

    /**
     * Authenticates a developer with the provided email and password.
     *
     * @param email    The email address of the developer.
     * @param password The password for the developer's account.
     * @return The authenticated {@link Developer} object.
     */
    Developer login(String email, String password);
  
    /**
     * Retrieves the currently logged-in developer.
     *
     * @return The {@link Developer} object representing the current developer.
     */
    Developer getCurrentDeveloper();

    /**
     * Retrieves a developer by their unique ID.
     *
     * @param developerId The unique ID of the developer.
     * @return The {@link Developer} object representing the developer with the specified ID.
     */
    Developer getDeveloper(int developerId);

    /**
     * Closes a bug by marking it as resolved.
     *
     * @param bugId The unique ID of the bug to be closed.
     */
    void closeBug(int bugId);

    /**
     * Retrieves a list of projects assigned to the currently logged-in developer.
     *
     * @return A {@link List} of {@link Project} objects assigned to the current developer.
     */
    List<Project> getAssignedProjects();

    /**
     * Retrieves a list of projects assigned to a specific developer.
     *
     * @param developerId The unique ID of the developer whose assigned projects are to be retrieved.
     * @return A {@link List} of {@link Project} objects assigned to the specified developer.
     */
    List<Project> getAssignedProjects(int developerId);

    /**
     * Retrieves a list of bugs assigned to the currently logged-in developer.
     *
     * @return A {@link List} of {@link Bug} objects assigned to the current developer.
     */
    List<Bug> getAssignedBugs();
}

