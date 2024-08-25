package org.example.services;

import org.example.models.Bug;

import java.util.List;

/**
 * The {@code IBugService} interface provides methods to manage bug reports within a project.
 * This includes reporting new bugs, assigning bugs to developers, closing bugs, and retrieving
 * bugs based on specific criteria such as the tester who reported them.
 */
public interface IBugService {

    /**
     * Creates a new bug report in the system.
     *
     * @param title       The title of the bug. It should be a brief summary of the issue.
     * @param description A detailed description of the bug, including steps to reproduce, expected behavior, etc.
     * @param severity    The severity level of the bug (e.g., "Low", "Medium", "High", "Critical").
     * @param projectId   The ID of the project where the bug was found.
     * @param createdBy   The ID of the user (typically a tester) who is reporting the bug.
     * @return The newly created {@link Bug} object containing the details of the bug report.
     */
    Bug reportBug(String title, String description, String severity, int projectId, int createdBy);

    /**
     * Assigns a bug to a developer for resolution.
     *
     * @param bugId       The ID of the bug to be assigned.
     * @param developerId The ID of the developer to whom the bug is being assigned.
     * @return The updated {@link Bug} object reflecting the assigned developer.
     */
    Bug assignDeveloper(int bugId, int developerId);

    /**
     * Closes a bug in the system, marking it as resolved or no longer an issue.
     *
     * @param bugId The ID of the bug to be closed.
     */
    void closeBug(int bugId);

    /**
     * Retrieves a list of bugs reported by a specific tester.
     *
     * @param testerId The ID of the tester whose bugs are to be retrieved.
     * @return A {@link List} of {@link Bug} objects that were reported by the specified tester.
     */
    List<Bug> getBugsByTester(int testerId);
}
