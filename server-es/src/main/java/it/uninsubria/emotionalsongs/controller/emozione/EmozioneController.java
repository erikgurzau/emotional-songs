package it.uninsubria.emotionalsongs.controller.emozione;

import com.sun.net.httpserver.HttpExchange;
import it.uninsubria.emotionalsongs.config.ApiConfig;
import it.uninsubria.emotionalsongs.controller.Controller;
import it.uninsubria.emotionalsongs.model.emozione.Emozione;
import it.uninsubria.emotionalsongs.service.SharedService;
import it.uninsubria.emotionalsongs.service.emozione.EmozioneService;
import it.uninsubria.emotionalsongs.service.sessione.SessioneService;
import it.uninsubria.emotionalsongs.utils.Costanti;
import it.uninsubria.emotionalsongs.utils.Logger;

import java.io.IOException;

public class EmozioneController extends Controller implements ApiConfig {

    private final EmozioneService emozioneService;

    private final SessioneService sessioneService;

    public EmozioneController() {

        emozioneService = SharedService.getEmozioneService();
        sessioneService = SharedService.getSessioneService();

    }


    public void handle(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        String method = exchange.getRequestMethod();
        Logger.info(this.getClass().getSimpleName() + ": " + path + " " + method);


        if (EmozioneApi.CREA_RECENSIONE.match(path, method)) {
            Logger.info(this.getClass().getSimpleName() + ": gestisciCreaRecensione");
            gestisciCreaRecensione(exchange);
        }else{
                sendResponse(exchange, Costanti.ErrorCode.PAGE_NOT_FOUND, Costanti.ErrorCode.PAGE_NOT_FOUND.getStatusCode());
            }
        }

    private void gestisciCreaRecensione(HttpExchange exchange) throws IOException {

        try {
            Emozione emozione = getRequestBody(exchange, Emozione.class);
            if (emozioneService.creaEmozione(emozione))
                sendResponse(exchange, emozione, Costanti.StatusCode.CREATED);
            else
                sendResponse(exchange, Costanti.ErrorCode.PAGE_NOT_FOUND, Costanti.ErrorCode.PAGE_NOT_FOUND.getStatusCode());
        } catch (IOException e) {
            sendResponse(exchange, Costanti.ErrorCode.BAD_REQUEST, Costanti.ErrorCode.BAD_REQUEST.getStatusCode());
            throw new RuntimeException(e);
        }

    }

}
