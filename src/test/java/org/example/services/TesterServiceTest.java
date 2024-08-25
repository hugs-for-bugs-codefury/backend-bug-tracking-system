package org.example.services;

import org.example.dao.impl.TesterDAOImpl;
import org.example.models.Bug;
import org.example.models.Project;
import org.example.models.Tester;
import org.example.models.User;
import org.example.services.impl.TesterServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TesterServiceTest {

    private TesterServiceImpl testerService;
    private TesterDAOImpl testerDAO;

    @BeforeEach
    void setUp() {
        testerDAO = new TesterDAOImpl();
        testerService = new TesterServiceImpl();
    }

    @Test
    void testRegisterUser() {
        String name = "Alice Tester";
        String email = "alice.tester@example.com";
        String password = "securePassword";

        Tester tester = testerService.registerUser(name, email, password);
        assertNotNull(tester);
        assertEquals(name, tester.getName());
        assertEquals(email, tester.getEmail());
    }

    @Test
    void testGetCurrentTester() {
        User user = new Tester();
        user.setRole("tester");
        testerService = new TesterServiceImpl(user);

        Tester currentTester = testerService.getCurrentTester();
        assertNotNull(currentTester);
        assertEquals(user.getRole(), currentTester.getRole());
    }

    @Test
    void testGetTester() {
        int testerId = 1; // Assuming a tester with this ID exists

        Tester tester = testerService.getTester(testerId);
        assertNotNull(tester);
        assertEquals(testerId, tester.getTesterId());
    }

    @Test
    void testReportBug() {
        Project project = new Project();
        project.setProjectId(1); // Assuming a project with this ID exists
        String title = "Bug Title";
        String description = "Bug Description";
        String severity = "High";

        Bug reportedBug = testerService.reportBug(project, title, description, severity);
        assertNotNull(reportedBug);
        assertEquals(title, reportedBug.getTitle());
        assertEquals(description, reportedBug.getDescription());
        assertEquals(severity, reportedBug.getSeverity());
    }

    @Test
    void testLogin() {
        String email = "alice.tester@example.com";
        String password = "securePassword";

        Tester loggedInTester = testerService.login(email, password);
        assertNotNull(loggedInTester);
        assertEquals(email, loggedInTester.getEmail());
    }

    @Test
    void testLoginInvalidCredentials() {
        String email = "invalid.email@example.com";
        String password = "wrongPassword";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            testerService.login(email, password);
        });

        assertEquals("Invalid email or password", exception.getMessage());
    }
}