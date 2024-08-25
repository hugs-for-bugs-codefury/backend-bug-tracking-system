package org.example.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private final int testerId;
    private List<Project> projects;
    private List<Bug> reportedBugs;


    /**
     * Constructs a new Tester with the specified tester ID, name, email, and password hash.
     * @param id
     * @param testerId
     * @param name
     * @param email
     * @param passwordHash
     */
    public Tester(int id, int testerId, String name, String email, String passwordHash){
        super(id, name, email, passwordHash, "tester");
        this.testerId = testerId;
        this.projects = new ArrayList<>();
        this.reportedBugs = new ArrayList<>();


    }

    /**
      * Constructs a new Tester with the specified tester ID, name, email, password hash, projects, and reported bugs.     * @param testerId
     * @param name
     * @param email
     * @param passwordHash
     * @param projects
     * @param reportedBugs
     */
    public Tester(int testerId, String name, String email, String passwordHash, List<Project> projects, List<Bug> reportedBugs) {
        super(name, email, passwordHash, "tester");
        this.testerId = testerId;
        this.projects = projects;
        this.reportedBugs = reportedBugs;
    }

    public Tester(String janeSmith, String mail, int testerId) {
        super();
        this.testerId = testerId;
    }

    public Tester(int testerId) {
        super();
        this.testerId = testerId;
    }


    /**
     * Retrieves the unique identifier of the tester.
     * @return The tester ID.
     */
    public int getTesterId() {
        return testerId;
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


    @Override
    public String toString() {
        return super.toString() + "Tester{" +
                "testerId=" + testerId +
                ", projects=" + projects +
                ", reportedBugs=" + reportedBugs +
                '}';
    }
}