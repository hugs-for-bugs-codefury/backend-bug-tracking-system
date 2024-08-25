package org.example.services;

import org.example.dao.impl.DeveloperDAOImpl;
import org.example.models.Bug;
import org.example.models.Developer;
import org.example.models.Project;
import org.example.models.User;
import org.example.services.impl.DeveloperServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class DeveloperServiceTest {

    private DeveloperServiceImpl developerService;
    private TestDeveloperDAO testDeveloperDAO;

    @Before
    public void setUp() throws Exception {
        User user = new Developer("John Doe", "john@example.com", "password123", "developer");
        developerService = new DeveloperServiceImpl(user);
        testDeveloperDAO = new TestDeveloperDAO();

        // Use reflection to replace the DAO
        Field daoField = DeveloperServiceImpl.class.getDeclaredField("developerDAO");
        daoField.setAccessible(true);
        daoField.set(developerService, testDeveloperDAO);
    }

    @Test
    public void testRegisterUser() {
        Developer registeredDeveloper = developerService.registerUser("Jane Doe", "jane@example.com", "password456");

        assertNotNull(registeredDeveloper);
        assertEquals("Jane Doe", registeredDeveloper.getName());
        assertEquals("jane@example.com", registeredDeveloper.getEmail());
        assertNotEquals("password456", registeredDeveloper.getPasswordHash()); // Password should be hashed
    }

    @Test
    public void testGetCurrentDeveloper() {
        Developer currentDeveloper = developerService.getCurrentDeveloper();

        assertNotNull(currentDeveloper);
        assertEquals("John Doe", currentDeveloper.getName());
        assertEquals("john@example.com", currentDeveloper.getEmail());
    }

    @Test
    public void testGetDeveloper() {
        Developer developer = developerService.getDeveloper(1);

        assertNotNull(developer);
        assertEquals(1, developer.getDeveloperId());
        assertEquals("Test Developer", developer.getName());
    }

    @Test
    public void testCloseBug() {
        developerService.closeBug(1);
        // This test is limited without mocking. In a real scenario, you'd verify that the bug was closed.
    }

    @Test
    public void testGetAssignedProject() {
       List<Project> project = developerService.getAssignedProjects();

        assertNotNull(project);
        assertEquals(1, project.size());
        assertEquals("Test Project", project.get(0).getProjectName());
    }

    @Test
    public void testGetAssignedProjectById() {
        List <Project> project = developerService.getAssignedProjects(1);

        assertNotNull(project);
        assertEquals(1, project.size());
    }

    @Test
    public void testGetAssignedBugs() {
        List<Bug> bugs = developerService.getAssignedBugs();

        assertNotNull(bugs);
        assertEquals(2, bugs.size());
        assertEquals("Bug 1", bugs.get(0).getTitle());
        assertEquals("Bug 2", bugs.get(1).getTitle());
    }

    @Test
    public void testLogin() {
        Developer loggedInDeveloper = developerService.login("john@example.com", "password123");

        assertNotNull(loggedInDeveloper);
        assertEquals("John Doe", loggedInDeveloper.getName());
        assertEquals("john@example.com", loggedInDeveloper.getEmail());
        assertNotNull(loggedInDeveloper.getLastLogin());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLoginWithInvalidCredentials() {
        developerService.login("john@example.com", "wrongpassword");
    }

    // Test implementation of DeveloperDAOImpl for testing purposes
    private class TestDeveloperDAO extends DeveloperDAOImpl {
        @Override
        public Developer saveUser(String name, String email, String password) {
            return new Developer(name, email, password, "developer");
        }

        @Override
        public Developer findByID(int developerId) {
            Developer developer = new Developer("Test Developer", "test@example.com", "hashedpassword", "developer");
            developer.setDeveloperId(developerId);
            return developer;
        }

        @Override
        public Developer findByEmail(String email) {
            return new Developer("John Doe", email, "hashedpassword", "developer");
        }

        @Override
        public List<Project> getAssignedProjects(int developerId) {
            return Arrays.asList(new Project("Test Project", LocalDateTime.now().plusDays(2)));
        }

        @Override
        public List<Bug> getAssignedBugs(int developerId) {
            return Arrays.asList(
                    new Bug("Bug 1", "Description 1", "High", "open", LocalDateTime.now(), 1, 1),
                    new Bug("Bug 2", "Description 2", "Medium", "open", LocalDateTime.now(), 1, 1)
            );
        }
    }
}