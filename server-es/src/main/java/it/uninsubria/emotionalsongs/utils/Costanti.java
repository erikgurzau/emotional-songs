package it.uninsubria.emotionalsongs.utils;

public class Costanti {

    public static final int PORTA_SERVER = 5555;
    public static final String LOG_LEVEL = LogLevel.INFO;
    public static final String DATA_ORA_PATTERN = "yyyy-MM-dd HH:mm:ss";


    public class HttpMethod {
        public static final String GET = "GET";
        public static final String POST = "POST";
        public static final String PUT = "PUT";
        public static final String DELETE = "DELETE";
        public static final String PATCH = "PATCH";
        public static final String HEAD = "HEAD";
        public static final String OPTIONS = "OPTIONS";
    }

    public static class LogLevel {
        public static final String INFO = "INFO";
        public static final String DEBUG = "DEBUG";
    }


}



