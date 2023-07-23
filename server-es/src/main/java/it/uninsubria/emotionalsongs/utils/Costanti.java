package it.uninsubria.emotionalsongs.utils;

import it.uninsubria.emotionalsongs.model.errore.Errore;

import java.util.Arrays;

public class Costanti {

    public static final int PORTA_SERVER = 5555;
    public static final String LOG_LEVEL = LogLevel.INFO;
    public static final String DATA_ORA_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final int DIMENSIONE_PAGINA = 100;
    public static final String KEY_SESSION_ID = "sessionId";


    public static class HttpMethod {
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

    public static class ErrorCode {
        public static final Errore PAGE_NOT_FOUND = new Errore(404, Arrays.asList("Pagina non trovata"));
        public static final Errore INTERNAL_SERVER_ERROR = new Errore(500, Arrays.asList("Errore interno del server"));
        public static final Errore BAD_REQUEST = new Errore(400, Arrays.asList("Richiesta non valida"));
        public static final Errore UNAUTHORIZED = new Errore(401, Arrays.asList("Non autorizzato"));
        public static final Errore FORBIDDEN = new Errore(403, Arrays.asList("Accesso vietato"));
        public static final Errore NOT_FOUND = new Errore(404, Arrays.asList("Risorsa non trovata"));
        public static final Errore METHOD_NOT_ALLOWED = new Errore(405, Arrays.asList("Metodo non consentito"));
        public static final Errore TOO_MANY_REQUESTS = new Errore(429, Arrays.asList("Troppe richieste"));
        public static final Errore ID_UTENTE = new Errore(StatusCode.BAD_REQUEST, Arrays.asList("ID utente invalido o mancate"));
        public static final Errore SESSION_NON_VALIDA = new Errore(StatusCode.UNAUTHORIZED, Arrays.asList("Sessione scaduta o mancate"));

    }

    public class StatusCode {
        public static final int OK = 200;
        public static final int CREATED = 201;
        public static final int BAD_REQUEST = 400;
        public static final int UNAUTHORIZED = 401;
        public static final int FORBIDDEN = 403;
        public static final int NOT_FOUND = 404;
        public static final int METHOD_NOT_ALLOWED = 405;
        public static final int INTERNAL_SERVER_ERROR = 500;
    }


}



