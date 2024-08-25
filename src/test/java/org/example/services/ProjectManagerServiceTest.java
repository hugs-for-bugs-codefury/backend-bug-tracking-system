package org.example.services;

import org.example.models.Bug;
import org.example.models.Developer;
import org.example.models.Project;
import org.example.models.ProjectManager;
import org.example.models.Tester;
import org.example.services.impl.ProjectManagerServiceImpl;
import org.example.services.impl.ProjectServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class IProjectManagerServiceTest {

    private IProjectManagerService projectManagerService;

    @BeforeEach
    void setUp() {
        projectManagerService = new ProjectManagerServiceImpl();
    }

    @Test
    void testRegisterUser() {
        ProjectManager projectManager = projectManagerService.registerUser("John Doe", "john@example.com", "password");
        Assertions.assertNotNull(projectManager);
        Assertions.assertEquals("John Doe", projectManager.getName());
        Assertions.assertEquals("john@example.com", projectManager.getEmail());
    }

    @Test
    void testLogin() {
        ProjectManager projectManager = projectManagerService.registerUser("John Doe", "john@example.com", "password");
        ProjectManager loggedInProjectManager = projectManagerService.login("john@example.com", "password");
        Assertions.assertNotNull(loggedInProjectManager);
        Assertions.assertEquals(projectManager, loggedInProjectManager);
    }

    @Test
    void testGetCurrentProjectManager() {
        ProjectManager projectManager = projectManagerService.registerUser("John Doe", "john@example.com", "password");
        projectManagerService.login("john@example.com", "password");
        ProjectManager currentProjectManager = projectManagerService.getCurrentProjectManager();
        Assertions.assertNotNull(currentProjectManager);
        Assertions.assertEquals(projectManager, currentProjectManager);
    }

    @Test
    void testGetProjectManager() {
        ProjectManager projectManager = projectManagerService.registerUser("John Doe", "john@example.com", "password");
        ProjectManager retrievedProjectManager = projectManagerService.getProjectManager(projectManager.getId());
        Assertions.assertNotNull(retrievedProjectManager);
        Assertions.assertEquals(projectManager, retrievedProjectManager);
    }

    @Test
    void testCreateProject() {
        ProjectManager projectManager = projectManagerService.registerUser("John Doe", "john@example.com", "password");
        projectManagerService.login("john@example.com", "password");

        LocalDateTime startDate = LocalDateTime.now().plusDays(2);
        Project project = ((ProjectManagerServiceImpl)projectManagerService).createProject("Test Project", startDate);

        Assertions.assertNotNull(project);
        Assertions.assertEquals("Test Project", project.getProjectName());
        Assertions.assertEquals(startDate, project.getStartDate());
        Assertions.assertEquals("In Progress", project.getStatus());
        Assertions.assertEquals(projectManager.getManagerId(), project.getProjectManagerId());
    }

    @Test
    void testAssignTester() {
        Project project = new Project("Test Project", LocalDateTime.now().plusDays(2));
        Tester tester = new Tester("Jane Smith", "jane@example.com");

        projectManagerService.assignTester(project, tester);

        List<Integer> assignedTesters = project.getTesters();
        Assertions.assertNotNull(assignedTesters);
        Assertions.assertTrue(assignedTesters.contains(tester));
    }

    @Test
    void testAssignDeveloper() {
        Bug bug = new Bug("Test Bug", "Description");
        Developer developer = new Developer("John Doe", "john@example.com");

        projectManagerService.assignDeveloper(bug, developer);

        Assertions.assertEquals(developer, bug.getCreatedById());
    }
}