package org.example.services;

import org.example.dao.impl.UserDAOImpl;
import org.example.models.Project;
import org.example.models.User;
import org.example.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private UserServiceImpl userService;
    private UserDAOImpl userDAO;

    @BeforeEach
    void setUp() {
        userDAO = new UserDAOImpl();
        userService = new UserServiceImpl();
    }

    @Test
    void testRegisterUser() throws Exception {
        String name = "John Doe";
        String email = "john.doe@example.com";
        String password = "password123";
        String role = "developer";

        User user = userService.registerUser(name, email, password, role);
        assertNotNull(user);
        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());
        assertEquals(role, user.getRole());
    }

    @Test
    void testGetUser() throws Exception {
        int userId = 1;

        User user = userService.getUser(userId);
        assertNotNull(user);
        assertEquals(userId, user.getId());
    }

    @Test
    void testGetProject() throws Exception {
        int projectId = 1;

        Project project = userService.getProject(projectId);
        assertNotNull(project);
        assertEquals(projectId, project.getProjectId());
    }

    @Test
    void testFindByEmail() throws Exception {
        String email = "john.doe@example.com";

        User user = userDAO.findByEmail(email);
        assertNotNull(user);
        assertEquals(email, user.getEmail());
    }

    @Test
    void testFindByID() throws Exception {
        int userId = 1;

        User user = userDAO.findByID(userId);
        assertNotNull(user);
        assertEquals(userId, user.getId());
    }

    @Test
    void testSaveUser() throws Exception {
        String name = "Jane Smith";
        String email = "jane.smith@example.com";
        String password = "password456";
        String role = "tester";

        User user = userDAO.saveUser(name, email, password, role);
        assertNotNull(user);
        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());
        assertEquals(role, user.getRole());
    }

    @Test
    void testComparePassword() throws Exception {
        String email = "john.doe@example.com";
        String password = "password123";

        boolean isPasswordMatch = userDAO.comparePassword(email, password);
        assertTrue(isPasswordMatch);
    }

    @Test
    void testUpdateLastLogin() throws Exception {
        int userId = 1;

        LocalDateTime lastLogin = userDAO.updateLastLogin(userId);
        assertNotNull(lastLogin);
    }
}