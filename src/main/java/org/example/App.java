package org.example;

import org.example.factory.impl.ServiceFactory;
import org.example.models.*;
import org.example.services.impl.DeveloperServiceImpl;
import org.example.services.impl.ProjectManagerServiceImpl;
import org.example.services.impl.TesterServiceImpl;
import org.example.services.impl.UserServiceImpl;

import java.time.LocalDateTime;
import java.util.List;


public class App 
{
    public static void main( String[] args )
    {
        UserServiceImpl userService = (UserServiceImpl) ServiceFactory.getService("UserService");

//        Register different types of users
        User user1 =  userService.registerUser("user1", "user1@gmail.com", "password", "project_manager");

        User user2 = userService.registerUser("user2", "user2@gmail.com", "password", "developer");

        User user3 = userService.registerUser("user3", "user3@gmail.com", "password", "developer");

        User user4 = userService.registerUser("user4", "user4@gmail.com", "password", "developer");

        User user5 = userService.registerUser("user5", "user5@gmail.com", "password", "tester");

        User user6 = userService.registerUser("user6", "user6@gmail.com", "password", "tester");

        User user7 = userService.registerUser("user7", "user7@gmail.com", "password", "tester");

        User user8 = userService.registerUser("user8", "user8@gmail.com", "password", "tester");

        // ================================== Service Factory ==================================


        // get project manager service as user1 (project manager)
        ProjectManagerServiceImpl projectManagerService = (ProjectManagerServiceImpl) ServiceFactory.getService("ProjectManagerService", user1);

        // get developer service as user2 (developer)
        DeveloperServiceImpl developerService = (DeveloperServiceImpl) ServiceFactory.getService("DeveloperService", user2);

        // get tester service as user5 (tester)
        TesterServiceImpl testerService = (TesterServiceImpl) ServiceFactory.getService("TesterService", user5);

        // =====================================================================================

        // ================================== Project Manager ==================================


        // 1. Project manager creates a project
        Project project1 = projectManagerService.createProject("Project 1", "Project 1 Description", LocalDateTime.now().toString());
        Project project2 = projectManagerService.createProject("Project 2", "Project 2 Description", LocalDateTime.now().toString());


//      // 2. Project manager assigns testers to the project

        projectManagerService.assignTester(project1, (Tester) user5);
        projectManagerService.assignTester(project1, (Tester)  user6);
        projectManagerService.assignTester(project1, (Tester) user7);



        // =================================== Tester ==================================


        // 3. Tester reports a bug
        Bug bug1 = testerService.reportBug(project1, "Bug 1", "Bug 1 Description", "High");
        Bug bug2 = testerService.reportBug(project1, "Bug 2", "Bug 2 Description", "Medium");
        Bug bug3 = testerService.reportBug(project1, "Bug 3", "Bug 3 Description", "Low");

        // 4. Manager assigns a bug to a developer
        projectManagerService.assignDeveloper(project1.getBugs().get(0), developerService.getDeveloper(user2.getId()));
        projectManagerService.assignDeveloper(project1.getBugs().get(1), developerService.getDeveloper(user2.getId()));

        // =================================== Developer ==================================
        // 5. Developer starts working on the bug

        List<Bug> bugs = developerService.getAssignedBugs();
        for (Bug bug : bugs) {
            developerService.closeBug(bug.getId());
        }









    }
}
