package it.uninsubria.controller.utente;

import com.sun.net.httpserver.HttpExchange;
import it.uninsubria.controller.HttpController;
import it.uninsubria.model.utente.Utente;
import it.uninsubria.service.LoggerService;
import it.uninsubria.service.utente.UtenteService;

import java.io.IOException;
import java.util.List;

public class UtenteController implements HttpController {
    private final String codiceGruppo = "UTENTE";

    private UtenteService utenteService;
    public UtenteController() {
        utenteService = new UtenteService();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        LoggerService.info("UtenteController gestisce " +
                exchange.getRequestURI().toString() +
                " " + exchange.getRequestMethod());

        List<Utente> utenti = utenteService.findAll();
        System.out.println(utenti.get(0).toString());

    }
}
