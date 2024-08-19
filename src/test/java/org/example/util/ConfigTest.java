package org.example.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ConfigTest {

    static Config config;


    @DisplayName("Test getProperty() for database ")
    @Test
    void testConfigDB() {



        String regexDBUrl = "jdbc:mysql://[a-zA-Z0-9.]+:[0-9]+/[a-zA-Z0-9]+";
        String regexDBUser = "[a-zA-Z0-9]+";
        String regexDBPassword = "[a-zA-Z0-9]+";

        String property = Config.getProperty("db.url");
        assertNotNull(property, "db.url is null");
        assertTrue(property.matches(regexDBUrl), "db.url is not in the correct format");


        property = Config.getProperty("db.user");
        assertNotNull(property, "db.user is null");
        assertTrue(property.matches(regexDBUser), "db.user is not in the correct format");

        property = Config.getProperty("db.password");
        assertNotNull(property, "db.password is null");
        assertTrue(property.matches(regexDBPassword), "db.password is not in the correct format");


    }

}


