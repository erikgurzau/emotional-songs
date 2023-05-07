package it.uninsubria.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface DatabaseConfig {
    String HOST = "localhost";
    String DATABASE_NAME = "database_name";
    String USERNAME = "username";
    String PASSWORD = "password";

    static Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://" + HOST + "/" + DATABASE_NAME;
        return DriverManager.getConnection(url, USERNAME, PASSWORD);
    }
}
