package it.uninsubria.emotionalsongs.utils;

import it.uninsubria.emotionalsongs.model.errore.Errore;

import java.util.Arrays;

/**
 * Questa classe contiene le costanti utilizzate nell'applicazione.
 * @author Erik Gurzau (749400, VA)
 * @author Alessia Metaj (738945, VA)
 * @author Sara Biavaschi (748698, VA)
 * @version 2.0.0
 */
public class Costanti {


    /** Porta predefinita del server.*/
    public static final int PORTA_SERVER = 5555;

    /** Livello di log predefinito. */
    public static final String LOG_LEVEL = LogLevel.INFO;

    /** Pattern predefinito per la data e l'ora. */
    public static final String DATA_ORA_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /** Dimensione predefinita della pagina. */
    public static final int DIMENSIONE_PAGINA = 100;

    /** Chiave per l'ID della sessione. */
    public static final String KEY_SESSION_ID = "sessionId";


    /** Contiene le costanti per i metodi HTTP. */
    public static class HttpMethod {


        /** Metodo HTTP GET. */
        public static final String GET = "GET";

        /** Metodo HTTP POST. */
        public static final String POST = "POST";

        /** Metodo HTTP PUT. */
        public static final String PUT = "PUT";

        /** Metodo HTTP DELETE. */
        public static final String DELETE = "DELETE";

        /** Metodo HTTP PATCH. */
        public static final String PATCH = "PATCH";

        /** Metodo HTTP HEAD. */
        public static final String HEAD = "HEAD";

        /** Metodo HTTP OPTIONS. */
        public static final String OPTIONS = "OPTIONS";
    }


    /** Contiene le costanti per i livelli di log. */
    public static class LogLevel {

        /** Livello di log INFO. */
        public static final String INFO = "INFO";

        /** Livello di log DEBUG. */
        public static final String DEBUG = "DEBUG";
    }

    /** Contiene le costanti per i codici di errore HTTP. */
    public static class ErrorCode {

        /** Errore 404 - Pagina non trovata. */
        public static final Errore PAGE_NOT_FOUND = new Errore(404, Arrays.asList("Pagina non trovata"));

        /** Errore 500 - Errore interno del server. */
        public static final Errore INTERNAL_SERVER_ERROR = new Errore(500, Arrays.asList("Errore interno del server"));

        /** Errore 400 - Richiesta non valida. */
        public static final Errore BAD_REQUEST = new Errore(400, Arrays.asList("Richiesta non valida"));

        /** Errore 401 - Non autorizzato. */
        public static final Errore UNAUTHORIZED = new Errore(401, Arrays.asList("Non autorizzato"));

        /** Errore 403 - Accesso vietato. */
        public static final Errore FORBIDDEN = new Errore(403, Arrays.asList("Accesso vietato"));

        /** Errore 404 - Risorsa non trovata. */
        public static final Errore NOT_FOUND = new Errore(404, Arrays.asList("Risorsa non trovata"));

        /** Errore 405 - Metodo non consentito. */
        public static final Errore METHOD_NOT_ALLOWED = new Errore(405, Arrays.asList("Metodo non consentito"));

        /** Errore 429 - Troppe richieste. */
        public static final Errore TOO_MANY_REQUESTS = new Errore(429, Arrays.asList("Troppe richieste"));

        /** Errore per ID utente non valido o mancante. */
        public static final Errore ID_UTENTE = new Errore(StatusCode.BAD_REQUEST, Arrays.asList("ID utente invalido o mancate"));

        /** Errore per sessione non valida o scaduta. */
        public static final Errore SESSION_NON_VALIDA = new Errore(StatusCode.UNAUTHORIZED, Arrays.asList("Sessione scaduta o mancate"));

    }

    /** Contiene costanti per i codici di stato HTTP. */
    public class StatusCode {

        /** Codice di stato 200 - OK. */
        public static final int OK = 200;

        /** Codice di stato 201 - Creato. */
        public static final int CREATED = 201;

        /** Codice di stato 400 - Richiesta non valida. */
        public static final int BAD_REQUEST = 400;

        /** Codice di stato 401 - Non autorizzato. */
        public static final int UNAUTHORIZED = 401;

        /** Codice di stato 403 - Accesso vietato. */
        public static final int FORBIDDEN = 403;

        /** Codice di stato 404 - Risorsa non trovata. */
        public static final int NOT_FOUND = 404;

        /** Codice di stato 405 - Metodo non consentito. */
        public static final int METHOD_NOT_ALLOWED = 405;

        /** Codice di stato 500 - Errore interno del server. */
        public static final int INTERNAL_SERVER_ERROR = 500;
    }


}



