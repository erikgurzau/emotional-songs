package it.uninsubria.emotionalsongs.config;

import it.uninsubria.emotionalsongs.utils.LogLevel;

public interface ServerConfig {

    LogLevel LOG_LEVEL = LogLevel.INFO;
    int PORTA = 5555;

    void start();
    void stop();


}
