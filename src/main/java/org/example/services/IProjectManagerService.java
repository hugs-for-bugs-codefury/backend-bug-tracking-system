package org.example.services;

import org.example.models.*;

import java.time.LocalDateTime;

public interface IProjectManagerService extends IUserService {
    public ProjectManager registerUser(String name, String email, String password);
    public ProjectManager login(String email, String password);
    public ProjectManager getCurrentProjectManager();
    public ProjectManager getProjectManager(int projectManagerId);
    Project createProject(String name, LocalDateTime startDate);
    public void assignTester(Project project, Tester tester);
    public void assignDeveloper(Bug bug, Developer developer);


}
