package it.uninsubria.emotionalsongs.controller.utente;

import com.sun.net.httpserver.HttpExchange;
import it.uninsubria.emotionalsongs.config.ApiConfig;
import it.uninsubria.emotionalsongs.controller.Controller;
import it.uninsubria.emotionalsongs.model.utente.Utente;
import it.uninsubria.emotionalsongs.service.sessione.SessioneService;
import it.uninsubria.emotionalsongs.utils.Costanti;
import it.uninsubria.emotionalsongs.utils.Logger;
import it.uninsubria.emotionalsongs.service.utente.UtenteService;
import it.uninsubria.emotionalsongs.service.SharedService;
import it.uninsubria.emotionalsongs.utils.Utils;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UtenteController extends Controller implements ApiConfig {

    private final UtenteService utenteService;
    private final SessioneService sessioneService;

    public UtenteController() {
        utenteService = SharedService.getUtenteService();
        sessioneService = SharedService.getSessioneService();
    }

    public void handle(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        String method = exchange.getRequestMethod();
        String sessionId = super.getSessionId(exchange);
        Logger.info(this.getClass().getSimpleName() + ": " + path + " " + method);

        //if (!sessioneService.hasSessioneAttiva(sessionId)) {
          //  sendResponse(exchange, Costanti.ErrorCode.SESSION_NON_VALIDA, Costanti.ErrorCode.SESSION_NON_VALIDA.getStatusCode());
        //}

        if (UtenteApi.GET_ALL_UTENTI.match(path, method)) {
            Logger.info(this.getClass().getSimpleName() + ": gestisciGetUtenti");
            gestisciGetUtenti(exchange);
        }
        else if (UtenteApi.REGISTRA_UTENTE.match(path, method)) {
            Logger.info(this.getClass().getSimpleName() + ": gestisciCreaUtente");
            gestisciCreaUtente(exchange);
        }
        else if (UtenteApi.GET_UTENTE_BY_ID.match(path, method)) {
            Logger.info(this.getClass().getSimpleName() + ": gestisciGetUtenteById");
            try {
                Map<String, String> pathVariables = Utils.getPathVariables(UtenteApi.GET_UTENTE_BY_ID.getPath(), path);
                Integer userId = Integer.valueOf(pathVariables.get("userId"));
                gestisciGetUtenteById(exchange, userId);
            }
            catch (Exception e) {
                sendResponse(exchange, Costanti.ErrorCode.ID_UTENTE, Costanti.ErrorCode.ID_UTENTE.getStatusCode());
            }

        }
        else sendResponse(exchange, Costanti.ErrorCode.PAGE_NOT_FOUND, Costanti.ErrorCode.PAGE_NOT_FOUND.getStatusCode());
    }

    private void gestisciGetUtenti(HttpExchange exchange) {
        List<Utente> utenti = utenteService.getAll();
        sendResponse(exchange, utenti, Costanti.StatusCode.OK);
    }

    private void gestisciCreaUtente(HttpExchange exchange){
        try {
            Utente utente = getRequestBody(exchange, Utente.class);

            if(utenteService.createUtente(utente))
                sendResponse(exchange, utente, Costanti.StatusCode.CREATED);
            else
                sendResponse(exchange, Costanti.ErrorCode.NOT_FOUND, Costanti.ErrorCode.NOT_FOUND.getStatusCode());

        } catch (IOException e) {
            sendResponse(exchange, Costanti.ErrorCode.BAD_REQUEST, Costanti.ErrorCode.BAD_REQUEST.getStatusCode());
            throw new RuntimeException(e);
        }
    }

    private void gestisciGetUtenteById(HttpExchange exchange, Integer userId) {
        Utente utente = utenteService.getUtenteById(userId);
        sendResponse(exchange, utente, Costanti.StatusCode.OK);
    }

}
