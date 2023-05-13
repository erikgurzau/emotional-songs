package it.uninsubria.config;

import it.uninsubria.utils.LogLevel;

public interface ServerConfig {

    LogLevel LOG_LEVEL = LogLevel.INFO;
    String HOST = "localhost";
    int PORTA = 5555;
    Integer TIMEOUT = 30000;

    void start();
    void stop();


}
