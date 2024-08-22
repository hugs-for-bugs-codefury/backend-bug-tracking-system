package org.example.services.impl;


import org.example.dao.impl.TesterDAOImpl;
import org.example.models.Bug;
import org.example.models.Project;
import org.example.models.Tester;
import org.example.models.User;
import org.example.services.ITesterService;

import java.time.LocalDateTime;

public class TesterServiceImpl extends UserServiceImpl implements ITesterService {

    Tester user;

    public TesterServiceImpl(User user)  {
        super(user);

        if(!user.getRole().equals("tester")) {
            throw new IllegalArgumentException("User is not a tester");
        }
        this.user = (Tester) user;
    }

    public TesterServiceImpl() {
        this.user = null;
    }

    @Override
    public Tester registerUser(String name, String email, String password) {
        TesterDAOImpl testerDAO = new TesterDAOImpl();
        try {
            return testerDAO.saveUser(name, email, hashPassword(password));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Tester getCurrentTester() {
        return user;
    }

    @Override
    public Tester getTester(int tester_id) {
        TesterDAOImpl testerDAO = new TesterDAOImpl();
        return testerDAO.findByID(tester_id);
    }


    @Override
    public Bug reportBug(Project project, String title, String description, String severity) {
        BugServiceImpl bugService = new BugServiceImpl();
        return bugService.reportBug(title, description, severity, project.getProjectId(), user.getTesterId());

    }



    @Override
    public void login(String email, String password) {
        TesterDAOImpl testerDAO = new TesterDAOImpl();
        try {

            super.login(email, password);

            this.user = testerDAO.findByEmail(email);

        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid email or password");
        }

    }



}
