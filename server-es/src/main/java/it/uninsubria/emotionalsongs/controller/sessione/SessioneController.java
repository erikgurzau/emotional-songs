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

public class SessioneController extends Controller implements ApiConfig {

    private final SessioneService sessioneService;

    public SessioneController() {
        sessioneService = SharedService.getSessioneService();
    }

    public void
    handle(HttpExchange exchange) throws IOException {
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

    private void gestisciCreaSessione(HttpExchange exchange, String sessionId) throws IOException {
        Utente utente = getRequestBody(exchange, Utente.class);
        Sessione sessione = sessioneService.creaSessione(sessionId, utente.getEmail(), utente.getPassword());
        if(!isNull(sessione))
            sendResponse(exchange, sessione.getSessionId(), Costanti.StatusCode.CREATED);
        else
            sendResponse(exchange, Costanti.ErrorCode.SESSION_NON_VALIDA, Costanti.ErrorCode.SESSION_NON_VALIDA.getStatusCode());
    }

    private void gestisciGetSessione(HttpExchange exchange, String sessionId) throws IOException {
        Sessione sessione = sessioneService.getSessione(sessionId);
        if(!isNull(sessione))
            sendResponse(exchange, sessione, Costanti.StatusCode.CREATED);
        else
            sendResponse(exchange, Costanti.ErrorCode.SESSION_NON_VALIDA, Costanti.ErrorCode.SESSION_NON_VALIDA.getStatusCode());
    }








}
