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

/**
 * Questa classe è responsabile della gestione delle richieste HTTP relative alle
 * recensioni emozionali di un brano.
 * @author Erik Gurzau (749400, VA)
 * @author Alessia Metaj (738945, VA)
 * @author Sara Biavaschi (748698, VA)
 * @version 2.0.0
 * @see it.uninsubria.emotionalsongs.config.ApiConfig
 * @see it.uninsubria.emotionalsongs.controller.Controller
 * @see it.uninsubria.emotionalsongs.model.emozione.Emozione
 * @see it.uninsubria.emotionalsongs.service.SharedService
 * @see it.uninsubria.emotionalsongs.service.emozione.EmozioneService
 * @see it.uninsubria.emotionalsongs.service.sessione.SessioneService
 * @see it.uninsubria.emotionalsongs.utils.Costanti
 * @see it.uninsubria.emotionalsongs.utils.Logger
 */
public class EmozioneController extends Controller implements ApiConfig {

    /**
     * Istanza del servizio relativo alle recensioni emozionali.
     */
    private final EmozioneService emozioneService;

    /**
     * Istanza del servizio di sessione.
     */
    private final SessioneService sessioneService;

    /**
     * Costruttore della classe.
     */
    public EmozioneController() {
        emozioneService = SharedService.getEmozioneService();
        sessioneService = SharedService.getSessioneService();
    }

    /**
     * Seleziona il metodo specifico che deve gestire la richiesta HTTP.
     * @param exchange Oggetto contenente la richiesta HTTP del client e usato per inviare la risposta
     * @throws IOException se occorrono errori di tipo I/O
     */
    public void handle(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        String method = exchange.getRequestMethod();
        String sessionId = super.getSessionId(exchange);
        Logger.info(this.getClass().getSimpleName() + ": " + path + " " + method);

        //if (!sessioneService.hasSessioneAttiva(sessionId)) {
        //  sendResponse(exchange, Costanti.ErrorCode.SESSION_NON_VALIDA, Costanti.ErrorCode.SESSION_NON_VALIDA.getStatusCode());
        //}
        if (EmozioneApi.CREA_RECENSIONE.match(path, method)) {
            Logger.info(this.getClass().getSimpleName() + ": gestisciCreaRecensione");
            gestisciCreaRecensione(exchange);
        } else{
            sendResponse(exchange, Costanti.ErrorCode.PAGE_NOT_FOUND, Costanti.ErrorCode.PAGE_NOT_FOUND.getStatusCode());
        }
    }

    /**
     * Gestisce la richiesta di inserimento di una nuova recensione emozionale.
     * @param exchange Oggetto contenente la richiesta HTTP del client e usato per inviare la risposta
     */
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