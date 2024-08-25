package org.example.services;

import org.example.models.Bug;
import org.example.models.Project;
import org.example.models.Tester;

public interface ITesterService extends IUserService {
    public Tester getCurrentTester();
    public Tester getTester(int testerId);
    public Bug reportBug(Project project, String title, String description, String severity);
    public Tester registerUser(String name, String email, String password);
    public Tester login(String email, String password);

}
