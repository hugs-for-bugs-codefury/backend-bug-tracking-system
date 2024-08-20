package org.example.services.impl;

import org.example.models.*;
import org.example.services.IProjectManagerService;

public class ProjectManagerServiceImpl extends UserServiceImpl implements IProjectManagerService   {
    ProjectManager user;

    public ProjectManagerServiceImpl(User user) {
        if(!user.getRole().equals("projectManager")) {
            throw new IllegalArgumentException("User is not a project manager");
        }

        this.user = (ProjectManager) user;
    }

    public ProjectManagerServiceImpl() {
        this.user = null;
    }

    @Override
    public ProjectManager getCurrentProjectManager() {
        return user;
    }

    @Override
    public Project createProject(String title, String description, String startDate) {
        return null;
    }

    @Override
    public ProjectManager getTester(int projectManagerId) {
        return null;
    }

    @Override
    public void assignTester(Project project, Tester tester) {

    }

    @Override
    public void assignDeveloper(Bug bug, Developer developer) {

    }
}
