package org.example.services.impl;

import org.example.models.Project;
import org.example.services.IProjectService;

import java.util.Date;

public class ProjectServiceImpl implements IProjectService {
    @Override
    public Project createProject(String name, Date startDate, int projectManagerId) {
        return null;
    }

    @Override
    public void assignDeveloper(int projectId, int userId) {

    }

    @Override
    public void assignTester(int projectId, int userId) {

    }

    @Override
    public Project getProject(int projectId) {
        return null;
    }
}
