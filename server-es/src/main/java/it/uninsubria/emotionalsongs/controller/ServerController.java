package it.uninsubria.emotionalsongs.controller;

import com.sun.net.httpserver.HttpExchange;
import it.uninsubria.emotionalsongs.controller.canzone.CanzoneController;
import it.uninsubria.emotionalsongs.controller.utente.UtenteController;
import it.uninsubria.emotionalsongs.service.LoggerService;

import java.io.IOException;

public class ServerController extends Controller {

    private final UtenteController utenteController;
    private final CanzoneController canzoneController;

    public ServerController() {
        utenteController = new UtenteController();
        canzoneController = new CanzoneController();
    }

    @Override
    public void handle(HttpExchange exchange) {
        try {
            redirectToController(exchange);
        }
        catch (IOException e) {
            LoggerService.errore(e.getMessage());
        }
    }

    public void redirectToController(HttpExchange exchange) throws IOException {
        String pathURI = exchange.getRequestURI().toString();
        String pathController = "/" + pathURI.split("/")[2];
        switch (pathController) {
            case PATH_UTENTE_API -> utenteController.handle(exchange);
            case PATH_CANZONE_API -> canzoneController.handle(exchange);
        }
    }
}
