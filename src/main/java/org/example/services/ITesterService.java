package org.example.services;

import org.example.models.*;

import java.util.List;

/**
 * The IDeveloperService interface provides methods for managing developer-specific operations,
 * including user registration, login, and handling of assigned projects and bugs.
 * It extends the IUserService interface, inheriting its user-related functionalities.
 */
public interface IDeveloperService extends IUserService {

    /**
     * Registers a new developer with the given details.
     *
     * @param name     the name of the developer.
     * @param email    the email address of the developer.
     * @param password the password for the developer's account.
     * @return the registered Developer object.
     */
    public Developer registerUser(String name, String email, String password);

    /**
     * Logs in a developer using the provided email and password.
     *
     * @param email    the email address of the developer.
     * @param password the password for the developer's account.
     * @return the Developer object if login is successful.
     */
    public Developer login(String email, String password);

    /**
     * Retrieves the currently logged-in developer.
     *
     * @return the Developer object representing the currently logged-in developer.
     */
    public Developer getCurrentDeveloper();

    /**
     * Retrieves a developer by their unique developer ID.
     *
     * @param developerId the unique identifier of the developer.
     * @return the Developer object associated with the provided developer ID.
     */
    public Developer getDeveloper(int developerId);

    /**
     * Closes a bug by its unique bug ID.
     *
     * @param bugId the unique identifier of the bug to be closed.
     */
    public void closeBug(int bugId);

    /**
     * Retrieves the project assigned to the currently logged-in developer.
     *
     * @return the Project object representing the assigned project.
     */
    public Project getAssignedProject();

    /**
     * Retrieves the project assigned to a specific developer by their unique developer ID.
     *
     * @param developerId the unique identifier of the developer.
     * @return the Project object representing the assigned project for the specified developer.
     */
    public Project getAssignedProject(int developerId);

    /**
     * Retrieves the list of bugs assigned to the currently logged-in developer.
     *
     * @return a list of Bug objects representing the assigned bugs.
     */
    public List<Bug> getAssignedBugs();
}