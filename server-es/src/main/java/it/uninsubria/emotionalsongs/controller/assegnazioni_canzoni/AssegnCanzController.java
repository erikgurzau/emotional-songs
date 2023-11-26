package it.uninsubria.emotionalsongs.controller.assegnazioni_canzoni;

import com.sun.net.httpserver.HttpExchange;
import it.uninsubria.emotionalsongs.config.ApiConfig;
import it.uninsubria.emotionalsongs.controller.Controller;
import it.uninsubria.emotionalsongs.model.assegnazioni_canzoni.AssegnCanzone;
import it.uninsubria.emotionalsongs.service.SharedService;
import it.uninsubria.emotionalsongs.service.assegnazioni_canzoni.AssegnCanzService;
import it.uninsubria.emotionalsongs.service.sessione.SessioneService;
import it.uninsubria.emotionalsongs.utils.Costanti;
import it.uninsubria.emotionalsongs.utils.Logger;

import java.io.IOException;
import java.util.List;

public class AssegnCanzController extends Controller implements ApiConfig {
    private final AssegnCanzService assegnCanzService;
    private final SessioneService sessioneService;

    public AssegnCanzController() {
        assegnCanzService = SharedService.getAssegnCanzService();
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
        if (ApiConfig.AssegnCanzApi.INSERT_ASSEGNAZIONE.match(path, method)) {
            Logger.info(this.getClass().getSimpleName() + ": gestisciInsertAssegnazione");
            gestisciInsertAssegnazione(exchange);
        }
        else sendResponse(exchange, Costanti.ErrorCode.PAGE_NOT_FOUND, Costanti.ErrorCode.PAGE_NOT_FOUND.getStatusCode());
    }

    private void gestisciInsertAssegnazione(HttpExchange exchange) {
        try {
            AssegnCanzone assegnazione = getRequestBody(exchange, AssegnCanzone.class);
            if(assegnCanzService.insertAssegnazione(assegnazione))
                sendResponse(exchange, assegnazione, Costanti.StatusCode.CREATED);
            else
                sendResponse(exchange, Costanti.ErrorCode.NOT_FOUND, Costanti.ErrorCode.NOT_FOUND.getStatusCode());
        } catch (IOException e) {
            sendResponse(exchange, Costanti.ErrorCode.BAD_REQUEST, Costanti.ErrorCode.BAD_REQUEST.getStatusCode());
            throw new RuntimeException(e);
        }
    }
}
