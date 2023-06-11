package it.uninsubria.emotionalsongs.utils;

import static it.uninsubria.emotionalsongs.utils.Utils.formatLocalDateTime;

public class Logger {

    public static boolean info(String ...args) {
        if (Costanti.LOG_LEVEL == Costanti.LogLevel.INFO) {
            for (String arg: args)
                System.out.println(formatLocalDateTime(Costanti.DATA_ORA_PATTERN) + "\t[" + Costanti.LOG_LEVEL + "]\t" + arg);
            return true;
        }
        return false;
    }
    public static boolean debug(String ...args) {
        if (Costanti.LOG_LEVEL == Costanti.LogLevel.DEBUG) {
            for (String arg: args)
                System.out.println(formatLocalDateTime(Costanti.DATA_ORA_PATTERN) + "\t[" + Costanti.LOG_LEVEL + "]\t" + arg);
            return true;
        }
        return false;
    }

    public static void errore(String arg) {
        System.out.println(formatLocalDateTime(Costanti.DATA_ORA_PATTERN) + "\t[ERROR]\t" + arg);
    }


}
