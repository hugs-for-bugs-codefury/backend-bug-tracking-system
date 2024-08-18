package org.example.models;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The Project class represents a project in the bug-tracking system.
 * A project is managed by a Project Manager and worked on by Developers and Testers.
 *
 * Example properties include:
 * - int id: The unique identifier for the project.
 * - String name: The name of the project.
 * - LocalDate startDate: The start date of the project.
 * - String status: The status of the project (e.g., "In Progress", "Completed").
 * - ProjectManager manager: The Project Manager responsible for the project.
 * - List<User> teamMembers: The list of team members (developers and testers) assigned to the project.
 * - List<Bug> bugs: The list of bugs reported in the project.
 */

public class Project {
    private String projectId;
    private String projectName;
    private LocalDateTime startDate;
    private String status;
    private String projectManagerId;
    private List<String> teamMembers;
    private List<Bug> bugs; // New property for bugs

    /**
     * Constructor with all fields.
     * Initializes a new instance of the Project class with the specified values.
     *
     * @param projectId        The unique identifier for the project.
     * @param projectName      The name of the project.
     * @param startDate        The start date of the project.
     * @param status           The current status of the project (e.g., In Progress, Completed).
     * @param projectManagerId The unique identifier of the project manager.
     * @param teamMembers      The list of unique identifiers for the team members assigned to the project.
     */
    public Project(String projectId, String projectName, LocalDateTime startDate,
                   String status, String projectManagerId, List<String> teamMembers) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.startDate = startDate;
        this.status = status;
        this.projectManagerId = projectManagerId;
        this.teamMembers = teamMembers;
    }

    /**
     * Retrieves the unique identifier of the project.
     *
     * @return the project ID as a String.
     */
    public String getProjectId() {
        return projectId;
    }

    /**
     * Sets or updates the unique identifier of the project.
     *
     * @param projectId the new project ID to set.
     */
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    /**
     * Retrieves the name of the project.
     *
     * @return the project name as a String.
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * Sets or updates the name of the project.
     *
     * @param projectName the new project name to set.
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * Retrieves the start date of the project.
     *
     * @return the start date of the project as a LocalDateTime.
     */
    public LocalDateTime getStartDate() {
        return startDate;
    }

    /**
     * Sets or updates the start date of the project.
     *
     * @param startDate the new start date to set.
     */
    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    /**
     * Retrieves the current status of the project.
     *
     * @return the project status as a String (e.g., "In Progress", "Completed").
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets or updates the current status of the project.
     *
     * @param status the new status to set (e.g., "In Progress", "Completed").
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Retrieves the identifier of the project manager responsible for the project.
     *
     * @return the project manager's ID as a String.
     */
    public String getProjectManagerId() {
        return projectManagerId;
    }

    /**
     * Sets or updates the identifier of the project manager responsible for the project.
     *
     * @param projectManagerId the new project manager ID to set.
     */
    public void setProjectManagerId(String projectManagerId) {
        this.projectManagerId = projectManagerId;
    }

    /**
     * Retrieves the list of team members assigned to the project.
     *
     * @return a list of team members, where each member is represented by a String (e.g., member ID or name).
     */
    public List<String> getTeamMembers() {
        return teamMembers;
    }

    /**
     * Sets or updates the list of team members assigned to the project.
     *
     * @param teamMembers the new list of team members to assign to the project.
     */
    public void setTeamMembers(List<String> teamMembers) {
        this.teamMembers = teamMembers;
    }

    /**
     * Retrieves the list of bugs reported in the project.
     *
     * @return a list of bugs.
     */
    public List<Bug> getBugs() {
        return bugs;
    }

    /**
     * Sets or updates the list of bugs reported in the project.
     *
     * @param bugs the new list of bugs.
     */
    public void setBugs(List<Bug> bugs) {
        this.bugs = bugs;
    }
}



