package org.example.util;

import java.sql.Connection;
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

    public static void resetDatabase() {


        try (Connection connection = MySQLConnection.getConnection()) {

            String sql = """
                                START TRANSACTION;
                                SET FOREIGN_KEY_CHECKS = 0;
                                TRUNCATE TABLE users;
                                TRUNCATE TABLE project_managers;
                                TRUNCATE TABLE projects;
                                TRUNCATE TABLE developers;
                                TRUNCATE TABLE testers;
                                TRUNCATE TABLE testers_projects;
                                TRUNCATE TABLE developers_projects;
                                TRUNCATE TABLE bugs;
                                SET FOREIGN_KEY_CHECKS = 1;
                                COMMIT;
                    """;
            connection.createStatement().execute(sql);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
