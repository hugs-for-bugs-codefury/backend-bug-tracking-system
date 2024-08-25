package org.example.dao;

import org.example.exceptions.users.UserNotFoundException;
import org.example.models.Bug;
import org.example.models.Developer;
import org.example.models.Project;

import java.sql.SQLException;
import java.util.List;

/**
 * IDeveloperDAO defines the data access methods related to Developer entities.
 * This interface abstracts the database operations for the Developer model.
 */
public interface IDeveloperDAO {

    /**
     * Saves a new Developer to the database.
     *
     * @param name the name of the Developer.
     * @param email the email address of the Developer (must be unique).
     * @param password the password for the Developer account (will be stored securely).
     * @return the saved Developer instance.
     * @throws SQLException if a database access error occurs or if a Developer with the same email already exists.
     */
    public Developer saveUser(String name, String email, String password) throws SQLException;

    /**
     * Finds a Developer by their unique identifier (ID).
     *
     * @param developerId the unique identifier of the Developer.
     * @return the Developer with the specified ID.
     * @throws UserNotFoundException if no Developer is found with the specified ID.
     * @throws SQLException if a database access error occurs.
     */
    public Developer findByID(int developerId) throws UserNotFoundException, SQLException;

    /**
     * Finds a Developer by their email address.
     *
     * @param email the email address of the Developer.
     * @return the Developer with the specified email address.
     * @throws UserNotFoundException if no Developer is found with the specified email.
     * @throws SQLException if a database access error occurs.
     */
    public Developer findByEmail(String email) throws UserNotFoundException, SQLException;

    /**
     * Retrieves a list of Projects assigned to a specific Developer.
     *
     * @param developerId the unique identifier of the Developer.
     * @return a list of Projects assigned to the specified Developer.
     * @throws SQLException if a database access error occurs.
     */
    public List<Project> getAssignedProjects(int developerId) throws SQLException;

    /**
     * Retrieves a list of Bugs assigned to a specific Developer.
     *
     * @param developerId the unique identifier of the Developer.
     * @return a list of Bugs assigned to the specified Developer.
     * @throws SQLException if a database access error occurs.
     */
    public List<Bug> getAssignedBugs(int developerId) throws SQLException;
}
