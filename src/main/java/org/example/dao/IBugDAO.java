package org.example.dao;

import org.example.exceptions.bugs.BugNotAssignableException;
import org.example.exceptions.bugs.BugNotFoundException;
import org.example.models.Bug;

import java.sql.SQLException;
import java.util.List;

/**
 * IBugDAO defines the data access methods related to Bug entities.
 * This interface abstracts the database operations for the Bug model.
 */
public interface IBugDAO {

    /**
     * Saves a new Bug to the database.
     *
     * @param bug the Bug to be saved.
     * @return the saved Bug instance.
     * @throws SQLException if a database access error occurs.
     */
    public Bug saveBug(Bug bug) throws SQLException;

    /**
     * Finds a Bug by its unique identifier (ID).
     *
     * @param bugId the unique identifier of the Bug.
     * @return the Bug with the specified ID.
     * @throws SQLException if a database access error occurs.
     * @throws BugNotFoundException if no Bug is found with the specified ID.
     */
    public Bug findBugById(int bugId) throws SQLException, BugNotFoundException;

    /**
     * Retrieves a list of Bugs associated with a specific Project.
     *
     * @param projectId the unique identifier of the Project.
     * @return a list of Bugs associated with the specified Project.
     * @throws SQLException if a database access error occurs.
     */
    public List<Bug> findBugsByProject(int projectId) throws SQLException;

    /**
     * Retrieves a list of Bugs assigned to a specific Tester.
     *
     * @param testerId the unique identifier of the Tester.
     * @return a list of Bugs assigned to the specified Tester.
     * @throws SQLException if a database access error occurs.
     */
    public List<Bug> findBugsByTester(int testerId) throws SQLException;

    /**
     * Deletes a Bug by its unique identifier (ID).
     *
     * @param bugId the unique identifier of the Bug to be deleted.
     * @throws SQLException if a database access error occurs.
     */
    public void deleteBugById(int bugId) throws SQLException;

    /**
     * Assigns a Developer to a Bug.
     *
     * @param bugId the unique identifier of the Bug.
     * @param developerId the unique identifier of the Developer.
     * @return the updated Bug instance with the Developer assigned.
     * @throws SQLException if a database access error occurs.
     * @throws BugNotAssignableException if the Bug cannot be assigned to the Developer.
     */
    public Bug assignDeveloper(int bugId, int developerId) throws SQLException, BugNotAssignableException;

    /**
     * Closes a Bug by its unique identifier (ID).
     *
     * @param bugId the unique identifier of the Bug to be closed.
     * @return the updated Bug instance with the closed status.
     * @throws SQLException if a database access error occurs.
     */
    public Bug closeBug(int bugId) throws SQLException;
}
