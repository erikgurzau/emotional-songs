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
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static it.uninsubria.emotionalsongs.utils.Utils.isNull;

/**
 * Questa classe è responsabile della gestione delle richieste HTTP relative alle canzoni.
 * @author Erik Gurzau (749400, VA)
 * @author Alessia Metaj (738945, VA)
 * @author Sara Biavaschi (748698, VA)
 * @version 2.0.0
 * @see it.uninsubria.emotionalsongs.config.ApiConfig
 * @see it.uninsubria.emotionalsongs.controller.Controller
 * @see it.uninsubria.emotionalsongs.model.canzone.Canzone
 * @see it.uninsubria.emotionalsongs.model.pagina.Pagina
 * @see it.uninsubria.emotionalsongs.service.SharedService
 * @see it.uninsubria.emotionalsongs.service.canzone.CanzoneService
 * @see it.uninsubria.emotionalsongs.service.sessione.SessioneService
 * @see it.uninsubria.emotionalsongs.utils.Costanti
 * @see it.uninsubria.emotionalsongs.utils.Logger
 * @see it.uninsubria.emotionalsongs.utils.Utils
 */
public class CanzoneController extends Controller implements ApiConfig {

    /**
     * Istanza del servizio relativo alle canzoni.
     */
    private final CanzoneService canzoneService;

    /**
     * Istanza del servizio di sessione.
     */
    private final SessioneService sessioneService;

    /**
     * Costruttore della classe.
     */
    public CanzoneController() {
        canzoneService = SharedService.getCanzoneService();
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
        else if (CanzoneApi.GET_CANZONE_BY_TITOLO.match(path, method)) {
            Logger.info(this.getClass().getSimpleName() + ": gestisciGetCanzoneByTitolo");
            Map<String, String> pathVariables = Utils.getPathVariables(CanzoneApi.GET_CANZONE_BY_TITOLO.getPath(), path);
            String titolo = pathVariables.get("titolo");
            gestisciGetCanzoneByTitolo(exchange, titolo);
        }
        else if (CanzoneApi.GET_CANZONE_BY_AUTORE_E_ANNO.match(path, method)) {
            Logger.info(this.getClass().getSimpleName() + ": gestisciGetCanzoneByAutoreAnno");
            Map<String, String> pathVariables = Utils.getPathVariables(CanzoneApi.GET_CANZONE_BY_AUTORE_E_ANNO.getPath(), path);
            String autore = pathVariables.get("autore");
            Integer anno = Integer.valueOf(pathVariables.get("anno"));
            gestisciGetCanzoneByAutoreAnno(exchange, autore, anno);
        }
        else sendResponse(exchange, Costanti.ErrorCode.PAGE_NOT_FOUND, Costanti.ErrorCode.PAGE_NOT_FOUND.getStatusCode());
    }

    /**
     * Gestisce la richiesta di visualizzazione di una lista con tutte le canzoni.
     * @param exchange Oggetto contenente la richiesta HTTP del client e usato per inviare la risposta
     * @throws IOException se occorrono errori di tipo I/O
     */
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

    /**
     * Gestisce la richiesta di ricerca della canzone con l'ID fornito.
     * @param exchange Oggetto contenente la richiesta HTTP del client e usato per inviare la risposta
     * @param canzoneId L'ID della canzone da cercare
     */
    private void gestisciGetCanzoneById(HttpExchange exchange, Integer canzoneId) throws IOException {
        Canzone canzone = canzoneService.getCanzoneById(canzoneId);
        sendResponse(exchange, canzone, Costanti.StatusCode.OK);
    }

    /**
     * Gestisce la richiesta la ricerca della canzone corrispondente/contenente il titolo fornito.
     * @param exchange Oggetto contenente la richiesta HTTP del client e usato per inviare la risposta
     * @param ricerca Il titolo della canzone da cercare
     */
    private void gestisciGetCanzoneByTitolo(HttpExchange exchange, String ricerca) throws IOException {
        List<Canzone> canzoni = canzoneService.getCanzoneByTitolo(ricerca);
        sendResponse(exchange, canzoni, Costanti.StatusCode.OK);
    }

    /**
     * Gestisce la richiesta la ricerca della canzone con l'autore e l'anno di produzione forniti.
     * @param exchange Oggetto contenente la richiesta HTTP del client e usato per inviare la risposta
     * @param autore L'autore della canzone da cercare
     * @param anno L'anno della canzone da cercare
     */
    private void gestisciGetCanzoneByAutoreAnno(HttpExchange exchange, String autore, Integer anno) throws IOException {
        List<Canzone> canzoni = canzoneService.getCanzoneByAutoreAnno(autore, anno);
        sendResponse(exchange, canzoni, Costanti.StatusCode.OK);
    }

}
