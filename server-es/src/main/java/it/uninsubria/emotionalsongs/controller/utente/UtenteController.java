package it.uninsubria.emotionalsongs.controller.utente;

import com.sun.net.httpserver.HttpExchange;
import it.uninsubria.emotionalsongs.controller.Controller;
import it.uninsubria.emotionalsongs.model.utente.Utente;
import it.uninsubria.emotionalsongs.service.LoggerService;
import it.uninsubria.emotionalsongs.service.utente.UtenteService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class UtenteController extends Controller {

    private final UtenteService utenteService;
    public static final String PATH_BASE_CONTROLLER = PATH_ROOT_API + PATH_UTENTE_API;

    public UtenteController() {
        utenteService = new UtenteService();
    }

    public void
    handle(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        String method = exchange.getRequestMethod();
        LoggerService.info("UtenteController: " + path + " " + method);

        if (path.matches(PATH_BASE_CONTROLLER) && method.equals("GET")) {
            LoggerService.info("UtenteController: gestisciGetUtenti");
            gestisciGetUtenti(exchange);
        }
        else if (path.equals(PATH_BASE_CONTROLLER + "/crea") && method.equals("POST")) {
            LoggerService.info("UtenteController: gestisciCreaUtente");
            gestisciCreaUtente(exchange);
        }
        else if (path.matches(PATH_BASE_CONTROLLER + "/(?<userId>[^/]+)") && method.equals("GET")) {
            String[] pathParamsNames = {"userId"};
            Map<String, String> mapPathParams = getPathParams(path, PATH_BASE_CONTROLLER + "/(?<userId>[^/]+)", pathParamsNames);
            Integer userId = Integer.valueOf(mapPathParams.get("userId"));
            LoggerService.info("UtenteController: gestisciGetUtenteById");
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
