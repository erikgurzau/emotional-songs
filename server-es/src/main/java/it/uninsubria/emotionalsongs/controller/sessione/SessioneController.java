package it.uninsubria.emotionalsongs.controller.sessione;

import com.sun.net.httpserver.HttpExchange;
import it.uninsubria.emotionalsongs.config.SessioneApiConfig;
import it.uninsubria.emotionalsongs.controller.Controller;
import it.uninsubria.emotionalsongs.model.sessione.Sessione;
import it.uninsubria.emotionalsongs.model.utente.Utente;
import it.uninsubria.emotionalsongs.service.SharedService;
import it.uninsubria.emotionalsongs.service.sessione.SessioneService;
import it.uninsubria.emotionalsongs.utils.Logger;

import java.io.IOException;
import java.util.Map;

import static it.uninsubria.emotionalsongs.utils.Utils.isNull;

public class SessioneController extends Controller implements SessioneApiConfig {

    private final SessioneService sessioneService;

    public SessioneController() {
        sessioneService = SharedService.getSessioneService();
    }

    public void
    handle(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        String method = exchange.getRequestMethod();
        Logger.info(this.getClass().getSimpleName() + ": " + path + " " + method);

        if (method.equals("POST") && isPathMatching(CREA_SESSIONE, path)) {
            Logger.info(this.getClass().getSimpleName() + ": gestisciCreaSessione");
            gestisciCreaSessione(exchange);
        }
        else if (method.equals("GET") && isPathMatching(GET_SESSIONE_BY_ID, path)) {
            Logger.info(this.getClass().getSimpleName() + ": gestisciGetSessione");
            Map<String, String> pathVariables = getPathVariables(GET_SESSIONE_BY_ID, path);
            String sessionId = pathVariables.get("sessionId");
            gestisciGetSessione(exchange, sessionId);
        }
        else sendResponse(exchange, "risorsa non trovata", 404);
    }

    private void gestisciCreaSessione(HttpExchange exchange) throws IOException {
        Utente utente = getRequestBody(exchange, Utente.class);
        Sessione sessione = sessioneService.creaSessione(utente.getEmail(), utente.getPassword());
        if(!isNull(sessione))
            sendResponse(exchange, sessione.getSessionId(), 201);
        else
            sendResponse(exchange, "sessione non creata", 400);

    }

    private void gestisciGetSessione(HttpExchange exchange, String sessionId) throws IOException {
        Sessione sessione = sessioneService.getSessione(sessionId);
        if(!isNull(sessione))
            sendResponse(exchange, sessione, 201);
        else
            sendResponse(exchange, "sessione non trovata", 400);
    }








}
