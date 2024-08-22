package org.example.services;

import org.example.models.Project;
import org.example.models.User;

public interface IUserService {

    /**
     * Register a new user
     * @param username
     * @param email
     * @param password
     * @param role
     * @return
     */
    User registerUser(String username, String email, String password, String role) throws Exception;

    /**
     * Login a user
     * @param email
     * @param password
     * @return
     */
    void login(String email, String password) throws Exception;

    /**
     * Get a user by id
     * @param userId
     * @return
     */
    User getUser(int userId) throws Exception;


    /**
     * Get a project by id
     * @param projectId
     * @return
     */
    Project getProject(int projectId) throws Exception;

}
