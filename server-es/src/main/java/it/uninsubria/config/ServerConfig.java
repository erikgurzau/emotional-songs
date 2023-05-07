package it.uninsubria.config;

import it.uninsubria.constant.LogLevel;

public interface ServerConfig {
    LogLevel LOG_LEVEL = LogLevel.INFO;
    String HOST = "localhost";
    int PORTA = 5555;
    int TIMEOUT = 30000;
    String PATH_ROOT_API = "/emotional-songs";
    String PATH_UTENTE_API = PATH_ROOT_API + "/utente";
    String PATH_CANZONE_API = PATH_ROOT_API + "/canzone";


    void start();

    void stop();


}
