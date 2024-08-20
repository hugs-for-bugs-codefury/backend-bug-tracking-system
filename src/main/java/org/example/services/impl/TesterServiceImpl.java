package org.example.services.impl;

import org.example.models.Bug;
import org.example.models.Project;
import org.example.models.Tester;
import org.example.models.User;
import org.example.services.ITesterService;

public class TesterServiceImpl extends UserServiceImpl implements ITesterService {

    Tester user;

    public TesterServiceImpl(User user)  {
        if(!user.getRole().equals("tester")) {
            throw new IllegalArgumentException("User is not a tester");
        }
        this.user = (Tester) user;
    }

    public TesterServiceImpl() {
        this.user = null;
    }

    @Override
    public Tester getCurrentTester() {
        return user;
    }

    @Override
    public Tester getTester(int testerId) {
        return null;
    }


    @Override
    public Bug reportBug(Project project, String title, String description, String severity) {
        return null;

    }
}
