package org.example.services;

import org.example.models.Bug;
import org.example.models.Developer;
import org.example.models.Project;
import org.example.models.Tester;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface IProjectService {

    /**
     * Create a new project
     * @param name
     * @param startDate
     * @param projectManagerId
     * @return
     */
    Project createProject(String name, LocalDateTime startDate, int projectManagerId);

    /**
     * Assign a developer to a project
     * @param projectId
     * @param userId
     */
    void assignDeveloper(int projectId, int userId);


    /**
     * Assign a tester to a project
     * @param projectId
     * @param userId
     */
    void assignTester(int projectId, int userId);



    /**
     * Get a project by id
     * @param projectId
     * @return
     */
    Project getProject(int projectId);

    /**
     * Get all projects assigned to a tester
     * @param testerId
     * @return
     */
    public List<Project> getProjectsByTester(int testerId);

    public List<Project> getProjectsByManager(int managerId);


    public  Project getProjectByDeveloper(int developerId);

    public List<Developer> getAssignedDevelopers(int projectId);
    public List<Tester> getAssignedTesters(int projectId);
    public List<Bug> getProjectBugs(int projectId);
}
