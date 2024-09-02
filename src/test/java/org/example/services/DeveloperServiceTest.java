package org.example.services;

import org.example.factory.impl.ServiceFactory;
import org.example.models.*;
import org.example.util.Config;
import org.example.util.MySQLConnection;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

public class DeveloperServiceTest {


    String developerName = "developer";
    String developerEmail = "developer@mail.com";
    String developerPassword = "password";
    String developerRole = "developer";



    @BeforeEach
    public  void setUp() {
        Config.setProperty("db.url", "jdbc:mysql://localhost:3306/test_db");
        MySQLConnection.setup();
        MySQLConnection.seed();


    }




    @DisplayName("Test register developer")
    @Test
    void testRegisterDeveloper() {

        IDeveloperService developerService = (IDeveloperService)ServiceFactory.getService("DeveloperService");

        assertDoesNotThrow(() -> {
            User u = developerService.registerUser(developerName, developerEmail, developerPassword);
            assertNotNull(u);
            assertEquals(developerName, u.getName());
            assertEquals(developerEmail, u.getEmail());
            assertEquals(developerRole, u.getRole());
            assertEquals(1, u.getId());

        });

    }

    @DisplayName("Test get logged in developer")
    @Test
    void testGetLoggedInDeveloper() {
        IDeveloperService developerService = (IDeveloperService)ServiceFactory.getService("DeveloperService");

        User registeredUser = developerService.registerUser(developerName, developerEmail, developerPassword);
        User loggedInUser = developerService.login(developerEmail, developerPassword);

        assertEquals(registeredUser, loggedInUser);
    }

    @DisplayName("Test get developer")
    @Test
    void testGetDeveloper() {
        IDeveloperService developerService = (IDeveloperService) ServiceFactory.getService("DeveloperService");

        User registeredUser = developerService.registerUser(developerName, developerEmail, developerPassword);

        assertDoesNotThrow(() -> {
            User u = developerService.getUser(registeredUser.getId());
            assertNotNull(u);
            assertEquals(developerName, u.getName());
            assertEquals(developerEmail, u.getEmail());
            assertEquals(developerRole, u.getRole());
        });

    }


    @DisplayName("Get Assigned Bugs")
    @Test
    void testGetAssignedBugs() {
        IProjectManagerService projectManagerService = (IProjectManagerService) ServiceFactory.getService("ProjectManagerService");
        IDeveloperService developerService = (IDeveloperService) ServiceFactory.getService("DeveloperService");
        ITesterService testerService = (ITesterService) ServiceFactory.getService("TesterService");

        // Register a project manager
        ProjectManager projectManager = projectManagerService.registerUser("project manager", "pm@mail.com", "password");
        Tester tester = testerService.registerUser("tester", "tester@mail.com", "password");

        Developer developer = developerService.registerUser(developerName, developerEmail, developerPassword);

        // Login the project manager
        projectManagerService.login("pm@mail.com", "password");

        // Create a project
        Project p = projectManagerService.createProject("project", LocalDateTime.now());

        // Add the developer to the project
        projectManagerService.assignDeveloperToProject(p.getProjectId(), developer.getDeveloperId());

        // Add the tester to the project
        projectManagerService.assignTesterToProject(p.getProjectId(), tester.getTesterId());

        // Login tester and report a bug
        testerService.login("tester@mail.com", "password");
        Bug bug1 = testerService.reportBug(p, "bug1", "description", "high");
        Bug bug2 = testerService.reportBug(p, "bu2", "description", "low");



        // Assign the bug to the developer
        projectManagerService.assignDeveloperToBug(bug1.getId(), developer.getDeveloperId());
        projectManagerService.assignDeveloperToBug(bug2.getId(), developer.getDeveloperId());

        // Login developer and close the bug
        developerService.login(developerEmail, developerPassword);
        assertDoesNotThrow(() -> {


            List<Bug> bugs = developerService.getAssignedBugs();

            assertEquals(2, bugs.size());
            assertTrue(bugs.stream().anyMatch(bug -> bug.getId() == bug1.getId()));
            assertTrue(bugs.stream().anyMatch(bug -> bug.getId() == bug2.getId()));

        });

    }


    @DisplayName("Test close bug")
    @Test
    void testCloseBug() {
        IProjectManagerService projectManagerService = (IProjectManagerService) ServiceFactory.getService("ProjectManagerService");
        IDeveloperService developerService = (IDeveloperService) ServiceFactory.getService("DeveloperService");
        ITesterService testerService = (ITesterService) ServiceFactory.getService("TesterService");

        // Register a project manager
        ProjectManager projectManager = projectManagerService.registerUser("project manager", "pm@mail.com", "password");
        Tester tester = testerService.registerUser("tester", "tester@mail.com", "password");

        Developer developer = developerService.registerUser(developerName, developerEmail, developerPassword);

        // Login the project manager
        projectManagerService.login("pm@mail.com", "password");

        // Create a project
        Project p = projectManagerService.createProject("project", LocalDateTime.now());

        // Add the developer to the project
        projectManagerService.assignDeveloperToProject(p.getProjectId(), developer.getDeveloperId());

        // Add the tester to the project
        projectManagerService.assignTesterToProject(p.getProjectId(), tester.getTesterId());

        // Login tester and report a bug
        testerService.login("tester@mail.com", "password");
        Bug bug = testerService.reportBug(p, "bug", "description", "high");

        assertEquals("open", bug.getStatus());

        // Assign the bug to the developer
        projectManagerService.assignDeveloperToBug(bug.getId(), developer.getDeveloperId());

        // Login developer and close the bug
        developerService.login(developerEmail, developerPassword);
        assertDoesNotThrow(() -> {
            developerService.closeBug(bug.getId());

            List<Bug> bugs = developerService.getAssignedBugs();

            assertEquals(1, bugs.size());
            assertEquals("closed", bugs.get(0).getStatus());


        });
    }

    @DisplayName("Get assigned projects")
    @Test
    void testGetAssignedProjects() {
        IProjectManagerService projectManagerService = (IProjectManagerService) ServiceFactory.getService("ProjectManagerService");
        IDeveloperService developerService = (IDeveloperService) ServiceFactory.getService("DeveloperService");

        // Register a project manager
        ProjectManager projectManager = projectManagerService.registerUser("project manager", "pm@mail.com", "password");

        // Register a developer
        Developer developer = developerService.registerUser(developerName, developerEmail, developerPassword);

        // Login the project manager
        projectManagerService.login(projectManager.getEmail(), "password");

        // Create a project
        Project p1 = projectManagerService.createProject("project1", LocalDateTime.now());
        Project p2 = projectManagerService.createProject("project2", LocalDateTime.now());

        // Add the developer to the project
        projectManagerService.assignDeveloperToProject(p1.getProjectId(), developer.getDeveloperId());
        projectManagerService.assignDeveloperToProject(p2.getProjectId(), developer.getDeveloperId());


        // Login the developer
        developerService.login(developer.getEmail(), developerPassword);

        assertDoesNotThrow(() -> {
            List<Project> projects = developerService.getAssignedProjects();
            assertEquals(2, projects.size());
            assertEquals(p1.getProjectId(), projects.get(0).getProjectId());
            assertEquals(p2.getProjectId(), projects.get(1).getProjectId());
        });

    }





}
