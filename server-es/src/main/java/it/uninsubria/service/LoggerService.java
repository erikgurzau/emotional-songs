package it.uninsubria.service;

import it.uninsubria.config.ServerConfig;
import it.uninsubria.utils.LogLevel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoggerService {

    private static final String pattern = "yyyy-MM-dd HH:mm:ss";
    public static boolean info(String arg) {
        if (ServerConfig.LOG_LEVEL == LogLevel.INFO) {
            System.out.println(formatLocalDateTime(pattern) + "\t[" + ServerConfig.LOG_LEVEL + "]\t" + arg);
            return true;
        }
        return false;
    }
    public static boolean debug(String arg) {
        if (ServerConfig.LOG_LEVEL == LogLevel.DEBUG) {
            System.out.println(formatLocalDateTime(pattern) + "\t[" + ServerConfig.LOG_LEVEL + "]\t" + arg);
            return true;
        }
        return false;
    }

    public static void errore(String arg) {
        System.out.println(formatLocalDateTime(pattern) + "\t[ERRORE]\t" + arg);
    }


    private static String formatLocalDateTime(String pattern) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }


}
