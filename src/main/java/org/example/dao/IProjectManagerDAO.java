package org.example.dao;

import org.example.exceptions.users.UserNotFoundException;
import org.example.models.ProjectManager;

import java.sql.SQLException;

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
    public  ProjectManager saveUser(String name, String email, String password) throws SQLException;
    public ProjectManager findByID(int managerId) throws SQLException, UserNotFoundException;
    public ProjectManager findByEmail(String email) throws SQLException, UserNotFoundException;


}
