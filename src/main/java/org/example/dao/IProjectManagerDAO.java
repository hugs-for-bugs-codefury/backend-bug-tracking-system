package org.example.dao;

import org.example.models.ProjectManager;

/**
 * IProjectManagerDAO defines the data access methods related to ProjectManager entities.
 * This interface abstracts the database operations for the ProjectManager model.
 *
 * Example methods include but are not limited to:
 * - ProjectManager save(ProjectManager manager): Save a new project manager to the database.
 * - ProjectManager findById(int id): Retrieve a project manager by their ID.
 * - List<ProjectManager> findAll(): Retrieve a list of all project managers.
 * - void deleteById(int id): Delete a project manager by their ID.
 */

public interface IProjectManagerDAO {
    public  ProjectManager saveUser(String name, String email, String password);
    public ProjectManager findByID(int managerId);
    public ProjectManager findByEmail(String email);


}
