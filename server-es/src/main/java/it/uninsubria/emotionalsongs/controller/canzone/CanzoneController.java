package it.uninsubria.emotionalsongs.controller.canzone;

import com.sun.net.httpserver.HttpExchange;
import it.uninsubria.emotionalsongs.config.ApiConfig;
import it.uninsubria.emotionalsongs.controller.Controller;
import it.uninsubria.emotionalsongs.model.canzone.Canzone;
import it.uninsubria.emotionalsongs.model.pagina.Pagina;
import it.uninsubria.emotionalsongs.service.SharedService;
import it.uninsubria.emotionalsongs.service.canzone.CanzoneService;
import it.uninsubria.emotionalsongs.service.sessione.SessioneService;
import it.uninsubria.emotionalsongs.utils.Costanti;
import it.uninsubria.emotionalsongs.utils.Logger;
import it.uninsubria.emotionalsongs.utils.Utils;

import java.io.IOException;
import java.util.Map;

import static it.uninsubria.emotionalsongs.utils.Utils.isNull;


public class CanzoneController extends Controller implements ApiConfig {
    private final CanzoneService canzoneService;
    private final SessioneService sessioneService;


    public CanzoneController() {
        canzoneService = new CanzoneService();
        sessioneService = SharedService.getSessioneService();
    }

    public void handle(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        String method = exchange.getRequestMethod();
        String sessionId = super.getSessionId(exchange);
        Logger.info(this.getClass().getSimpleName() + ": " + path + " " + method);

        if (!sessioneService.hasSessioneAttiva(sessionId)) {
            sendResponse(exchange, Costanti.ErrorCode.SESSION_NON_VALIDA, Costanti.ErrorCode.SESSION_NON_VALIDA.getStatusCode());
        }
        if (CanzoneApi.GET_ALL_CANZONI.match(path, method)) {
            Logger.info(this.getClass().getSimpleName() + ": gestisciGetCanzoni");
            gestisciGetCanzoni(exchange);
        }
        else if (CanzoneApi.GET_CANZONE_BY_ID.match(path, method)) {
            Logger.info(this.getClass().getSimpleName() + ": gestisciGetCanzoneById");
            Map<String, String> pathVariables = Utils.getPathVariables(CanzoneApi.GET_CANZONE_BY_ID.getPath(), path);
            Integer id = Integer.valueOf(pathVariables.get("id"));
            gestisciGetCanzoneById(exchange, id);
        }
        else sendResponse(exchange, Costanti.ErrorCode.PAGE_NOT_FOUND, Costanti.ErrorCode.PAGE_NOT_FOUND.getStatusCode());
    }


    private void gestisciGetCanzoni(HttpExchange exchange) throws IOException {
        Pagina pagina = getRequestBody(exchange, Pagina.class);

        // Imposta valori predefiniti se mancanti
        if (isNull(pagina))
            pagina = new Pagina();
        int numeroPagina = isNull(pagina.getNumeroPagina()) ? 0 : pagina.getNumeroPagina();
        int dimensionePagina = isNull(pagina.getDimensionePagina()) ? Costanti.DIMENSIONE_PAGINA : pagina.getDimensionePagina();

        // Recupera i dati della pagina
        pagina.setNumeroPagina(numeroPagina);
        pagina.setDimensionePagina(dimensionePagina);
        pagina.setData(canzoneService.getAll(pagina));
        pagina.setTotale(canzoneService.getTotaleCanzoni());

        sendResponse(exchange, pagina, Costanti.StatusCode.OK);
    }


    private void gestisciGetCanzoneById(HttpExchange exchange, Integer canzoneId) throws IOException {
        Canzone canzone = canzoneService.getCanzoneById(canzoneId);
        sendResponse(exchange, canzone, Costanti.StatusCode.OK);
    }

}
