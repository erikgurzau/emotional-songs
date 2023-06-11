package it.uninsubria.emotionalsongs.controller.utente;

import com.sun.net.httpserver.HttpExchange;
import it.uninsubria.emotionalsongs.config.ApiConfig;
import it.uninsubria.emotionalsongs.controller.Controller;
import it.uninsubria.emotionalsongs.model.utente.Utente;
import it.uninsubria.emotionalsongs.utils.Logger;
import it.uninsubria.emotionalsongs.service.utente.UtenteService;
import it.uninsubria.emotionalsongs.service.SharedService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class UtenteController extends Controller implements ApiConfig {

    private final UtenteService utenteService;

    public UtenteController() {
        utenteService = SharedService.getUtenteService();
    }

    public void handle(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        String method = exchange.getRequestMethod();
        Logger.info(this.getClass().getSimpleName() + ": " + path + " " + method);


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
            Map<String, String> pathVariables = getPathVariables(UtenteApi.GET_UTENTE_BY_ID.getPath(), path);
            Integer userId = Integer.valueOf(pathVariables.get("userId"));
            gestisciGetUtenteById(exchange, userId);
        }
        else sendResponse(exchange, "risorsa non trovata", 404);
    }

    private void gestisciGetUtenti(HttpExchange exchange) throws IOException {
        List<Utente> utenti = utenteService.getAll();
        sendResponse(exchange, utenti, 200);
    }

    private void gestisciCreaUtente(HttpExchange exchange) throws IOException {
        Utente utente = getRequestBody(exchange, Utente.class);
        if(utenteService.createUtente(utente))
            sendResponse(exchange, utente, 201);
        else
            sendResponse(exchange, "false", 400);

    }

    private void gestisciGetUtenteById(HttpExchange exchange, Integer userId) throws IOException {
        Utente utente = utenteService.getUtenteById(userId);
        sendResponse(exchange, utente, 200);
    }








}
