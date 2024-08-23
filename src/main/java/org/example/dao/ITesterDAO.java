package org.example.dao;


import org.example.exceptions.users.UserNotFoundException;
import org.example.models.Bug;
import org.example.models.Tester;

import java.sql.SQLException;
import java.util.List;

/**
 * ITesterDAO defines the data access methods related to Tester entities.
 * This interface abstracts the database operations for the Tester model.
 *
 * Example methods include but are not limited to:
 * - Tester save(Tester tester): Save a new tester to the database.
 * - Tester findById(int id): Retrieve a tester by their ID.
 * - List<Tester> findAll(): Retrieve a list of all testers.
 * - void deleteById(int id): Delete a tester by their ID.
 */

public interface ITesterDAO extends IUserDAO {

        public Tester saveUser(String name, String email, String password) throws SQLException;
        public Tester findByID(int testerId) throws UserNotFoundException, SQLException;
        public Tester findByEmail(String email) throws UserNotFoundException, SQLException;
        public List<Tester> findTestersByProject(int projectId) throws SQLException;

}
