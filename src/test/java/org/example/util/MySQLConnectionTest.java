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




    @DisplayName("Test MySQL Connection")
    @Test
    void testConnection() {

        try {
            Connection connection = MySQLConnection.getConnection();
            assertNotNull(connection, "Connection is null");
            assertFalse(connection.isClosed(), "Connection is closed");
            connection.close();
            assertTrue(connection.isClosed(), "Connection is not closed");
        } catch (SQLException e) {
            fail("SQLException: " + e.getMessage());
        }

    }

    @DisplayName("Test DB Setup functionality")
    @Test
    void testSetup() {
        assertDoesNotThrow(MySQLConnection::setup);
    }

    @DisplayName("Test DB Seed functionality")
    @Test
    void testSeed() {
        assertDoesNotThrow(MySQLConnection::seed);
    }

}
