package org.example.dao;

import org.example.models.Bug;
import org.example.models.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
    public  User findByEmail(String email) throws Exception;
    public User findByID(int id) throws Exception;
    public User saveUser(String name, String email, String password, String role) throws Exception;
    public boolean comparePassword(String email, String password) throws Exception;
    public LocalDateTime updateLastLogin(int user_id) throws Exception;
}
