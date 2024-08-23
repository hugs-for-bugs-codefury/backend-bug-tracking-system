package org.example.services.impl;

import org.example.dao.impl.ProjectDAOImpl;
import org.example.models.Bug;
import org.example.models.Developer;
import org.example.models.Project;
import org.example.models.Tester;
import org.example.services.IProjectService;

import java.time.LocalDateTime;

import java.util.List;

public class ProjectServiceImpl implements IProjectService {
    @Override
    public Project createProject(String name, LocalDateTime startDate, int projectManagerId) {
        ProjectDAOImpl projectDAO = new ProjectDAOImpl();
        try {
            return projectDAO.saveProject(new Project(name, startDate, "in_progress", projectManagerId));
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public void assignDeveloper(int projectId, int developerId) {
        ProjectDAOImpl projectDAO = new ProjectDAOImpl();
        try {
            projectDAO.assignDeveloper(projectId, developerId);
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public void assignTester(int projectId, int tester_id) {
        ProjectDAOImpl projectDAO = new ProjectDAOImpl();
        try {
            projectDAO.assignTester(projectId, tester_id);
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }



    }

    @Override
    public Project getProject(int projectId) {
        ProjectDAOImpl projectDAO = new ProjectDAOImpl();
        try {
            return projectDAO.findByID(projectId);
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public List<Project> getProjectsByTester(int testerId) {
        ProjectDAOImpl projectDAO = new ProjectDAOImpl();
        try {
            return projectDAO.findProjectsByTester(testerId);
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public List<Project> getProjectsByManager(int managerId) {
        ProjectDAOImpl projectDAO = new ProjectDAOImpl();
        try {
            return projectDAO.findProjectsByManager(managerId);

        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Project> getProjectByDeveloper(int developerId) {
        ProjectDAOImpl projectDAO = new ProjectDAOImpl();
        try {
            return projectDAO.findProjectsByDeveloper(developerId);
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Developer> getAssignedDevelopers(int projectId) {
        ProjectDAOImpl projectDAO = new ProjectDAOImpl();
        try {
            return projectDAO.getAssignedDevelopers(projectId);
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public List<Tester> getAssignedTesters(int projectId) {
        ProjectDAOImpl projectDAO = new ProjectDAOImpl();
        try {
            return projectDAO.getAssignedTesters(projectId);
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public List<Bug> getProjectBugs(int projectId) {
        ProjectDAOImpl projectDAO = new ProjectDAOImpl();
        try {
            return projectDAO.getProjectBugs(projectId);
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }


}
