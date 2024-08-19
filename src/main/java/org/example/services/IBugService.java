package org.example.services;

import org.example.models.Bug;

public interface IBugService {

    /**
     * Create a new bug report
     * @param title
     * @param description
     * @param severity
     * @param projectId
     * @param createdBy
     * @return
     */
    Bug reportBug(String title, String description, String severity, int projectId, int createdBy);

    /**
     * Assign a bug to a developer
     * @param bugId
     * @param developerId
     */
    void assignBug(int bugId, int developerId);

    /**
     * Close a bug
     * @param bugId
     */
    void closeBug(int bugId);
}
