package it.uninsubria.controller.utente;

import com.sun.net.httpserver.HttpExchange;
import it.uninsubria.controller.HttpController;
import it.uninsubria.service.APIService;
import it.uninsubria.service.LoggerService;

import java.io.IOException;

public class UtenteController implements HttpController {
    private final String codiceGruppo = "UTENTE";
    private APIService apiService;
    public UtenteController() {
        apiService = new APIService();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        LoggerService.info("UtenteController gestisce " +
                exchange.getRequestURI().toString() +
                " " + exchange.getRequestMethod());

    }
}
