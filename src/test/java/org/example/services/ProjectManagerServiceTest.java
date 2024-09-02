package org.example.services;

import org.example.factory.impl.ServiceFactory;
import org.example.models.*;
import org.example.util.Config;
import org.example.util.MySQLConnection;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Paths;
import java.time.LocalDateTime;

public class ProjectManagerServiceTest {


    String pmName = "project manager";
    String pmEmail = "developer@mail.com";
    String pmPassword = "password";
    String pmRole = "project_manager";



    @BeforeEach
    public  void setUp() {
        Config.setProperty("db.url", "jdbc:mysql://localhost:3306/test_db");
        MySQLConnection.setup();
        MySQLConnection.seed();


    }




    @DisplayName("Test register project manager")
    @Test
    void testRegisterDeveloper() {

        IProjectManagerService projectManagerService = (IProjectManagerService)ServiceFactory.getService("ProjectManagerService");

        assertDoesNotThrow(() -> {
            User u = projectManagerService.registerUser(pmName, pmEmail, pmPassword);
            assertNotNull(u);
            assertEquals(pmName, u.getName());
            assertEquals(pmEmail, u.getEmail());
            assertEquals(pmRole, u.getRole());


        });

    }

    @DisplayName("Test login project manager")
    @Test
    void testLoginProjectManager() {
        IProjectManagerService projectManagerService = (IProjectManagerService)ServiceFactory.getService("ProjectManagerService");

        User registeredUser = projectManagerService.registerUser(pmName, pmEmail, pmPassword);
        User loggedInUser = projectManagerService.login(pmEmail, pmPassword);

        assertEquals(registeredUser, loggedInUser);
    }

    @DisplayName("Test get logged in project manager")
    @Test
    void testGetLoggedInProjectManager() {
        IProjectManagerService projectManagerService = (IProjectManagerService)ServiceFactory.getService("ProjectManagerService");

        User registeredUser = projectManagerService.registerUser(pmName, pmEmail, pmPassword);
        User loggedInUser = projectManagerService.login(pmEmail, pmPassword);

        assertEquals(registeredUser, loggedInUser);
    }

    @DisplayName("Test get project manager")
    @Test
    void testGetProjectManager() {
        IProjectManagerService projectManagerService = (IProjectManagerService) ServiceFactory.getService("ProjectManagerService");

        User registeredUser = projectManagerService.registerUser(pmName, pmEmail, pmPassword);

        assertDoesNotThrow(() -> {
            User u = projectManagerService.getUser(registeredUser.getId());
            assertNotNull(u);
            assertEquals(pmName, u.getName());
            assertEquals(pmEmail, u.getEmail());
            assertEquals(pmRole, u.getRole());
        });
    }

    @DisplayName("Test manager creates project")
    @Test
    void testCreateProject() {
        IProjectManagerService projectManagerService = (IProjectManagerService) ServiceFactory.getService("ProjectManagerService");

        ProjectManager projectManager = projectManagerService.registerUser(pmName, pmEmail, pmPassword);

        // Login the project manager
        projectManagerService.login(pmEmail, pmPassword);

        assertDoesNotThrow(() -> {

            Project project = projectManagerService.createProject("project", LocalDateTime.now());
            assertNotNull(project);
            assertEquals("project", project.getProjectName());
            assertEquals(projectManager.getManagerId(), project.getProjectManagerId());
            assertEquals(1, project.getProjectId());
        });
    }



}
