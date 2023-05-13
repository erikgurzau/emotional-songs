package it.uninsubria.controller;

import com.sun.net.httpserver.HttpExchange;
import it.uninsubria.controller.canzone.CanzoneController;
import it.uninsubria.controller.utente.UtenteController;
import it.uninsubria.service.LoggerService;

import java.io.IOException;

public class ServerController implements HttpController {

    private UtenteController utenteController;
    private CanzoneController canzoneController;

    public ServerController() {
        utenteController = new UtenteController();
        canzoneController = new CanzoneController();
    }

    @Override
    public void handle(HttpExchange exchange) {
        LoggerService.info("ServerController gestisce " +
                exchange.getRequestURI().toString() +
                " " + exchange.getRequestMethod());
        try {
            redirectToController(exchange);
        }
        catch (IOException e) {
            LoggerService.errore(e.getMessage());
        }
    }

    public void redirectToController(HttpExchange exchange) throws IOException {
        String uri = exchange.getRequestURI().toString();
        switch (uri) {
            case PATH_UTENTE_API:
                utenteController.handle(exchange);
                break;
            case PATH_CANZONE_API:
                canzoneController.handle(exchange);
                break;
        }
    }
}
