package org.example.util;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

public class MySQLConnectionTest {
    static MySQLConnection mySQLConnection;



    @BeforeAll
    static void setup() {
        mySQLConnection = new MySQLConnection();
    }


    @DisplayName("Test MySQL Connection")
    @Test
    void testConnection() {

        try {
            Connection connection = mySQLConnection.getConnection();
            assertNotNull(connection, "Connection is null");
            assertFalse(connection.isClosed(), "Connection is closed");
            connection.close();
            assertTrue(connection.isClosed(), "Connection is not closed");
        } catch (SQLException e) {
            fail("SQLException: " + e.getMessage());
        }


    }

}
