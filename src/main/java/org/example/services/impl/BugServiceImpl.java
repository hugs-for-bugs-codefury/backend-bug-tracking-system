package org.example.services.impl;

import org.example.models.Bug;
import org.example.services.IBugService;

public class BugServiceImpl implements IBugService {
    @Override
    public Bug reportBug(String title, String description, String severity, int projectId, int createdBy) {
        return null;
    }

    @Override
    public void assignBug(int bugId, int developerId) {

    }

    @Override
    public void closeBug(int bugId) {

    }
}
