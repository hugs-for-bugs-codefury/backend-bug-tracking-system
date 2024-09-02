package org.example.util;

import java.sql.Connection;
import java.util.Properties;

public abstract class Config {


    private static final Properties properties = new Properties();


    static {
        properties.put("db.url", "jdbc:mysql://localhost:3306/codefury");
        properties.put("db.user", "root");
        properties.put("db.password", "admin");

    }


    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static void setProperty(String key, String value) {
        if (properties.containsKey(key)) {
            System.out.println("Warning: Property " + key + " already exists. Overwriting value.");
        }
        properties.put(key, value);

    }





}
