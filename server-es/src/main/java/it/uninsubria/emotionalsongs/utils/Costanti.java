package it.uninsubria.emotionalsongs.utils;

public class Costanti {

    public static final String PATH_ROOT_API = "/emotional-songs";
    public static final String PATH_SESSIONE_API = "/sessione";
    public static final String PATH_UTENTE_API = "/utente";
    public static final String PATH_CANZONE_API = "/canzone";
    public static final int PORTA_SERVER = 5555;

    public static final Costanti.LogLevel LOG_LEVEL = Costanti.LogLevel.INFO;

    public static final String DATA_ORA_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public enum LogLevel {
        INFO,
        DEBUG,
    }
}



