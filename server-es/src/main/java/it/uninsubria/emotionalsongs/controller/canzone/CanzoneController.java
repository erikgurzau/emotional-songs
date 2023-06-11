package it.uninsubria.emotionalsongs.controller.canzone;

import com.sun.net.httpserver.HttpExchange;
import it.uninsubria.emotionalsongs.config.ApiConfig;
import it.uninsubria.emotionalsongs.controller.Controller;
import it.uninsubria.emotionalsongs.model.canzone.Canzone;
import it.uninsubria.emotionalsongs.utils.Logger;
import it.uninsubria.emotionalsongs.service.canzone.CanzoneService;
import it.uninsubria.emotionalsongs.utils.Utils;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public class CanzoneController extends Controller implements ApiConfig {
    private final CanzoneService canzoneService;

    public CanzoneController() {
        canzoneService = new CanzoneService();
    }

    public void handle(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        String method = exchange.getRequestMethod();
        Logger.info(this.getClass().getSimpleName() + ": " + path + " " + method);

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
        else sendResponse(exchange, "risorsa non trovata", 404);
    }


    private void gestisciGetCanzoni(HttpExchange exchange) throws IOException {
        List<Canzone> canzoni = canzoneService.getAll();
        sendResponse(exchange, canzoni, 200);
    }

    private void gestisciGetCanzoneById(HttpExchange exchange, Integer canzoneId) throws IOException {
        Canzone canzone = canzoneService.getCanzoneById(canzoneId);
        sendResponse(exchange, canzone, 200);
    }





}
