package org.example.dao;


import org.example.models.Project;
import java.util.Date;

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
<<<<<<< HEAD

=======
    void saveProject(Project project);
    Project findProjectById(int projectId);
    List<Project> findProjectsByManager(int managerId);
>>>>>>> refs/remotes/origin/main
}
