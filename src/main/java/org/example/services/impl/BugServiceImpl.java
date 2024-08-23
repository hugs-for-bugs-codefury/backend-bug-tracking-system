package org.example.services.impl;

import org.example.dao.impl.BugDAOImpl;
import org.example.models.Bug;
import org.example.services.IBugService;

import java.time.LocalDateTime;
import java.util.List;

public class BugServiceImpl implements IBugService {
    @Override
    public Bug reportBug(String title, String description, String severity, int projectId, int testerId) {
        BugDAOImpl bugDAO = new BugDAOImpl();
        Bug bug = new Bug(title, description, severity, "open", LocalDateTime.now(), testerId, projectId);
        bug =  bugDAO.saveBug(bug);
        return bug;

    }

    @Override
    public Bug assignDeveloper(int bugId, int developerId) {
        BugDAOImpl bugDAO = new BugDAOImpl();
       return bugDAO.assignDeveloper(bugId, developerId);

    }



    @Override
    public void closeBug(int bugId) {
        BugDAOImpl bugDAO = new BugDAOImpl();
        bugDAO.closeBug(bugId);

    }

    @Override
    public List<Bug> getBugsByTester(int testerId) {
        BugDAOImpl bugDAO = new BugDAOImpl();
        return bugDAO.findBugsByTester(testerId);

    }

}
