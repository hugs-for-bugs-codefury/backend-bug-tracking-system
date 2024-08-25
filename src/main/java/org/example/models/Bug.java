package org.example.models;

import java.time.LocalDateTime;

/**
 * The Bug class represents a bug in the bug-tracking system.
 * Bugs are reported by Testers and resolved by Developers.
 * <p>
 * Example properties include:
 * - int bugId: The unique identifier for the bug.
 * - String title: A short, descriptive title of the bug.
 * - String description: A detailed description of the bug.
 * - String severity: The severity level of the bug (e.g., "Low", "Medium", "High").
 * - LocalDateTime createdOn: The timestamp when the bug was reported.
 * - Tester createdBy: The Tester who reported the bug.
 * - Developer assignedTo: The Developer assigned to resolve the bug.
 * - String status: The current status of the bug (e.g., "Open", "In Progress", "Closed").
 * - Project project: The project to which this bug is related.
 */


public class Bug {
    private int bugId;
    private String title;
    private String description;
    private String severity;
    private LocalDateTime createdOn;
    private int createdById;
    private Developer assignedTo;
    private String status;
    private int projectId;

    /**
     * Constructs a Bug object with the specified parameters.
     *
     * @param bugId       The unique identifier for the bug.
     * @param title       The title of the bug.
     * @param description A detailed description of the bug.
     * @param severity    The severity level of the bug (e.g., "Low", "Medium", "High").
     * @param createdOn   The timestamp when the bug was reported.
     * @param createdById The id of tester who reported the bug.
     * @param projectId   The project id to which this bug is related.
     */
    public Bug(int bugId, String title, String description, String severity, String status, LocalDateTime createdOn, int createdById, int projectId) {
        this.bugId = bugId;
        this.title = title;
        this.description = description;
        this.severity = severity;
        this.status = status;
        this.createdOn = createdOn;
        this.createdById = createdById;
        this.projectId = projectId;
    }


    public Bug(String title, String description, String severity, String status, LocalDateTime createdOn, int createdById, int projectId) {
        this.title = title;
        this.description = description;
        this.severity = severity;
        this.status = status;
        this.createdOn = createdOn;
        this.createdById = createdById;
        this.projectId = projectId;

    }

    public Bug(String testBug, String description) {
    }


    /**
     * Gets the unique identifier for the bug.
     *
     * @return The bug's ID.
     */
    public int getId() {
        return bugId;
    }

    /**
     * Sets the unique identifier for the bug.
     *
     * @param bugId The bug's new ID.
     */
    public void setId(int bugId) {
        this.bugId = bugId;
    }

    /**
     * Gets the title of the bug.
     *
     * @return The bug's title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the bug.
     *
     * @param title The bug's new title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the description of the bug.
     *
     * @return The bug's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the bug.
     *
     * @param description The bug's new description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the severity level of the bug.
     *
     * @return The severity level of the bug.
     */
    public String getSeverity() {
        return severity;
    }

    /**
     * Sets the severity level of the bug.
     *
     * @param severity The bug's new severity level.
     */
    public void setSeverity(String severity) {
        this.severity = severity;
    }

    /**
     * Gets the timestamp when the bug was reported.
     *
     * @return The bug's creation timestamp.
     */
    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    /**
     * Sets the timestamp when the bug was reported.
     *
     * @param createdOn The bug's new creation timestamp.
     */
    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    /**
     * Gets the Tester who reported the bug.
     *
     * @return The Tester who created the bug.
     */
    public int getCreatedById() {
        return createdById;
    }

    /**
     * Sets the Tester who reported the bug.
     *
     * @param createdBy The bug's new creator (Tester).
     */
    public void setCreatedById(int createdById) {
        this.createdById = createdById;
    }

    /**
     * Gets the Developer assigned to resolve the bug.
     *
     * @return The Developer assigned to the bug.
     */
    public Developer getAssignedTo() {
        return assignedTo;
    }

    /**
     * Sets the Developer assigned to resolve the bug.
     *
     * @param assignedTo The new Developer assigned to the bug.
     */
    public void setAssignedTo(Developer assignedTo) {
        this.assignedTo = assignedTo;
    }

    /**
     * Gets the current status of the bug.
     *
     * @return The bug's status (e.g., "Open", "In Progress", "Closed").
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the current status of the bug.
     *
     * @param status The bug's new status.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets the project to which this bug is related.
     *
     * @return The project associated with the bug.
     */
    public int getProjectId() {
        return projectId;
    }

    /**
     * Sets the project to which this bug is related.
     *
     * @param project The bug's new associated project.
     */
    public void setProject(int projectId) {
        this.projectId = projectId;
    }


    @Override
    public String toString() {
        return "Bug{" +
                "bugId=" + bugId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", severity='" + severity + '\'' +
                ", createdOn=" + createdOn +
                ", createdById=" + createdById +
                ", assignedTo=" + assignedTo +
                ", status='" + status + '\'' +
                ", projectId=" + projectId +
                '}';
    }
}
