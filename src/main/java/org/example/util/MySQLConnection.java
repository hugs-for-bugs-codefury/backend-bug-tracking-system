package org.example.util;

import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Set;

public class MySQLConnection  {

    // Replace with your actual database URL, name, and password
    private static final String DB_URL = Config.getProperty("db.url");
    private static final String USER = Config.getProperty("db.user");
    private static final String PASSWORD = Config.getProperty("db.password");

    /**
     * Gets a connection to the MySQL database.
     *
     * @return a Connection object.
     * @throws java.sql.SQLException if a database access error occurs.
     */

    public static Connection getConnection() throws SQLException {
        // Load MySQL JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC driver not found.", e);
        }

        // Return a connection to the database which is try-with-resource friendly
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }


    public static void setup()  {
        try {
            runScript(Paths.get("src/main/resources/schema.sql"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void seed() {
        try {
            runScript(Paths.get("src/main/resources/seed.sql"));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void runScript(Path script) throws SQLException, FileNotFoundException {
        //Registering the Driver
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        //Getting the connection
        Connection con = getConnection();
        System.out.println("Connection established......");
        //Initialize the script runner
        ScriptRunner sr = new ScriptRunner(con);
        //Creating a reader object
        Reader reader = new BufferedReader(new FileReader(script.toString()));
        //Chang this to null so that the log will not be printed in console
        sr.setLogWriter(null);
        //Running the script
        sr.runScript(reader);

    }

  



}