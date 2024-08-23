package org.example.services.impl;

import org.example.dao.impl.BugDAOImpl;
import org.example.dao.impl.DeveloperDAOImpl;
import org.example.models.Bug;
import org.example.models.Developer;
import org.example.services.IBugService;

import java.time.LocalDateTime;
import java.util.List;

public class BugServiceImpl implements IBugService {
    @Override
    public Bug reportBug(String title, String description, String severity, int projectId, int testerId) {
        BugDAOImpl bugDAO = new BugDAOImpl();
        Bug bug = new Bug(title, description, severity, "open", LocalDateTime.now(), testerId, projectId);
        try {
            return bugDAO.saveBug(bug);
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public Bug assignDeveloper(int bugId, int developerId) {

        BugDAOImpl bugDAO = new BugDAOImpl();


        try {
            return bugDAO.assignDeveloper(bugId, developerId);
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }




    }



    @Override
    public void closeBug(int bugId)  {
        BugDAOImpl bugDAO = new BugDAOImpl();

        try {
            bugDAO.closeBug(bugId);
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }


    }

    @Override
    public List<Bug> getBugsByTester(int testerId) {
        BugDAOImpl bugDAO = new BugDAOImpl();

        try {
            return bugDAO.findBugsByTester(testerId);
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

}
