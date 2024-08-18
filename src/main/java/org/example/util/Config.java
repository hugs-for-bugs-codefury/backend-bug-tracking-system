package org.example.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

public abstract class Config {

    private static final String db_url = "jdbc:mysql://localhost:3306/codefury";
    private static final String db_user = "root";
    private static final String db_password = "admin";



    private static final Properties properties = new Properties();


    static {
        properties.put("db.url", db_url);
        properties.put("db.user", db_user);
        properties.put("db.password", db_password);

    }



    public static String getProperty(String key) {
        return properties.getProperty(key);
    }



}
