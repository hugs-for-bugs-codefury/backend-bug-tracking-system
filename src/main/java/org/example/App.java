package org.example;

import org.example.factory.impl.ServiceFactory;

import org.example.models.*;

import org.example.services.IDeveloperService;
import org.example.services.IProjectManagerService;
import org.example.services.ITesterService;


import java.time.LocalDateTime;
import java.util.List;


public class App 
{
    public static void main( String[] args )
    {
        IProjectManagerService projectManagerService = (IProjectManagerService) ServiceFactory.getService("ProjectManagerService");
        IDeveloperService developerService = (IDeveloperService) ServiceFactory.getService("DeveloperService");
        ITesterService testerService = (ITesterService) ServiceFactory.getService("TesterService");


        System.out.println("Starting the application");
//        Register different types of users
        ProjectManager pm = projectManagerService.registerUser("Project Manager", "pm@mail.com", "password1");
        Developer dev1 = developerService.registerUser("Developer 1", "dev1@mail.com", "password2");
        Developer dev2 = developerService.registerUser("Developer 2", "dev2@mail.com", "password3");
        Tester tester1 = testerService.registerUser("Tester 1", "tester1@mail.com", "password4");
        Tester tester2 = testerService.registerUser("Tester 2", "tester2@mail.com", "password5");



        // ================================== Service Factory ==================================





        // =====================================================================================

        // ================================== Project Manager ==================================

        // 1. Project manager logs in
        try {
            projectManagerService.login(pm.getEmail(), "password1");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }



        // 2. Project manager creates a project

        Project project1 = projectManagerService.createProject("Project 1", LocalDateTime.now());
        Project project2 = projectManagerService.createProject("Project 2", LocalDateTime.now());


//      // 3. Project manager assigns developers to the project

        projectManagerService.assignTester(project1, testerService.getTester(tester1.getId()));



        // =================================== Tester ==================================

        // 1. Tester logs in
        try {
            testerService.login(tester1.getEmail(), "password4");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        // 2. Tester reports bugs
        Bug bug1 = testerService.reportBug(project1, "Bug 1", "Bug 1 Description", "High");
        Bug bug2 = testerService.reportBug(project1, "Bug 2", "Bug 2 Description", "Medium");
        Bug bug3 = testerService.reportBug(project1, "Bug 3", "Bug 3 Description", "Low");


        // =================================== Project Manager ==================================
        // 1. Manager assigns a bug to a developer
        projectManagerService.assignDeveloper(bug1, developerService.getDeveloper(dev1.getId()));


        // =================================== Developer ==================================
        // 1. Developer logs in
        try {
            developerService.login(dev1.getEmail(), "password2");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        // 2. Developer gets assigned bugs
        List<Bug> bugs = developerService.getAssignedBugs();
        System.out.println("Bugs assigned to " + developerService.getCurrentDeveloper().getName());
        bugs.forEach(bug -> System.out.println(bug.getTitle()));

        // 3. Developer closes bugs
        for (Bug bug : bugs) {
            developerService.closeBug(bug.getId());
        }
        //====================================== Project Manager ==================================
        // 1. Manager sees status of bugs
        try {
            Project p = projectManagerService.getProject(project1.getProjectId());
            System.out.println("Bugs in project " + p.getProjectName());
            p.getBugs().forEach(bug -> System.out.println(bug.getTitle() + " - " + bug.getStatus()));


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }









    }
}
