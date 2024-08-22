package org.example.services;

import org.example.models.Bug;
import org.example.models.Developer;
import org.example.models.Project;
import org.example.models.Tester;

import java.util.List;

public interface IDeveloperService extends IUserService{
    public Developer registerUser(String name, String email, String password);
    public Developer getCurrentDeveloper();
    public Developer getDeveloper(int developerId);
    public void closeBug(int bugId);
    public Project getAssignedProject();
    public Project getAssignedProject(int developerId);
    public List<Bug> getAssignedBugs();
}
