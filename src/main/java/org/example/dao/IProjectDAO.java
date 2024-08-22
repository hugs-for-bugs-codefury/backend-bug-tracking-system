package org.example.dao;


import org.example.models.Bug;
import org.example.models.Developer;
import org.example.models.Project;
import org.example.models.Tester;

import java.util.Date;
import java.util.List;

/**
 * IProjectDAO defines the data access methods related to Project entities.
 * This interface abstracts the database operations for the Project model.
 *
 * Example methods include but are not limited to:
 * - Project save(Project project): Save a new project to the database.
 * - Project findById(int id): Retrieve a project by its ID.
 * - List<Project> findAll(): Retrieve a list of all projects.
 * - void deleteById(int id): Delete a project by its ID.
 */

public interface IProjectDAO {

    public Project saveProject(Project project);
    public Project findByID(int projectId);
    public List<Project> findProjectsByManager(int managerId);
    public List<Project> findProjectsByTester(int testerId);
    public Project findProjectByDeveloper(int developerId);
    public Project assignTester(int projectId, int testerId);
    public Project assignDeveloper(int projectId, int developerId);
    public List<Developer> getAssignedDevelopers(int projectId);
    public List<Tester> getAssignedTesters(int projectId);
    public List<Bug> getProjectBugs(int projectId);


}
