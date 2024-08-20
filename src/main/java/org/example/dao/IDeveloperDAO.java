package org.example.dao;

import org.example.models.Developer;

/**
 * IDeveloperDAO defines the data access methods related to Developer entities.
 * This interface abstracts the database operations for the Developer model.
 *
 * Example methods include but are not limited to:
 * - Developer save(Developer developer): Save a new developer to the database.
 * - Developer findById(int id): Retrieve a developer by their ID.
 * - List<Developer> findAll(): Retrieve a list of all developers.
 * - void deleteById(int id): Delete a developer by their ID.
 */

public interface IDeveloperDAO {
    public void saveDeveloper(Developer developer);
    public Developer findDeveloperById(int developerId);
    public Developer findDeveloperByEmail(String email);

}
