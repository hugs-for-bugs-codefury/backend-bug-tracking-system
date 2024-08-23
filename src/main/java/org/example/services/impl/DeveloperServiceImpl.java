package org.example.services.impl;

import org.example.dao.impl.DeveloperDAOImpl;
import org.example.dao.impl.ProjectManagerDAOImpl;
import org.example.models.Bug;
import org.example.models.Developer;
import org.example.models.Project;
import org.example.models.User;
import org.example.services.IDeveloperService;

import java.util.ArrayList;
import java.util.List;

public class DeveloperServiceImpl extends UserServiceImpl  implements IDeveloperService {
    Developer user;

    public DeveloperServiceImpl(User user) {
        super(user);

        if(!user.getRole().equals("developer")) {
            throw new IllegalArgumentException("User is not a developer");
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
            return developerDAO.saveUser(name, email,hashPassword(password));
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
        DeveloperDAOImpl developerDAO = new DeveloperDAOImpl();
        return developerDAO.findByID(developerId);
    }

    @Override
    public void closeBug(int bugId) {
        BugServiceImpl bugService = new BugServiceImpl();
        bugService.closeBug(bugId);



    }

    @Override
    public Project getAssignedProject() {
        DeveloperDAOImpl developerDAO = new DeveloperDAOImpl();
        return developerDAO.getAssignedProject(user.getDeveloperId());

    }

    @Override
    public Project getAssignedProject(int developerId) {
        DeveloperDAOImpl developerDAO = new DeveloperDAOImpl();
        return developerDAO.getAssignedProject(developerId);
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
