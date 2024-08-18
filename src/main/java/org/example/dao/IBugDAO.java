package org.example.dao;


import org.example.models.Bug;

/**
 * IBugDAO defines the data access methods related to Bug entities.
 * This interface abstracts the database operations for the Bug model.
 *
 * Example methods include but are not limited to:
 * - Bug save(Bug bug): Save a new bug to the database.
 * - Bug findById(int id): Retrieve a bug by its ID.
 * - List<Bug> findAllByProjectId(int projectId): Retrieve a list of bugs for a specific project.
 * - void deleteById(int id): Delete a bug by its ID.
 */

public interface IBugDAO {

    /**
     * Create a new bug with the given title, description, severity, project ID, and created by user ID.
     * @param title The title of the bug.
     * @param description The description of the bug.
     * @param severity The severity of the bug.
     * @param projectId The ID of the project the bug belongs to.
     * @param createdBy The ID of the user who created the bug.
     * @return The created bug.
     */
    Bug reportBug(String title, String description, String severity, int projectId, int createdBy);

    /**
     * Assign a bug to a developer with the given bug ID and developer ID.
     * @param bugId The ID of the bug.
     * @param developerId The ID of the developer.
     */
    void assignBug(int bugId, int developerId);

    /**
     * Get a bug by its ID.
     * @param bugId The ID of the bug.
     * @return The bug with the given ID.
     */
    void closeBug(int bugId);

}
