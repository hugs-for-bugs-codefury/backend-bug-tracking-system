package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection  {

    // Replace with your actual database URL, username, and password
    private static final String DB_URL = Config.getProperty("db.url");
    private static final String USER = Config.getProperty("db.user");
    private static final String PASSWORD = Config.getProperty("db.password");

    /**
     * Gets a connection to the MySQL database.
     *
     * @return a Connection object.
     * @throws java.sql.SQLException if a database access error occurs.
     */

    public Connection getConnection() throws SQLException {
        // Load MySQL JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC driver not found.", e);
        }

        // Return a connection to the database which is try-with-resource friendly
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }



}