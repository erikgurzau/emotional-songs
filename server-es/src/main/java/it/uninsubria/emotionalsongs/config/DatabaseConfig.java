package it.uninsubria.emotionalsongs.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Questa interfaccia fornisce le informazioni di configurazione necessarie
 * per stabilire una connessione con il database PostgreSQL.
 * @author Erik Gurzau (749400, VA)
 * @author Alessia Metaj (738945, VA)
 * @author Sara Biavaschi (748698, VA)
 * @version 2.0.0
 * @see it.uninsubria.emotionalsongs.model.api.API
 * @see it.uninsubria.emotionalsongs.utils.Costanti
 */
public interface DatabaseConfig {

    /**
     * Nome dell'host.
     */
    String HOST = "localhost";

    /**
     * Nome del database.
     */
    String DATABASE_NAME = "emotional_songs";

    /**
     * Nome dell'utente.
     */
    String USERNAME = "postgres";

    /**
     * Password.
     */
    String PASSWORD = "admin";

    /**
     * Ritorna un'istanza di Connection rappresentante la connessione con il database.
     * @return La connessione creata
     * @throws SQLException se la connessione fallisce
     */
    static Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://" + HOST + "/" + DATABASE_NAME;
        return DriverManager.getConnection(url, USERNAME, PASSWORD);
    }

}
