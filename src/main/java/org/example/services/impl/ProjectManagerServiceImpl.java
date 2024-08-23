package org.example.services.impl;

import org.example.dao.impl.ProjectManagerDAOImpl;
import org.example.models.*;
import org.example.services.IProjectManagerService;

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
            return projectManagerDAO.saveUser(name, email, hashPassword(password));

        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ProjectManager getCurrentProjectManager() {
        return user;
    }

    @Override
    public ProjectManager getProjectManager(int projectManagerId) {
        ProjectManagerDAOImpl projectManagerDAO = new ProjectManagerDAOImpl();
        return projectManagerDAO.findByID(projectManagerId);
    }


    @Override
    public Project createProject(String name, LocalDateTime startDate) {
        if (user == null) {
            throw new IllegalArgumentException("Unauthorized access");
        }
        ProjectServiceImpl projectService = new ProjectServiceImpl();
        return projectService.createProject(name, startDate, user.getManagerId());
    }


    @Override
    public void assignTester(Project project, Tester tester) {
        if (user == null) {
            throw new IllegalArgumentException("Unauthorized access");
        }
        ProjectServiceImpl projectService = new ProjectServiceImpl();
        projectService.assignTester(project.getProjectId(), tester.getTesterId());

    }

    @Override
    public void assignDeveloper(Bug bug, Developer developer) {
        if (user == null) {
            throw new IllegalArgumentException("Unauthorized access");
        }
        BugServiceImpl bugService = new BugServiceImpl();
        bugService.assignDeveloper(bug.getId(), developer.getDeveloperId());

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
            throw new IllegalArgumentException("Invalid email or password");
        }

    }
}
