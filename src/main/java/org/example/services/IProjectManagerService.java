package org.example.services;

import org.example.models.*;

import java.time.LocalDateTime;

public interface IProjectManagerService extends IUserService {
    public ProjectManager registerUser(String name, String email, String password);
    public ProjectManager login(String email, String password);
    public ProjectManager getCurrentProjectManager();
    public ProjectManager getProjectManager(int projectManagerId);
    Project createProject(String name, LocalDateTime startDate);
    public void assignTesterToProject(int projectId, int testerId);
    public void assignDeveloperToProject(int projectId, int developerId);
    public void assignDeveloperToBug(int bugId, int developerId );


}
