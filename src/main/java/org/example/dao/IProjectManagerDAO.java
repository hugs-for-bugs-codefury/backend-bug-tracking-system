package org.example.dao;

import org.example.exceptions.users.UserNotFoundException;
import org.example.models.ProjectManager;

import java.sql.SQLException;

/**
 * IProjectManagerDAO defines the data access methods related to ProjectManager entities.
 * This interface abstracts the database operations for the ProjectManager model.
 */
public interface IProjectManagerDAO {

    /**
     * Saves a new ProjectManager to the database.
     *
     * @param name the name of the ProjectManager.
     * @param email the email address of the ProjectManager (must be unique).
     * @param password the password for the ProjectManager account (will be stored securely).
     * @return the saved ProjectManager instance.
     * @throws SQLException if a database access error occurs or if a ProjectManager with the same email already exists.
     */
    public ProjectManager saveUser(String name, String email, String password) throws SQLException;

    /**
     * Finds a ProjectManager by their unique identifier (ID).
     *
     * @param managerId the unique identifier of the ProjectManager.
     * @return the ProjectManager with the specified ID.
     * @throws SQLException if a database access error occurs.
     * @throws UserNotFoundException if no ProjectManager is found with the specified ID.
     */
    public ProjectManager findByID(int managerId) throws SQLException, UserNotFoundException;

    /**
     * Finds a ProjectManager by their email address.
     *
     * @param email the email address of the ProjectManager.
     * @return the ProjectManager with the specified email address.
     * @throws SQLException if a database access error occurs.
     * @throws UserNotFoundException if no ProjectManager is found with the specified email.
     */
    public ProjectManager findByEmail(String email) throws SQLException, UserNotFoundException;
}
