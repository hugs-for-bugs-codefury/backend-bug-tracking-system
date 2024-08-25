package org.example.dao;

import org.example.exceptions.users.UserNotFoundException;
import org.example.models.Tester;

import java.sql.SQLException;
import java.util.List;

/**
 * ITesterDAO defines the data access methods related to Tester entities.
 * This interface extends IUserDAO and abstracts the database operations specific to the Tester model.
 *
 * Implementations of this interface should handle operations such as saving a Tester,
 * retrieving Testers by ID or email, finding Testers by project, and more.
 */
public interface ITesterDAO extends IUserDAO {

        /**
         * Saves a new Tester to the database.
         *
         * @param name the name of the Tester.
         * @param email the email address of the Tester (must be unique).
         * @param password the password for the Tester account (will be stored securely).
         * @return the saved Tester instance, including any generated fields like ID.
         * @throws SQLException if a database access error occurs or if a Tester with the same email already exists.
         */
        public Tester saveUser(String name, String email, String password) throws SQLException;

        /**
         * Finds a Tester by their unique identifier (ID).
         *
         * @param testerId the unique identifier of the Tester to be retrieved.
         * @return the Tester with the specified ID.
         * @throws UserNotFoundException if no Tester is found with the specified ID.
         * @throws SQLException if a database access error occurs.
         */
        public Tester findByID(int testerId) throws UserNotFoundException, SQLException;

        /**
         * Finds a Tester by their email address.
         *
         * @param email the email address of the Tester to be retrieved.
         * @return the Tester with the specified email address.
         * @throws UserNotFoundException if no Tester is found with the specified email.
         * @throws SQLException if a database access error occurs.
         */
        public Tester findByEmail(String email) throws UserNotFoundException, SQLException;

        /**
         * Retrieves a list of Testers associated with a specific project.
         *
         * @param projectId the unique identifier of the project for which Testers are to be retrieved.
         * @return a list of Testers assigned to the specified project.
         * @throws SQLException if a database access error occurs.
         */
        public List<Tester> findTestersByProject(int projectId) throws SQLException;
}
