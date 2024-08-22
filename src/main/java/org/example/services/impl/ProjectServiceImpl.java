package org.example.services.impl;

import org.example.dao.impl.ProjectDAOImpl;
import org.example.models.Bug;
import org.example.models.Developer;
import org.example.models.Project;
import org.example.models.Tester;
import org.example.services.IProjectService;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class ProjectServiceImpl implements IProjectService {
    @Override
    public Project createProject(String name, LocalDateTime startDate, int projectManagerId) {
        ProjectDAOImpl projectDAO = new ProjectDAOImpl();
        return projectDAO.saveProject(new Project(name, startDate, "in_progress", projectManagerId));

    }

    @Override
    public void assignDeveloper(int projectId, int userId) {
        ProjectDAOImpl projectDAO = new ProjectDAOImpl();
        projectDAO.assignDeveloper(projectId, userId);




    }

    @Override
    public void assignTester(int projectId, int userId) {
        ProjectDAOImpl projectDAO = new ProjectDAOImpl();
        projectDAO.assignTester(projectId, userId);



    }

    @Override
    public Project getProject(int projectId) {
        ProjectDAOImpl projectDAO = new ProjectDAOImpl();
        return projectDAO.findByID(projectId);

    }

    @Override
    public List<Project> getProjectsByTester(int testerId) {
        ProjectDAOImpl projectDAO = new ProjectDAOImpl();
        return projectDAO.findProjectsByTester(testerId);

    }

    @Override
    public List<Project> getProjectsByManager(int managerId) {
        ProjectDAOImpl projectDAO = new ProjectDAOImpl();
        return projectDAO.findProjectsByManager(managerId);
    }

    @Override
    public Project getProjectByDeveloper(int developerId) {
        ProjectDAOImpl projectDAO = new ProjectDAOImpl();
        return projectDAO.findProjectByDeveloper(developerId);
    }

    @Override
    public List<Developer> getAssignedDevelopers(int projectId) {
        ProjectDAOImpl projectDAO = new ProjectDAOImpl();
        return projectDAO.getAssignedDevelopers(projectId);
    }

    @Override
    public List<Tester> getAssignedTesters(int projectId) {
        ProjectDAOImpl projectDAO = new ProjectDAOImpl();
        return projectDAO.getAssignedTesters(projectId);
    }

    @Override
    public List<Bug> getProjectBugs(int projectId) {
        ProjectDAOImpl projectDAO = new ProjectDAOImpl();
        return projectDAO.getProjectBugs(projectId);
    }


}
