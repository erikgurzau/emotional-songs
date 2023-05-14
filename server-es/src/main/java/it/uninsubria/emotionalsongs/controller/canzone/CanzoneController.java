package it.uninsubria.emotionalsongs.controller.canzone;

import com.sun.net.httpserver.HttpExchange;
import it.uninsubria.emotionalsongs.controller.Controller;
import it.uninsubria.emotionalsongs.model.canzone.Canzone;
import it.uninsubria.emotionalsongs.model.utente.Utente;
import it.uninsubria.emotionalsongs.service.LoggerService;
import it.uninsubria.emotionalsongs.service.canzone.CanzoneService;
import it.uninsubria.emotionalsongs.service.utente.UtenteService;

import java.io.IOException;
import java.util.List;

public class CanzoneController extends Controller {
    private final CanzoneService canzoneService;

    public static final String PATH_BASE_CONTROLLER = PATH_ROOT_API + PATH_CANZONE_API;

    public CanzoneController() {
        canzoneService = new CanzoneService();
    }

    public void handle(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        String method = exchange.getRequestMethod();
        LoggerService.info(this.getClass().getSimpleName() + ": " + path + " " + method);

        if (path.matches(PATH_BASE_CONTROLLER) && method.equals("GET")) {
            LoggerService.info(this.getClass().getSimpleName() + ": gestisciGetCanzoni");
            gestisciGetCanzoni(exchange);
        }
//        else if (path.equals(PATH_BASE_CONTROLLER + "/crea") && method.equals("POST")) {
//            LoggerService.info("CanzoneController: gestisciCreaUtente");
//            gestisciCreaUtente(exchange);
//        }
//        else if (path.matches(PATH_BASE_CONTROLLER + "/\\d+") && method.equals("GET")) {
//            LoggerService.info("CanzoneController: gestisciGetUtentiById");
//        }
//        else sendResponse(exchange, "risorsa non trovata", 404);
    }


    private void gestisciGetCanzoni(HttpExchange exchange) throws IOException {
        List<Canzone> canzoni = canzoneService.getAll();
        sendResponse(exchange, canzoni, 200);
    }





}
