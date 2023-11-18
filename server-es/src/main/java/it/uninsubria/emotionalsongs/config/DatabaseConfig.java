package it.uninsubria.emotionalsongs.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface DatabaseConfig {
    String HOST = "localhost";
    String DATABASE_NAME = "emotional_songs";
    String USERNAME = "postgres";
    String PASSWORD = "programming";

    static Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://" + HOST + "/" + DATABASE_NAME;
        return DriverManager.getConnection(url, USERNAME, PASSWORD);

    }
}
