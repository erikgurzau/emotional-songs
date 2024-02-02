package it.uninsubria.emotionalsongs.utils;

import static it.uninsubria.emotionalsongs.utils.Utils.formatLocalDateTime;


/**
 * Questa classe fornisce delle funzionalità di registrazione all'applicazione.
 * @author Erik Gurzau (749400, VA)
 * @author Alessia Metaj (738945, VA)
 * @author Sara Biavaschi (748698, VA)
 * @version 2.0.0
 */
public class Logger {

    /**
     * Registra dei messaggi di informazione se il livello di log è impostato su INFO.
     * @param args Gli argomenti da registrare.
     * @return {@code true} se la registrazione è riuscita, {@code false} altrimenti.
     */
    public static boolean info(String ...args) {
        if (Costanti.LOG_LEVEL == Costanti.LogLevel.INFO) {
            for (String arg: args)
                System.out.println(formatLocalDateTime(Costanti.DATA_ORA_PATTERN) + "\t[" + Costanti.LOG_LEVEL + "]\t" + arg);
            return true;
        }
        return false;
    }
    /**
     * Registra dei messaggi di debug se il livello di log è impostato su DEBUG.
     * @param args Gli argomenti da registrare.
     * @return {@code true} se la registrazione è riuscita, {@code false} altrimenti.
     */
    public static boolean debug(String ...args) {
        if (Costanti.LOG_LEVEL == Costanti.LogLevel.DEBUG) {
            for (String arg: args)
                System.out.println(formatLocalDateTime(Costanti.DATA_ORA_PATTERN) + "\t[" + Costanti.LOG_LEVEL + "]\t" + arg);
            return true;
        }
        return false;
    }


}
