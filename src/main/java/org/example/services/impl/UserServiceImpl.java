package org.example.services.impl;

import org.example.dao.impl.ProjectDAOImpl;
import org.example.dao.impl.UserDAOImpl;
import org.example.exceptions.users.UserNotAuthorizedException;
import org.example.exceptions.users.UserNotFoundException;
import org.example.models.Project;
import org.example.models.User;
import org.example.services.IUserService;
import org.example.util.Algorithms;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.LocalDateTime;

public class UserServiceImpl  implements IUserService {

    User user;


    public UserServiceImpl(User user) {
        this.user = user;
    }

    public UserServiceImpl() {
        this.user = null;
    }



    @Override
    public User registerUser(String name, String email, String password, String role)  {

        try {

            UserDAOImpl userDAO = new UserDAOImpl();
            return userDAO.saveUser(name, email, Algorithms.hashPassword(password), role);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public User login(String email, String password) {
        String hashedPassword = null;
        try {
            hashedPassword = Algorithms.hashPassword(password);

            UserDAOImpl userDAO = new UserDAOImpl();
            User user = userDAO.findByEmail(email);
            if (user == null) {
                throw new UserNotFoundException("User not found");
            }
            if (!user.getPasswordHash().equals(hashedPassword)) {
                throw new UserNotAuthorizedException("Invalid password");
            }

            user.setLastLogin(userDAO.updateLastLogin(user.getId()));
            this.user = user;
            return user;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }


    @Override
    public User getUser(int userId) {
        UserDAOImpl userDAO = new UserDAOImpl();
        try {
            return userDAO.findByID(userId);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }


    @Override
    public Project getProject(int projectId) {
        if(this.user == null) {
            throw new UserNotAuthorizedException("Unauthorized access, please login");
        }
        ProjectDAOImpl projectDAO = new ProjectDAOImpl();

        try {
            return projectDAO.findByID(projectId);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }





}
