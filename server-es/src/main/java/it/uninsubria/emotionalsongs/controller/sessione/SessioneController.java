package it.uninsubria.emotionalsongs.controller.sessione;

import com.sun.net.httpserver.HttpExchange;
import it.uninsubria.emotionalsongs.config.ApiConfig;
import it.uninsubria.emotionalsongs.controller.Controller;
import it.uninsubria.emotionalsongs.model.sessione.Sessione;
import it.uninsubria.emotionalsongs.model.utente.Utente;
import it.uninsubria.emotionalsongs.service.SharedService;
import it.uninsubria.emotionalsongs.service.sessione.SessioneService;
import it.uninsubria.emotionalsongs.utils.Costanti;
import it.uninsubria.emotionalsongs.utils.Logger;
import it.uninsubria.emotionalsongs.utils.Utils;

import java.io.IOException;
import java.util.Map;

import static it.uninsubria.emotionalsongs.utils.Utils.isNull;

/**
 * Questa classe è responsabile della gestione delle richieste HTTP relative alle sessioni utente.
 * @author Erik Gurzau (749400, VA)
 * @author Alessia Metaj (738945, VA)
 * @author Sara Biavaschi (748698, VA)
 * @version 2.0.0
 * @see it.uninsubria.emotionalsongs.config.ApiConfig
 * @see it.uninsubria.emotionalsongs.controller.Controller
 * @see it.uninsubria.emotionalsongs.model.sessione.Sessione
 * @see it.uninsubria.emotionalsongs.service.SharedService
 * @see it.uninsubria.emotionalsongs.service.sessione.SessioneService
 * @see it.uninsubria.emotionalsongs.utils.Costanti
 * @see it.uninsubria.emotionalsongs.utils.Logger
 * @see it.uninsubria.emotionalsongs.utils.Utils
 */
public class SessioneController extends Controller implements ApiConfig {

    /**
     * Istanza del servizio di sessione.
     */
    private final SessioneService sessioneService;

    /**
     * Costruttore della classe
     */
    public SessioneController() {
        sessioneService = SharedService.getSessioneService();
    }

    /**
     * Seleziona il metodo specifico che deve gestire la richiesta HTTP.
     * @param exchange Oggetto contenente la richiesta HTTP del client e usato per inviare la risposta
     * @throws IOException se occorrono errori di tipo I/O
     */
    public void handle(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        String method = exchange.getRequestMethod();
        Logger.info(this.getClass().getSimpleName() + ": " + path + " " + method);

        if (
            SessioneApi.CREA_SESSIONE_AUTO_ID.match(path, method) ||
            SessioneApi.CREA_SESSIONE_ID.match(path, method)
        ) {
            Logger.info(this.getClass().getSimpleName() + ": gestisciCreaSessione");
            Map<String, String> pathVariables = Utils.getPathVariables(SessioneApi.GET_SESSIONE_BY_ID.getPath(), path);
            String sessionId = pathVariables.get("sessionId");
            gestisciCreaSessione(exchange, sessionId);
        }
        else if (SessioneApi.GET_SESSIONE_BY_ID.match(path, method)) {
            Logger.info(this.getClass().getSimpleName() + ": gestisciGetSessione");
            Map<String, String> pathVariables = Utils.getPathVariables(SessioneApi.GET_SESSIONE_BY_ID.getPath(), path);
            String sessionId = pathVariables.get("sessionId");
            gestisciGetSessione(exchange, sessionId);
        }
        else sendResponse(exchange, Costanti.ErrorCode.PAGE_NOT_FOUND, Costanti.ErrorCode.PAGE_NOT_FOUND.getStatusCode());
    }

    /**
     * Gestisce la richiesta di creazione di una nuova sessione.
     * @param exchange Oggetto contenente la richiesta HTTP del client e usato per inviare la risposta
     * @param sessionId L'ID della sessione
     */
    private void gestisciCreaSessione(HttpExchange exchange, String sessionId) throws IOException {
        Utente utente = getRequestBody(exchange, Utente.class);
        Sessione sessione = sessioneService.creaSessione(sessionId, utente.getEmail(), utente.getPassword());
        if(!isNull(sessione))
            sendResponse(exchange, sessione.getSessionId(), Costanti.StatusCode.CREATED);
        else
            sendResponse(exchange, Costanti.ErrorCode.SESSION_NON_VALIDA, Costanti.ErrorCode.SESSION_NON_VALIDA.getStatusCode());
    }

    /**
     * Gestisce la richiesta di ricerca della sessione con l'ID fornito.
     * @param exchange Oggetto contenente la richiesta HTTP del client e usato per inviare la risposta
     * @param sessionId L'ID della sessione
     */
    private void gestisciGetSessione(HttpExchange exchange, String sessionId) throws IOException {
        Sessione sessione = sessioneService.getSessione(sessionId);
        if(!isNull(sessione))
            sendResponse(exchange, sessione, Costanti.StatusCode.CREATED);
        else
            sendResponse(exchange, Costanti.ErrorCode.SESSION_NON_VALIDA, Costanti.ErrorCode.SESSION_NON_VALIDA.getStatusCode());
    }

}
