package org.example.dao;

import org.example.exceptions.bugs.BugNotAssignableException;
import org.example.exceptions.bugs.BugNotFoundException;
import org.example.models.Bug;

import java.sql.SQLException;
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

    public Bug saveBug(Bug bug) throws SQLException;
    public Bug findBugById(int bugId) throws SQLException, BugNotFoundException;
    public List<Bug> findBugsByProject(int projectId) throws SQLException;
    public List<Bug> findBugsByTester(int testerId) throws SQLException;
    public void deleteBugById(int bugId) throws SQLException;
    public Bug assignDeveloper(int bugId, int developerId) throws SQLException, BugNotAssignableException;
    public Bug closeBug(int bugId) throws SQLException;

}
