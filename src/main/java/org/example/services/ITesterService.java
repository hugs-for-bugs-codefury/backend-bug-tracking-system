package org.example.services;

import org.example.models.Bug;
import org.example.models.Project;
import org.example.models.Tester;

/**
 * The {@code ITesterService} interface extends the {@link IUserService} and provides
 * specific methods for managing tester-related operations within the system. This includes
 * tester registration, login, retrieving tester details, and reporting bugs in projects.
 */
public interface ITesterService extends IUserService {

    /**
     * Retrieves the currently logged-in tester.
     *
     * @return The {@link Tester} object representing the current tester.
     */
    Tester getCurrentTester();

    /**
     * Retrieves a tester by their unique ID.
     *
     * @param testerId The unique ID of the tester.
     * @return The {@link Tester} object representing the tester with the specified ID.
     */
    Tester getTester(int testerId);

    /**
     * Reports a bug in a specified project.
     *
     * @param project     The {@link Project} object in which the bug is found.
     * @param title       The title of the bug.
     * @param description A detailed description of the bug.
     * @param severity    The severity level of the bug (e.g., "Low", "Medium", "High").
     * @return The newly created {@link Bug} object.
     */
    Bug reportBug(Project project, String title, String description, String severity);

    /**
     * Registers a new tester in the system.
     *
     * @param name     The name of the tester.
     * @param email    The email address of the tester.
     * @param password The password for the tester's account.
     * @return The newly registered {@link Tester} object.
     */
    Tester registerUser(String name, String email, String password);

    /**
     * Authenticates a tester with the provided email and password.
     *
     * @param email    The email address of the tester.
     * @param password The password for the tester's account.
     * @return The authenticated {@link Tester} object.
     */
    Tester login(String email, String password);
}
