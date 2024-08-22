package org.example;

import org.example.factory.impl.ServiceFactory;

import org.example.models.*;

import org.example.services.IDeveloperService;
import org.example.services.IProjectManagerService;
import org.example.services.ITesterService;
import org.example.util.DummyData;


import java.time.LocalDateTime;
import java.util.List;


public class App 
{
    public static void main( String[] args )
    {
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
        System.out.println("Project manager logs in");
        try {
            projectManagerService.login(pm.getEmail(), "password1");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }



        // 2. Project manager creates a project
        System.out.println("Creating a project");
        Project project1 = projectManagerService.createProject("Project 1", LocalDateTime.now());
        Project project2 = projectManagerService.createProject("Project 2", LocalDateTime.now());
        System.out.println("Project created: " + project1);
        System.out.println("Project created: " + project2);



//      // 3. Project manager assigns testers to the project
        System.out.println("Assigning testers to the project");
        projectManagerService.assignTester(project1, testerService.getTester(tester1.getTesterId()));
        projectManagerService.assignTester(project1, testerService.getTester(tester2.getTesterId()));




        // =================================== Tester ==================================
        System.out.println("=".repeat(20) + " Tester " + "=".repeat(20));


        // 1. Tester logs in
        System.out.println("Tester1 logs in");
        try {
            testerService.login(tester1.getEmail(), "password4");
        } catch (Exception e) {
            e.printStackTrace();
        }


        // 2. Tester reports bugs
        System.out.println("Reporting bugs");
        Bug bug1 = testerService.reportBug(project1, "Bug 1", "Bug 1 Description", "high");
        Bug bug2 = testerService.reportBug(project1, "Bug 2", "Bug 2 Description", "medium");
        Bug bug3 = testerService.reportBug(project1, "Bug 3", "Bug 3 Description", "low");

        System.out.println("Bugs reported: ");
        System.out.println(bug1);
        System.out.println(bug2);
        System.out.println(bug3);



        // =================================== Project Manager ==================================
        System.out.println("=".repeat(20) + " Project Manager " + "=".repeat(20));

        // 1. Manager assigns a bug to a developer
        System.out.println("Assigning a bug to a developer");
        projectManagerService.assignDeveloper(bug1, developerService.getDeveloper(dev1.getDeveloperId()));


        // =================================== Developer ==================================
        System.out.println("=".repeat(20) + " Developer " + "=".repeat(20));
        // 1. Developer logs in
        System.out.println("Developer logs in");
        try {
            developerService.login(dev1.getEmail(), "password2");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        // 2. Developer gets assigned bugs
        System.out.println("Getting assigned bugs");
        List<Bug> bugs = developerService.getAssignedBugs();

        System.out.println("Bugs assigned to " + developerService.getCurrentDeveloper().getName());
        bugs.forEach(bug -> System.out.println(bug.getTitle()));

        // 3. Developer closes bugs
        System.out.println("Closing bugs");
        for (Bug bug : bugs) {
            developerService.closeBug(bug.getId());
            System.out.println("Bug closed: " + bug.getTitle());
        }
        //====================================== Project Manager ==================================
        System.out.println("=".repeat(20) + " Project Manager " + "=".repeat(20));
        // 1. Manager sees status of bugs
        System.out.println("Getting status of the project");
        try {
            Project p = projectManagerService.getProject(project1.getProjectId());
            System.out.println("Project: " + p.getProjectName());
            System.out.println("Bugs: ");
            p.getBugs().forEach(bug -> System.out.println(bug.getTitle() + " - " + bug.getSeverity() + " - " + bug.getStatus()));


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        System.out.println("*".repeat(20) + " END " + "*".repeat(20));









    }
}
