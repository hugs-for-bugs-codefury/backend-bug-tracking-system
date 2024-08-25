package org.example.services.impl;

import org.example.dao.impl.DeveloperDAOImpl;
import org.example.dao.impl.ProjectManagerDAOImpl;
import org.example.exceptions.users.UserNotAuthorizedException;
import org.example.exceptions.users.UserNotFoundException;
import org.example.models.Bug;
import org.example.models.Developer;
import org.example.models.Project;
import org.example.models.User;
import org.example.services.IDeveloperService;
import org.example.util.Algorithms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeveloperServiceImpl extends UserServiceImpl  implements IDeveloperService {
    Developer user;

    public DeveloperServiceImpl(User user) throws UserNotAuthorizedException {
        super(user);

        if(!user.getRole().equals("developer")) {
            throw new UserNotAuthorizedException("User is not a developer");
        }
        this.user = (Developer) user;
    }

    public DeveloperServiceImpl() {
        this.user = null;
    }


    @Override
    public Developer registerUser(String name, String email, String password) {
        DeveloperDAOImpl developerDAO = new DeveloperDAOImpl();
        try{
            return developerDAO.saveUser(name, email, Algorithms.hashPassword(password));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Developer getCurrentDeveloper() {
        return user;
    }

    @Override
    public Developer getDeveloper(int developerId) {
        try {

            DeveloperDAOImpl developerDAO = new DeveloperDAOImpl();
            return developerDAO.findByID(developerId);
        }catch (UserNotFoundException | SQLException e) {
            throw new RuntimeException(e.getMessage());

        }

    }

    @Override
    public void closeBug(int bugId) {
        BugServiceImpl bugService = new BugServiceImpl();
        bugService.closeBug(bugId);




    }

    @Override
    public List<Project> getAssignedProjects() {
        DeveloperDAOImpl developerDAO = new DeveloperDAOImpl();
        try {
            return developerDAO.getAssignedProjects(user.getDeveloperId());

        }catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public List<Project> getAssignedProjects(int developerId) {
        DeveloperDAOImpl developerDAO = new DeveloperDAOImpl();
        try {
            return developerDAO.getAssignedProjects(developerId);

        }catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Bug> getAssignedBugs() {
        DeveloperDAOImpl developerDAO = new DeveloperDAOImpl();
        return developerDAO.getAssignedBugs(user.getDeveloperId());
    }


    @Override
    public Developer login(String email, String password) {
        DeveloperDAOImpl developerDAO = new DeveloperDAOImpl();
        try {

            User loggedIn = super.login(email, password);

            this.user = developerDAO.findByEmail(email);
            this.user.setLastLogin(loggedIn.getLastLogin());
            return this.user;

        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid email or password");
        }

    }



}
