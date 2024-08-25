package org.example.models;

import java.time.LocalDateTime;
import java.util.List;


/**
 * The Developer class extends the User class and represents a developer in the system.
 * A Developer is responsible for working on bugs and implementing features in the projects.
 * <p>
 * Fields:
 * - String developerId: A unique identifier for the developer.
 * - List<String> projectIds: The list of project IDs to which the developer is currently assigned.
 * - List<String> assignedBugIds: The list of bug IDs assigned to the developer for resolution.
 */
public class Developer extends User {
    private int developerId;
    private List<String> projectIds;
    private List<String> assignedBugIds;


    /**
     * Constructs a new Developer with the specified developer ID.
     *
     * @param developerId  The unique identifier for the developer.
     * @param id           The user ID.
     * @param name         The user's name.
     * @param email        The user's email address.
     * @param passwordHash The hashed password for authentication.
     * @param role         The user's role (e.g., "developer").
     */
    public Developer(int id, int developerId, String name, String email,
                     String passwordHash) {
        super(id, name, email, passwordHash, "developer"); // Call the User class constructor
        this.developerId = developerId;
    }

    /**
     * Constructs a new Developer with the specified developer ID, project IDs, and assigned bug IDs.
     *
     * @param developerId    The unique identifier for the developer.
     * @param projectIds     The list of project IDs to which the developer is assigned.
     * @param assignedBugIds The list of bug IDs assigned to the developer for resolution.
     * @param id             The user ID.
     * @param name           The user's name.
     * @param email          The user's email address.
     * @param passwordHash   The hashed password for authentication.
     * @param role           The user's role (e.g., "developer").
     */
    public Developer(int id, int developerId, List<String> projectIds, List<String> assignedBugIds,
                     String name, String email, String passwordHash) {
        super(id, name, email, passwordHash, "developer"); // Call the User class constructor
        this.developerId = developerId;
        this.projectIds = projectIds;
        this.assignedBugIds = assignedBugIds;

    }

    public Developer(int id, String name, String email, String passwordHash) {
        super(id, name, email, passwordHash, "developer");

    }

    public Developer(String johnDoe, String mail) {
        super();
    }

    public Developer(String johnDoe, String mail, String password123, String developer) {
        super();
    }


    /**
     * Gets the unique identifier for the developer.
     *
     * @return The developer's ID.
     */
    public int getDeveloperId() {
        return developerId;
    }

    /**
     * Sets the unique identifier for the developer.
     *
     * @param developerId The developer's ID to set.
     */
    public void setDeveloperId(int developerId) {
        this.developerId = developerId;
    }

    /**
     * Gets the list of project IDs to which the developer is currently assigned.
     *
     * @return The list of project IDs.
     */
    public List<String> getProjectIds() {
        return projectIds;
    }

    /**
     * Sets the list of project IDs to which the developer is currently assigned.
     *
     * @param projectIds The list of project IDs to assign to the developer.
     */
    public void setProjectIds(List<String> projectIds) {
        this.projectIds = projectIds;
    }

    /**
     * Gets the list of bug IDs assigned to the developer for resolution.
     *
     * @return The list of assigned bug IDs.
     */
    public List<String> getAssignedBugIds() {
        return assignedBugIds;
    }

    /**
     * Sets the list of bug IDs assigned to the developer for resolution.
     *
     * @param assignedBugIds The new list of assigned bug IDs.
     */
    public void setAssignedBugIds(List<String> assignedBugIds) {
        this.assignedBugIds = assignedBugIds;
    }


    @Override
    public String toString() {
        return super.toString() + "Developer{" +
                "developerId=" + developerId +
                ", projectIds=" + projectIds +
                ", assignedBugIds=" + assignedBugIds +
                '}';
    }
}
