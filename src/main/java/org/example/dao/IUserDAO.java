package org.example.dao;

import org.example.exceptions.users.UserNotAuthorizedException;
import org.example.exceptions.users.UserNotFoundException;
import org.example.models.User;

import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * IUserDAO defines the data access methods related to User entities.
 * This interface abstracts the database operations for the User model.
 *
 * Implementations of this interface should handle operations such as saving a user,
 * finding a user by ID or email, updating a user's last login time, and more.
 */
public interface IUserDAO {

    /**
     * Finds a User by their email address.
     *
     * @param email the email address of the User to be retrieved.
     * @return the User with the specified email address.
     * @throws SQLException if a database access error occurs.
     * @throws UserNotFoundException if no user is found with the specified email.
     */
    public User findByEmail(String email) throws SQLException, UserNotFoundException;

    /**
     * Finds a User by their unique identifier (ID).
     *
     * @param id the unique identifier of the User to be retrieved.
     * @return the User with the specified ID.
     * @throws SQLException if a database access error occurs.
     * @throws UserNotFoundException if no user is found with the specified ID.
     */
    public User findByID(int id) throws SQLException, UserNotFoundException;

    /**
     * Saves a new User to the database.
     *
     * @param name the name of the User.
     * @param email the email address of the User (must be unique).
     * @param password the password for the User (will be stored securely).
     * @param role the role of the User (e.g., "tester", "developer", "manager").
     * @return the saved User instance, including any generated fields like ID.
     * @throws SQLException if a database access error occurs or if a user with the same email already exists.
     */
    public User saveUser(String name, String email, String password, String role) throws SQLException;

    /**
     * Updates the last login timestamp of a User.
     *
     * @param user_id the unique identifier of the User whose last login time is to be updated.
     * @return the updated last login timestamp.
     * @throws SQLException if a database access error occurs.
     */
    public LocalDateTime updateLastLogin(int user_id) throws SQLException;
}
