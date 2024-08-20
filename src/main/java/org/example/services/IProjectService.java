package org.example.services;

import org.example.models.Project;

import java.util.Date;

public interface IProjectService {

    /**
     * Create a new project
     * @param name
     * @param startDate
     * @param projectManagerId
     * @return
     */
    Project createProject(String name, Date startDate, int projectManagerId);

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
}
