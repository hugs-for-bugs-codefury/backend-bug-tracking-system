package org.example.services.impl;

import org.example.dao.impl.ProjectDAOImpl;
import org.example.dao.impl.UserDAOImpl;
import org.example.models.Project;
import org.example.models.User;
import org.example.services.IUserService;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

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
            return userDAO.saveUser(name, email, hashPassword(password), role);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void login(String email, String password) {
        String hashedPassword = null;
        try {
            hashedPassword = hashPassword(password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        UserDAOImpl userDAO = new UserDAOImpl();
        User user = userDAO.findByEmail(email);
        if(user == null) {
            throw new IllegalArgumentException("User not found");
        }
        if(!user.getPasswordHash().equals(hashedPassword)) {
            throw new IllegalArgumentException("Invalid password");
        }



        this.user = user;
    }


    @Override
    public User getUser(int userId) {
        UserDAOImpl userDAO = new UserDAOImpl();
        return userDAO.findByID(userId);
    }


    @Override
    public Project getProject(int projectId) {
        if(this.user == null) {
            throw new IllegalArgumentException("Unauthorized access");
        }
        ProjectDAOImpl projectDAO = new ProjectDAOImpl();
        Project p =  projectDAO.findByID(projectId);
        if(p == null) {
            throw new IllegalArgumentException("Project not found");
        }

        return p;

    }



    protected static String hashPassword(String password) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));

        // Convert bytes to hex string
        StringBuilder hexString = new StringBuilder();
        for (byte hashByte : hashBytes) {
            String hex = Integer.toHexString(0xff & hashByte);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

}
