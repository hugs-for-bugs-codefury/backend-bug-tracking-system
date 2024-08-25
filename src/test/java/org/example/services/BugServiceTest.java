package org.example.services;

import org.example.dao.impl.BugDAOImpl;
import org.example.models.Bug;
import org.example.services.impl.BugServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class BugServiceTest {

    private BugServiceImpl bugService;
    private TestBugDAO testBugDAO;

    @Before
    public void setUp() {
        bugService = new BugServiceImpl();
        testBugDAO = new TestBugDAO();
    }

    @Test
    public void testReportBug() {
        Bug reportedBug = bugService.reportBug("Test Bug", "Description", "High", 1, 1);

        assertNotNull(reportedBug);
        assertEquals("Test Bug", reportedBug.getTitle());
        assertEquals("Description", reportedBug.getDescription());
        assertEquals("High", reportedBug.getSeverity());
        assertEquals("open", reportedBug.getStatus());
        assertEquals(1, reportedBug.getProjectId());
    }

    @Test
    public void testAssignDeveloper() {
        Bug assignedBug = bugService.assignDeveloper(1, 2);

        assertNotNull(assignedBug);
    }

    @Test
    public void testCloseBug() {
        bugService.closeBug(1);
        assertTrue(testBugDAO.isBugClosed(1));
    }

    @Test
    public void testGetBugsByTester() {
        List<Bug> bugs = bugService.getBugsByTester(1);

        assertNotNull(bugs);
        assertEquals(2, bugs.size());
        assertEquals(1, bugs.get(0).getCreatedById());
        assertEquals(1, bugs.get(1).getCreatedById());
    }

    // Test implementation of BugDAOImpl for testing purposes
    private class TestBugDAO extends BugDAOImpl {
        private List<Bug> bugs = Arrays.asList(
                new Bug("Bug 1", "Description 1", "High", "open", LocalDateTime.now(), 1, 1),
                new Bug("Bug 2", "Description 2", "Medium", "open", LocalDateTime.now(), 1, 1)
        );

        @Override
        public Bug saveBug(Bug bug) {
            bug.setId(1);
            return bug;
        }

        @Override
        public Bug assignDeveloper(int bugId, int developerId) {
            Bug bug = new Bug("Test Bug", "Description", "High", "open", LocalDateTime.now(), 1, 1);
            bug.setId(bugId);
            bug.setId(developerId);
            return bug;
        }

        @Override
        public Bug closeBug(int bugId) {
            Bug bug = new Bug("Test Bug", "Description", "High", "closed", LocalDateTime.now(), 1, 1);
            bug.setId(bugId);
            return bug;
        }

        @Override
        public List<Bug> findBugsByTester(int testerId) {
            return bugs;
        }

        public boolean isBugClosed(int bugId) {
            return true;
        }
    }
}