package it.uninsubria.emotionalsongs.controller.utente;

import com.sun.net.httpserver.HttpExchange;
import it.uninsubria.emotionalsongs.config.ApiConfig;
import it.uninsubria.emotionalsongs.controller.Controller;
import it.uninsubria.emotionalsongs.model.utente.Utente;
import it.uninsubria.emotionalsongs.service.sessione.SessioneService;
import it.uninsubria.emotionalsongs.utils.Costanti;
import it.uninsubria.emotionalsongs.utils.Logger;
import it.uninsubria.emotionalsongs.service.utente.UtenteService;
import it.uninsubria.emotionalsongs.service.SharedService;
import it.uninsubria.emotionalsongs.utils.Utils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Questa classe è responsabile della gestione delle richieste HTTP relative agli utenti.
 * @author Erik Gurzau (749400, VA)
 * @author Alessia Metaj (738945, VA)
 * @author Sara Biavaschi (748698, VA)
 * @version 2.0.0
 * @see it.uninsubria.emotionalsongs.config.ApiConfig
 * @see it.uninsubria.emotionalsongs.controller.Controller
 * @see it.uninsubria.emotionalsongs.model.utente.Utente
 * @see it.uninsubria.emotionalsongs.service.utente.UtenteService
 * @see it.uninsubria.emotionalsongs.service.SharedService
 * @see it.uninsubria.emotionalsongs.service.sessione.SessioneService
 * @see it.uninsubria.emotionalsongs.utils.Costanti
 * @see it.uninsubria.emotionalsongs.utils.Logger
 * @see it.uninsubria.emotionalsongs.utils.Utils
 */
public class UtenteController extends Controller implements ApiConfig {

    /**
     * Istanza del servizio relativo agli utenti.
     */
    private final UtenteService utenteService;

    /**
     * Istanza del servizio di sessione.
     */
    private final SessioneService sessioneService;

    /**
     * Costruttore della classe
     */
    public UtenteController() {
        utenteService = SharedService.getUtenteService();
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

        if (UtenteApi.GET_ALL_UTENTI.match(path, method)) {
            Logger.info(this.getClass().getSimpleName() + ": gestisciGetUtenti");
            gestisciGetUtenti(exchange);
        }
        else if (UtenteApi.REGISTRA_UTENTE.match(path, method)) {
            Logger.info(this.getClass().getSimpleName() + ": gestisciCreaUtente");
            gestisciCreaUtente(exchange);
        }
        else if (UtenteApi.GET_UTENTE_BY_ID.match(path, method)) {
            Logger.info(this.getClass().getSimpleName() + ": gestisciGetUtenteById");
            try {
                Map<String, String> pathVariables = Utils.getPathVariables(UtenteApi.GET_UTENTE_BY_ID.getPath(), path);
                Integer userId = Integer.valueOf(pathVariables.get("userId"));
                gestisciGetUtenteById(exchange, userId);
            }
            catch (Exception e) {
                sendResponse(exchange, Costanti.ErrorCode.ID_UTENTE, Costanti.ErrorCode.ID_UTENTE.getStatusCode());
            }
        }
        else sendResponse(exchange, Costanti.ErrorCode.PAGE_NOT_FOUND, Costanti.ErrorCode.PAGE_NOT_FOUND.getStatusCode());
    }

    /**
     * Gestisce la richiesta di visualizzazione di una lista con tutti gli utenti.
     * @param exchange Oggetto contenente la richiesta HTTP del client e usato per inviare la risposta
     */
    private void gestisciGetUtenti(HttpExchange exchange) {
        List<Utente> utenti = utenteService.getAll();
        sendResponse(exchange, utenti, Costanti.StatusCode.OK);
    }

    /**
     * Gestisce la richiesta di registrazione di un nuovo utente.
     * @param exchange Oggetto contenente la richiesta HTTP del client e usato per inviare la risposta
     */
    private void gestisciCreaUtente(HttpExchange exchange){
        try {
            Utente utente = getRequestBody(exchange, Utente.class);

            if(utenteService.createUtente(utente))
                sendResponse(exchange, utente, Costanti.StatusCode.CREATED);
            else
                sendResponse(exchange, Costanti.ErrorCode.NOT_FOUND, Costanti.ErrorCode.NOT_FOUND.getStatusCode());

        } catch (IOException e) {
            sendResponse(exchange, Costanti.ErrorCode.BAD_REQUEST, Costanti.ErrorCode.BAD_REQUEST.getStatusCode());
            throw new RuntimeException(e);
        }
    }

    /**
     * Gestisce la richiesta di ricerca dell'utente registrato con l'ID fornito.
     * @param exchange Oggetto contenente la richiesta HTTP del client e usato per inviare la risposta
     * @param userId L'ID dell'utente
     */
    private void gestisciGetUtenteById(HttpExchange exchange, Integer userId) {
        Utente utente = utenteService.getUtenteById(userId);
        sendResponse(exchange, utente, Costanti.StatusCode.OK);
    }

}