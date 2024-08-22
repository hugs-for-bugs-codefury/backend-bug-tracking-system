package org.example.services;

import org.example.models.Bug;

import java.util.List;

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
    Bug assignDeveloper(int bugId, int developerId);

    /**
     * Close a bug
     * @param bugId
     */
    void closeBug(int bugId);

    List<Bug> getBugsByTester(int testerId);
}
