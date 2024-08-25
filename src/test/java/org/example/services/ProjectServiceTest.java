package org.example.services;

import org.example.models.Bug;
import org.example.models.Developer;
import org.example.models.Project;
import org.example.models.Tester;
import org.example.services.impl.ProjectServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProjectServiceTest {

    private ProjectServiceImpl projectService;

    @BeforeEach
    void setUp() {
        projectService = new ProjectServiceImpl();
    }

    @Test
    void testCreateProject() {
        String projectName = "Test Project";
        LocalDateTime startDate = LocalDateTime.now();
        int managerId = 1;

        Project project = projectService.createProject(projectName, startDate, managerId);
        assertNotNull(project);
        assertEquals(projectName, project.getProjectName());
        assertEquals("in_progress", project.getStatus());
        assertEquals(managerId, project.getProjectManagerId());
    }

    @Test
    void testAssignDeveloper() {
        int projectId = 1;
        int developerId = 2;

        projectService.assignDeveloper(projectId, developerId);
        // Assuming there is a way to verify the assignment
        Project project = projectService.getProject(projectId);
        List<Developer> developers = projectService.getAssignedDevelopers(projectId);
        assertTrue(developers.stream().anyMatch(d -> d.getId() == developerId));
    }

    @Test
    void testAssignTester() {
        int projectId = 1;
        int testerId = 3;

        projectService.assignTester(projectId, testerId);
        // Assuming there is a way to verify the assignment
        Project project = projectService.getProject(projectId);
        List<Tester> testers = projectService.getAssignedTesters(projectId);
        assertTrue(testers.stream().anyMatch(t -> t.getId() == testerId));
    }

    @Test
    void testGetProject() {
        int projectId = 1;

        Project project = projectService.getProject(projectId);
        assertNotNull(project);
        assertEquals(projectId, project.getProjectId());
    }

    @Test
    void testGetProjectsByTester() {
        int testerId = 3;

        List<Project> projects = projectService.getProjectsByTester(testerId);
        assertNotNull(projects);
        assertFalse(projects.isEmpty());
    }

    @Test
    void testGetProjectsByManager() {
        int managerId = 1;

        List<Project> projects = projectService.getProjectsByManager(managerId);
        assertNotNull(projects);
        assertFalse(projects.isEmpty());
    }

    @Test
    void testGetProjectByDeveloper() {
        int developerId = 2;

        List<Project> project = projectService.getProjectByDeveloper(developerId);
        assertTrue(project.isEmpty());
    }

    @Test
    void testGetAssignedDevelopers() {
        int projectId = 1;

        List<Developer> developers = projectService.getAssignedDevelopers(projectId);
        assertNotNull(developers);
    }

    @Test
    void testGetAssignedTesters() {
        int projectId = 1;

        List<Tester> testers = projectService.getAssignedTesters(projectId);
        assertNotNull(testers);
    }

    @Test
    void testGetProjectBugs() {
        int projectId = 1;

        List<Bug> bugs = projectService.getProjectBugs(projectId);
        assertNotNull(bugs);
    }
}