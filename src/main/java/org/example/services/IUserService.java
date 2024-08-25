package org.example.services;

import org.example.models.Project;
import org.example.models.User;

public interface IUserService {

    /**
     * Register a new user
     * @param name
     * @param email
     * @param password
     * @param role
     * @return
     */
    User registerUser(String name, String email, String password, String role) ;



    /**
     * Get a user by id
     * @param userId
     * @return
     */
    User getUser(int userId) ;


    /**
     * Get a project by id
     * @param projectId
     * @return
     */
    Project getProject(int projectId) ;

    User login(String email, String password);

}
