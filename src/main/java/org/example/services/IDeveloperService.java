package org.example.services;

import org.example.models.*;

import java.util.List;

public interface IDeveloperService extends IUserService{
    public Developer registerUser(String name, String email, String password);
    public Developer login(String email, String password);
    public Developer getCurrentDeveloper();
    public Developer getDeveloper(int developerId);
    public void closeBug(int bugId);
    public List<Project> getAssignedProjects();
    public List<Project> getAssignedProjects(int developerId);
    public List<Bug> getAssignedBugs();
}
