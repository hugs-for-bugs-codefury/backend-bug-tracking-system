package org.example;

import org.example.factory.impl.ServiceFactory;

import org.example.models.*;

import org.example.services.IDeveloperService;
import org.example.services.IProjectManagerService;
import org.example.services.ITesterService;
import org.example.util.DummyData;


import java.time.LocalDateTime;
import java.util.List;


public class App {
    public static void main(String[] args) {
        try {

            // ================================== Service Factory ==================================

            IProjectManagerService projectManagerService = (IProjectManagerService) ServiceFactory.getService("ProjectManagerService");

            IDeveloperService developerService = (IDeveloperService) ServiceFactory.getService("DeveloperService");
            ITesterService testerService = (ITesterService) ServiceFactory.getService("TesterService");

            // ================================== Registering Users ==================================

            ProjectManager pm = projectManagerService.registerUser("Project Manager", DummyData.getPersonEmail("project_manager"), "password1");
            Developer dev1 = developerService.registerUser("Developer 1", DummyData.getPersonEmail("developer"), "password2");
            Developer dev2 = developerService.registerUser("Developer 2", DummyData.getPersonEmail("developer"), "password3");
            Tester tester1 = testerService.registerUser("Tester 1", DummyData.getPersonEmail("tester"), "password4");
            Tester tester2 = testerService.registerUser("Tester 2", DummyData.getPersonEmail("tester"), "password5");


            // ================================== Project Manager ==================================

            System.out.println("=".repeat(20) + " Project Manager " + "=".repeat(20));

            // 1. Project manager logs in
            System.out.println("▶️Project manager logs in");


            pm = projectManagerService.login(pm.getEmail(), "password1");
            System.out.println(pm);


            // 2. Project manager creates a project
            System.out.println("▶️Creating a project");
            Project project1 = projectManagerService.createProject(DummyData.getProjectName(), LocalDateTime.now());
            Project project2 = projectManagerService.createProject(DummyData.getProjectName(), LocalDateTime.now());
            Project project3 = projectManagerService.createProject(DummyData.getProjectName(), LocalDateTime.now());
            Project project4 = projectManagerService.createProject(DummyData.getProjectName(), LocalDateTime.now());


            System.out.println(project1);
            System.out.println(project2);


//      // 3. Project manager assigns testers and developers to the project
            System.out.println("▶️Assigning testers to the project");
            projectManagerService.assignTesterToProject(project1.getProjectId(), tester1.getTesterId());
            projectManagerService.assignTesterToProject(project2.getProjectId(), tester1.getTesterId());
            projectManagerService.assignTesterToProject(project3.getProjectId(), tester2.getTesterId());

            projectManagerService.assignDeveloperToProject(project1.getProjectId(), dev1.getDeveloperId());


            // =================================== Tester ==================================
            System.out.println("=".repeat(20) + " Tester " + "=".repeat(20));


            // 1. Tester logs in
            System.out.println("▶️Tester1 logs in");

            tester1 = testerService.login(tester1.getEmail(), "password4");
            System.out.println(tester1);


            // 2. Tester reports bugs
            System.out.println("▶️Reporting bugs");
            Bug bug1 = testerService.reportBug(project1, DummyData.getBugTitle(), DummyData.getBugDescription(), DummyData.getBugSeverity());
            Bug bug2 = testerService.reportBug(project1, DummyData.getBugTitle(), DummyData.getBugDescription(), DummyData.getBugSeverity());
            Bug bug3 = testerService.reportBug(project1, DummyData.getBugTitle(), DummyData.getBugDescription(), DummyData.getBugSeverity());
            Bug bug4 = testerService.reportBug(project2, DummyData.getBugTitle(), DummyData.getBugDescription(), DummyData.getBugSeverity());

            System.out.println("▶️Bugs reported: ");
            System.out.println(bug1);
            System.out.println(bug2);
            System.out.println(bug3);
            System.out.println(bug4);


            // =================================== Project Manager ==================================
            System.out.println("=".repeat(20) + " Project Manager " + "=".repeat(20));

            // 1. Manager assigns a bug to a developer
            System.out.println("▶️Assigning a bug to a developer");
            projectManagerService.assignDeveloperToBug(bug1.getId(), dev1.getDeveloperId());
            projectManagerService.assignDeveloperToBug(bug2.getId(), dev1.getDeveloperId());


            // =================================== Developer ==================================
            System.out.println("=".repeat(20) + " Developer " + "=".repeat(20));
            // 1. Developer logs in
            System.out.println("▶️Developer logs in");

            dev1 = developerService.login(dev1.getEmail(), "password2");
            System.out.println(dev1);


            // 2. Developer gets assigned bugs
            System.out.println("▶️Getting assigned bugs");
            List<Bug> bugs = developerService.getAssignedBugs();

            System.out.println("Bugs assigned to " + developerService.getCurrentDeveloper().getName());
            bugs.forEach(bug -> System.out.println(bug.getId() + ": " + bug.getTitle() + " - " + bug.getSeverity() + " - " + bug.getStatus()));

            // 3. Developer closes bugs
            System.out.println("▶️Closing bugs");
            for (Bug bug : bugs) {
                developerService.closeBug(bug.getId());
                System.out.println("Bug closed: " + bug.getTitle());
            }
            //====================================== Project Manager ==================================
            System.out.println("=".repeat(20) + " Project Manager " + "=".repeat(20));
            // 1. Manager sees status of bugs
            System.out.println("▶️Getting status of the project");

            Project p = projectManagerService.getProject(project1.getProjectId());
            System.out.println(p);
            System.out.println("Bugs Status: ");
            p.getBugs().forEach(bug -> System.out.println(bug.getTitle() + " - " + bug.getSeverity() + " - " + bug.getStatus()));


            System.out.println("*".repeat(20) + " END " + "*".repeat(20));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }


    }
}
