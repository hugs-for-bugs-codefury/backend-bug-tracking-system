package org.example.exceptions.projects;

public class ProjectNotFoundException extends RuntimeException {
    int projectId;
    public ProjectNotFoundException(int projectId) {
        super("Project with id " + projectId + " not found");
        this.projectId = projectId;

    }
}
