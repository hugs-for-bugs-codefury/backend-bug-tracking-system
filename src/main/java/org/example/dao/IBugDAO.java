package org.example.dao;

import org.example.models.Bug;

import java.util.List;

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

    public Bug saveBug(Bug bug);
    public Bug findBugById(int bugId);
    public List<Bug> findBugsByProject(int projectId);
    public List<Bug> findBugsByTester(int testerId);
    public void deleteBugById(int bugId);
    public Bug assignTester(int bugId, int testerId);
    public Bug assignDeveloper(int bugId, int developerId);
    public Bug closeBug(int bugId);

}
