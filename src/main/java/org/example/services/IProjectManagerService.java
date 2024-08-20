package org.example.services;

import org.example.models.*;

public interface IProjectManagerService extends IUserService {

    public ProjectManager getCurrentProjectManager();
    public ProjectManager getTester(int projectManagerId);
    Project createProject(String title, String description, String startDate);
    public void assignTester(Project project, Tester tester);
    public void assignDeveloper(Bug bug, Developer developer);


}
