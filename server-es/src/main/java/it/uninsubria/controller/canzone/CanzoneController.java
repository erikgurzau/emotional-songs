package it.uninsubria.controller.canzone;

import com.sun.net.httpserver.HttpExchange;
import it.uninsubria.controller.HttpController;
import it.uninsubria.model.canzone.Canzone;
import it.uninsubria.model.utente.Utente;
import it.uninsubria.service.LoggerService;
import it.uninsubria.service.canzone.utente.CanzoneService;
import it.uninsubria.service.utente.UtenteService;

import java.io.IOException;
import java.util.List;

public class CanzoneController implements HttpController {
    private final String codiceGruppo = "CANZONE";

    private CanzoneService canzoneService;
    public CanzoneController() {
        canzoneService = new CanzoneService();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        LoggerService.info("UtenteController gestisce " +
                exchange.getRequestURI().toString() +
                " " + exchange.getRequestMethod());

        List<Canzone> canzoni = canzoneService.findAll();
        canzoni.forEach(c -> System.out.println(c));

    }
}
