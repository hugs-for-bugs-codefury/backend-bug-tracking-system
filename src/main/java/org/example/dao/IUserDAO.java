package org.example.dao;

import org.example.models.User;

/**
 * IUserDAO defines the data access methods related to User entities.
 * This interface abstracts the database operations for the User model.
 *
 * Example methods include but are not limited to:
 * - User save(User user): Save a new user to the database.
 * - User findById(int id): Retrieve a user by their ID.
 * - User findByEmail(String email): Retrieve a user by their email.
 * - List<User> findAll(): Retrieve a list of all users.
 * - void deleteById(int id): Delete a user by their ID.
 */

public interface IUserDAO {

    /**
     * Register a new user with the given username, email, password, and role.
     * @param username The username of the user.
     * @param email The email of the user.
     * @param password The password of the user.
     * @param role The role of the user.
     * @return The registered user.
     */
    User registerUser(String username, String email, String password, String role);


    /**
     * Login a user with the given email and password.
     * @param email The email of the user.
     * @param password The password of the user.
     * @return The logged in user.
     */
    User loginUser(String email, String password);


    /**
     * Get a user by their ID.
     * @param userId The ID of the user.
     * @return The user with the given ID.
     */
    User getUser(int userId);

}
