package it.uninsubria.emotionalsongs.controller;

import com.sun.net.httpserver.HttpExchange;
import it.uninsubria.emotionalsongs.config.ApiConfig;
import it.uninsubria.emotionalsongs.controller.assegnazioni_canzoni.AssegnCanzController;
import it.uninsubria.emotionalsongs.controller.canzone.CanzoneController;
import it.uninsubria.emotionalsongs.controller.emozione.EmozioneController;
import it.uninsubria.emotionalsongs.controller.playlist.PlaylistController;
import it.uninsubria.emotionalsongs.controller.report.ReportController;
import it.uninsubria.emotionalsongs.controller.sessione.SessioneController;
import it.uninsubria.emotionalsongs.controller.utente.UtenteController;
import it.uninsubria.emotionalsongs.utils.Costanti;
import it.uninsubria.emotionalsongs.utils.Logger;

import java.io.IOException;

/**
 * Questa classe è responsabile della gestione delle richieste HTTP in arrivo sul server,
 * instradandole al controller specifico.
 * @author Erik Gurzau (749400, VA)
 * @author Alessia Metaj (738945, VA)
 * @author Sara Biavaschi (748698, VA)
 * @version 2.0.0
 * @see it.uninsubria.emotionalsongs.config.ApiConfig
 * @see it.uninsubria.emotionalsongs.controller.assegnazioni_canzoni.AssegnCanzController
 * @see it.uninsubria.emotionalsongs.controller.canzone.CanzoneController
 * @see it.uninsubria.emotionalsongs.controller.emozione.EmozioneController
 * @see it.uninsubria.emotionalsongs.controller.playlist.PlaylistController
 * @see it.uninsubria.emotionalsongs.controller.report.ReportController
 * @see it.uninsubria.emotionalsongs.controller.sessione.SessioneController
 * @see it.uninsubria.emotionalsongs.controller.utente.UtenteController
 * @see it.uninsubria.emotionalsongs.utils.Costanti
 * @see it.uninsubria.emotionalsongs.utils.Logger
 */
public class ServerController extends Controller implements ApiConfig {

    /**
     * Controller responsabile delle richieste relative agli utenti.
     */
    private final UtenteController utenteController;

    /**
     * Controller responsabile delle richieste relative alle canzoni.
     */
    private final CanzoneController canzoneController;

    /**
     * Controller responsabile delle richieste relative alle sessioni.
     */
    private final SessioneController sessioneController;

    /**
     * Controller responsabile delle richieste relative alle playlist.
     */
    private final PlaylistController playlistController;

    /**
     * Controller responsabile delle richieste relative alle assegnazioni di canzoni alle playlist.
     */
    private final AssegnCanzController assegnCanzController;

    /**
     * Controller responsabile delle richieste relative ai report emozionali.
     */
    private final ReportController reportController;

    /**
     * Controller responsabile delle richieste relative alle recensioni emozionali delle canzoni.
     */
    private final EmozioneController emozioneController;

    /**
     * Costruttore della classe.
     */
    public ServerController() {
        utenteController = new UtenteController();
        canzoneController = new CanzoneController();
        sessioneController = new SessioneController();
        playlistController = new PlaylistController();
        assegnCanzController = new AssegnCanzController();
        reportController = new ReportController();
        emozioneController = new EmozioneController();
    }

    /**
     * Gestisce le richieste HTTP in arrivo dal client.
     * @param exchange Oggetto contenente la richiesta HTTP del client e usato per inviare la risposta
     */
    @Override
    public void handle(HttpExchange exchange) {
        try {
            redirectToController(exchange);
        }
        catch (IOException e) {
           e.printStackTrace();
        }
    }

    /**
     * Dirige le richieste HTTP verso i controller specifici.
     * @param exchange Oggetto contenente la richiesta HTTP del client e usato per inviare la risposta
     * @throws IOException se occorrono errori di tipo I/O
     */
    public void redirectToController(HttpExchange exchange) throws IOException {
        Logger.info(this.getClass().getSimpleName() + ": redirecting to controller");
        String pathURI = exchange.getRequestURI().toString();
        String pathController = "/" + pathURI.split("/")[2];
        switch (pathController) {
            case PATH_UTENTE_API -> utenteController.handle(exchange);
            case PATH_CANZONE_API -> canzoneController.handle(exchange);
            case PATH_SESSIONE_API -> sessioneController.handle(exchange);
            case PATH_PLAYLIST_API -> playlistController.handle(exchange);
            case PATH_ASSEGNAZIONE_API -> assegnCanzController.handle(exchange);
            case PATH_REPORT_API -> reportController.handle(exchange);
            case PATH_EMOZIONE_API -> emozioneController.handle(exchange);
            default -> sendResponse(exchange, Costanti.ErrorCode.PAGE_NOT_FOUND, 404);
        }
    }

}