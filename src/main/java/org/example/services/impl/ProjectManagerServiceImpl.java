package org.example.services.impl;

import org.example.dao.impl.ProjectManagerDAOImpl;
import org.example.exceptions.users.UserNotAuthorizedException;
import org.example.models.*;
import org.example.services.IProjectManagerService;
import org.example.util.Algorithms;

import java.time.LocalDateTime;

public class ProjectManagerServiceImpl extends UserServiceImpl implements IProjectManagerService {
    ProjectManager user;

    public ProjectManagerServiceImpl(User user) {
        super(user);

        if (!user.getRole().equals("projectManager")) {
            throw new IllegalArgumentException("User is not a project manager");
        }

        this.user = (ProjectManager) user;
    }

    public ProjectManagerServiceImpl() {
        this.user = null;
    }

    @Override
    public ProjectManager registerUser(String name, String email, String password) {
        ProjectManagerDAOImpl projectManagerDAO = new ProjectManagerDAOImpl();
        try {
            return projectManagerDAO.saveUser(name, email, Algorithms.hashPassword(password));

        }catch (Exception e) {

            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public ProjectManager getCurrentProjectManager() {
        return user;
    }

    @Override
    public ProjectManager getProjectManager(int projectManagerId) {
        ProjectManagerDAOImpl projectManagerDAO = new ProjectManagerDAOImpl();
        try {
            return projectManagerDAO.findByID(projectManagerId);
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }


    @Override
    public Project createProject(String name, LocalDateTime startDate) {
        if (user == null) {
            throw new UserNotAuthorizedException("Unauthorized access, please login as a project manager");
        }
        ProjectServiceImpl projectService = new ProjectServiceImpl();

        return projectService.createProject(name, startDate, user.getManagerId());
    }


    @Override
    public void assignTesterToProject(int projectId, int testerId) {
        if (user == null) {
            throw new UserNotAuthorizedException("Unauthorized access, please login as a project manager");
        }
        ProjectServiceImpl projectService = new ProjectServiceImpl();
        projectService.assignTester(projectId, testerId);

    }

    @Override
    public void assignDeveloperToProject(int projectId, int developerId) {
        if (user == null) {
            throw new UserNotAuthorizedException("Unauthorized access, please login as a project manager");
        }

        ProjectServiceImpl projectService = new ProjectServiceImpl();
        projectService.assignDeveloper(projectId, developerId);

    }

    @Override
    public void assignDeveloperToBug(int bugId, int developerId) {
        if (user == null) {
            throw new UserNotAuthorizedException("Unauthorized access, please login as a project manager");
        }

        BugServiceImpl bugService = new BugServiceImpl();
        bugService.assignDeveloper(bugId, developerId);

    }


    @Override
    public ProjectManager login(String email, String password) {
        ProjectManagerDAOImpl projectManagerDAO = new ProjectManagerDAOImpl();
        try {

            User loggedIn = super.login(email, password);

            this.user = projectManagerDAO.findByEmail(email);
            this.user.setLastLogin(loggedIn.getLastLogin());

            return this.user;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }
}
