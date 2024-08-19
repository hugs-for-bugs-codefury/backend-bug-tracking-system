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
     * Assign a team member to a project
     * @param projectId
     * @param userId
     * @param role
     */
    void assignTeamMember(int projectId, int userId, String role);

    /**
     * Get a project by id
     * @param projectId
     * @return
     */
    Project getProject(int projectId);
}
