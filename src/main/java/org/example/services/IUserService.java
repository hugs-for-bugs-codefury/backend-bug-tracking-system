package org.example.services;

import org.example.models.Project;
import org.example.models.User;

/**
 * The {@code IUserService} interface provides common user-related operations within the system,
 * including registration, authentication, and retrieving user and project details.
 * It serves as a base interface for more specific user roles, such as testers, developers, and project managers.
 */
public interface IUserService {

    /**
     * Registers a new user in the system with the specified role.
     *
     * @param name     The name of the user.
     * @param email    The email address of the user.
     * @param password The password for the user's account.
     * @param role     The role of the user (e.g., "Tester", "Developer", "Project Manager").
     * @return The newly registered {@link User} object.
     */
    User registerUser(String name, String email, String password, String role);

    /**
     * Retrieves a user by their unique ID.
     *
     * @param userId The unique ID of the user.
     * @return The {@link User} object representing the user with the specified ID.
     */
    User getUser(int userId);

    /**
     * Retrieves a project by its unique ID.
     *
     * @param projectId The unique ID of the project.
     * @return The {@link Project} object representing the project with the specified ID.
     */
    Project getProject(int projectId);

    /**
     * Authenticates a user with the provided email and password.
     *
     * @param email    The email address of the user.
     * @param password The password for the user's account.
     * @return The authenticated {@link User} object.
     */
    User login(String email, String password);
}
