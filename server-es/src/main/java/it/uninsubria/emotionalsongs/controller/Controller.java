package it.uninsubria.emotionalsongs.controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import it.uninsubria.emotionalsongs.utils.Costanti;
import it.uninsubria.emotionalsongs.utils.Utils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Questa classe astratta è responsabile della gestione delle richieste HTTP fornendo
 * l'implementazione dei metodi per elaborare richieste e inviare risposte.
 * @author Erik Gurzau (749400, VA)
 * @author Alessia Metaj (738945, VA)
 * @author Sara Biavaschi (748698, VA)
 * @version 2.0.0
 * @see it.uninsubria.emotionalsongs.utils.Costanti
 * @see it.uninsubria.emotionalsongs.utils.Utils
 */
public abstract class Controller implements HttpHandler {

    /**
     * Seleziona il metodo specifico che deve gestire la richiesta HTTP.
     * @param exchange Oggetto contenente la richiesta HTTP del client e usato per inviare la risposta
     * @throws IOException se occorrono errori di tipo I/O
     */
    public <T> T getRequestBody(HttpExchange exchange, Class<T> clazz) throws IOException {
        return Utils.convertInputStreamToObject(exchange.getRequestBody(), clazz);
    }

    /**
     * Invia la risposta relativa ad una richiesta HTTP, fornendo i dati richiesti e un codice di stato HTTP.
     * @param exchange Oggetto contenente la richiesta HTTP del client e usato per inviare la risposta
     * @param responseObj L'oggetto della risposta
     * @param statusCode Il codice di stato HTTP
     */
    public void sendResponse(HttpExchange exchange, Object responseObj, int statusCode) {
        try {
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(statusCode, 0);

            OutputStream os = exchange.getResponseBody();
            os.write(Utils.convertObjectToBytes(responseObj));
            os.flush();
            os.close();
        } catch (IOException e) {
            sendResponse(exchange, Costanti.ErrorCode.INTERNAL_SERVER_ERROR, Costanti.ErrorCode.INTERNAL_SERVER_ERROR.getStatusCode());
        }
    }

    /**
     * Seleziona l'ID della sessione relativa ad una richiesta.
     * @param exchange Oggetto contenente la richiesta HTTP del client e usato per inviare la risposta
     */
    public String getSessionId(HttpExchange exchange) {
        List<String> params = exchange.getRequestHeaders().get(Costanti.KEY_SESSION_ID);
        if (!Utils.isNull(params) && params.size() > 0)
            return params.get(0).toString();
        return null;
    }

}