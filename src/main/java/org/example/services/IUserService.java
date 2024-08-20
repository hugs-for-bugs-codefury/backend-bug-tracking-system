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
    User registerUser(String username, String email, String password, String role);

    /**
     * Login a user
     * @param email
     * @param password
     * @return
     */
    User loginUser(String email, String password);

    /**
     * Get a user by id
     * @param userId
     * @return
     */
    User getUser(int userId);


    /**
     * Get a project by id
     * @param projectId
     * @return
     */
    Project getProject(int projectId);

}
