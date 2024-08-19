package org.example.models;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The Tester class extends the User class and represents a tester in the system.
 * A Tester is responsible for testing the software and reporting bugs.
 *
 * Fields:
 * - List<Project> projects: The list of projects the tester is working on.
 * - List<Bug> reportedBugs: The list of bugs reported by the tester.
 */
public class Tester extends User {
    private String testerId;
    private List<Project> projects;
    private List<Bug> reportedBugs;

    /**
     * Constructs a new Tester with the specified tester ID.
     *
     * @param testerId     The unique identifier for the tester.
     * @param id            The user ID.
     * @param name          The user's name.
     * @param email         The user's email address.
     * @param passwordHash  The hashed password for authentication.
     * @param role          The user's role (e.g., "tester").
     * @param lastLogin     The timestamp of the user's last login.
     */
    public Tester(String testerId, int id, String name, String email,
                  String passwordHash, String role, LocalDateTime lastLogin) {
        super(id, name, email, passwordHash, role, lastLogin); // Call the User class constructor
        this.testerId = testerId;
    }

    /**
     * Constructs a new Tester with the specified tester ID, project list, and reported bugs list.
     *
     * @param testerId     The unique identifier for the tester.
     * @param projects     The list of projects the tester is working on.
     * @param reportedBugs The list of bugs reported by the tester.
     * @param id            The user ID.
     * @param name          The user's name.
     * @param email         The user's email address.
     * @param passwordHash  The hashed password for authentication.
     * @param role          The user's role (e.g., "tester").
     * @param lastLogin     The timestamp of the user's last login.
     */
    public Tester(String testerId, List<Project> projects, List<Bug> reportedBugs,
                  int id, String name, String email, String passwordHash,
                  String role, LocalDateTime lastLogin) {
        super(id, name, email, passwordHash, role, lastLogin); // Call the User class constructor
        this.testerId = testerId;
        this.projects = projects;
        this.reportedBugs = reportedBugs;
    }


    /**
     * Gets the list of projects the tester is working on.
     *
     * @return The list of projects.
     */
    public List<Project> getProjects() {
        return projects;
    }

    /**
     * Sets the list of projects the tester is working on.
     *
     * @param projects The new list of projects.
     */
    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    /**
     * Gets the list of bugs reported by the tester.
     *
     * @return The list of reported bugs.
     */
    public List<Bug> getReportedBugs() {
        return reportedBugs;
    }

    /**
     * Sets the list of bugs reported by the tester.
     *
     * @param reportedBugs The new list of reported bugs.
     */
    public void setReportedBugs(List<Bug> reportedBugs) {
        this.reportedBugs = reportedBugs;
    }

    /**
     * Adds a bug to the list of bugs reported by the tester.
     *
     * @param bug The bug to add to the reported bugs list.
     */
    public void reportBug(Bug bug) {
        this.reportedBugs.add(bug);
    }
}