package org.example.services;

import org.example.models.Bug;
import org.example.models.Developer;
import org.example.models.Project;
import org.example.models.Tester;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The {@code IProjectService} interface provides methods to manage project-related operations
 * within the system. This includes creating projects, assigning developers and testers,
 * retrieving project details, and managing project participants and bugs.
 */
public interface IProjectService {

    /**
     * Creates a new project in the system.
     *
     * @param name              The name of the project.
     * @param startDate         The start date of the project.
     * @param projectManagerId  The ID of the project manager responsible for the project.
     * @return The newly created {@link Project} object.
     */
    Project createProject(String name, LocalDateTime startDate, int projectManagerId);

    /**
     * Assigns a developer to a project.
     *
     * @param projectId The unique ID of the project.
     * @param userId    The unique ID of the developer to be assigned to the project.
     */
    void assignDeveloper(int projectId, int userId);

    /**
     * Assigns a tester to a project.
     *
     * @param projectId The unique ID of the project.
     * @param userId    The unique ID of the tester to be assigned to the project.
     */
    void assignTester(int projectId, int userId);

    /**
     * Retrieves a project by its unique ID.
     *
     * @param projectId The unique ID of the project.
     * @return The {@link Project} object representing the project with the specified ID.
     */
    Project getProject(int projectId);

    /**
     * Retrieves all projects assigned to a specific tester.
     *
     * @param testerId The unique ID of the tester.
     * @return A {@link List} of {@link Project} objects that are assigned to the specified tester.
     */
    List<Project> getProjectsByTester(int testerId);

    /**
     * Retrieves all projects managed by a specific project manager.
     *
     * @param managerId The unique ID of the project manager.
     * @return A {@link List} of {@link Project} objects managed by the specified project manager.
     */
    List<Project> getProjectsByManager(int managerId);

    /**
     * Retrieves all projects assigned to a specific developer.
     *
     * @param developerId The unique ID of the developer.
     * @return A {@link List} of {@link Project} objects assigned to the specified developer.
     */
    List<Project> getProjectByDeveloper(int developerId);

    /**
     * Retrieves all developers assigned to a specific project.
     *
     * @param projectId The unique ID of the project.
     * @return A {@link List} of {@link Developer} objects assigned to the specified project.
     */
    List<Developer> getAssignedDevelopers(int projectId);

    /**
     * Retrieves all testers assigned to a specific project.
     *
     * @param projectId The unique ID of the project.
     * @return A {@link List} of {@link Tester} objects assigned to the specified project.
     */
    List<Tester> getAssignedTesters(int projectId);

    /**
     * Retrieves all bugs reported within a specific project.
     *
     * @param projectId The unique ID of the project.
     * @return A {@link List} of {@link Bug} objects associated with the specified project.
     */
    List<Bug> getProjectBugs(int projectId);
}
