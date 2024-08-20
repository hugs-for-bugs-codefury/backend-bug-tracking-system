package org.example.services.impl;

import org.example.models.Bug;
import org.example.models.Developer;
import org.example.models.Project;
import org.example.models.User;
import org.example.services.IDeveloperService;

import java.util.ArrayList;
import java.util.List;

public class DeveloperServiceImpl extends UserServiceImpl  implements IDeveloperService {
    Developer user;

    public DeveloperServiceImpl(User user) {
        if(!user.getRole().equals("Developer")) {
            throw new IllegalArgumentException("User is not a developer");
        }
        this.user = (Developer) user;
    }

    public DeveloperServiceImpl() {
        this.user = null;
    }


    @Override
    public Developer getCurrentDeveloper() {
        return user;
    }

    @Override
    public Developer getDeveloper(int developerId) {
        return null;
    }

    @Override
    public void closeBug(int bugId) {

    }

    @Override
    public Project getAssignedProject() {
        return null;
    }

    @Override
    public Project getAssignedProject(int developerId) {
        return null;
    }

    @Override
    public List<Bug> getAssignedBugs() {
        return new ArrayList<>();
    }

}
