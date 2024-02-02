package it.uninsubria.emotionalsongs.controller.playlist;

import com.sun.net.httpserver.HttpExchange;
import it.uninsubria.emotionalsongs.config.ApiConfig;
import it.uninsubria.emotionalsongs.controller.Controller;
import it.uninsubria.emotionalsongs.model.playlist.Playlist;
import it.uninsubria.emotionalsongs.service.SharedService;
import it.uninsubria.emotionalsongs.service.playlist.PlaylistService;
import it.uninsubria.emotionalsongs.service.sessione.SessioneService;
import it.uninsubria.emotionalsongs.utils.Costanti;
import it.uninsubria.emotionalsongs.utils.Logger;

import java.io.IOException;
import java.util.List;

/**
 * Questa classe è responsabile della gestione delle richieste HTTP relative alle playlist.
 * @author Erik Gurzau (749400, VA)
 * @author Alessia Metaj (738945, VA)
 * @author Sara Biavaschi (748698, VA)
 * @version 2.0.0
 * @see it.uninsubria.emotionalsongs.config.ApiConfig
 * @see it.uninsubria.emotionalsongs.controller.Controller
 * @see it.uninsubria.emotionalsongs.model.playlist.Playlist
 * @see it.uninsubria.emotionalsongs.service.SharedService
 * @see it.uninsubria.emotionalsongs.service.playlist.PlaylistService
 * @see it.uninsubria.emotionalsongs.service.sessione.SessioneService
 * @see it.uninsubria.emotionalsongs.utils.Costanti
 * @see it.uninsubria.emotionalsongs.utils.Logger
 */
public class PlaylistController extends Controller implements ApiConfig {

    /**
     * Istanza del servizio relativo alle playlist.
     */
    private final PlaylistService playlistService;

    /**
     * Istanza del servizio di sessione.
     */
    private final SessioneService sessioneService;

    /**
     * Costruttore della classe.
     */
    public PlaylistController() {
        playlistService = SharedService.getPlaylistService();
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
        if (PlaylistApi.GET_ALL_PLAYLIST.match(path, method)) {
            Logger.info(this.getClass().getSimpleName() + ": gestisciGetPlaylist");
            gestisciGetPlaylist(exchange);
        }
        else if (PlaylistApi.CREA_PLAYLIST.match(path, method)) {
            Logger.info(this.getClass().getSimpleName() + ": gestisciCreaPlaylist");
            gestisciCreaPlaylist(exchange);
        }
        else sendResponse(exchange, Costanti.ErrorCode.PAGE_NOT_FOUND, Costanti.ErrorCode.PAGE_NOT_FOUND.getStatusCode());
    }

    /**
     * Gestisce la richiesta di visualizzazione di una lista con tutte le playlist.
     * @param exchange Oggetto contenente la richiesta HTTP del client e usato per inviare la risposta
     */
    private void gestisciGetPlaylist(HttpExchange exchange) throws IOException {
        List<Playlist> playlists = playlistService.getAll();
        sendResponse(exchange, playlists, Costanti.StatusCode.OK);
    }

    /**
     * Gestisce la richiesta di inserimento di una nuova playlist.
     * @param exchange Oggetto contenente la richiesta HTTP del client e usato per inviare la risposta
     */
    private void gestisciCreaPlaylist(HttpExchange exchange) throws IOException {
        try {
            Playlist playlist = getRequestBody(exchange, Playlist.class);

            if(playlistService.createPlaylist(playlist))
                sendResponse(exchange, playlist, Costanti.StatusCode.CREATED);
            else
                sendResponse(exchange, Costanti.ErrorCode.NOT_FOUND, Costanti.ErrorCode.NOT_FOUND.getStatusCode());

        } catch (IOException e) {
            sendResponse(exchange, Costanti.ErrorCode.BAD_REQUEST, Costanti.ErrorCode.BAD_REQUEST.getStatusCode());
            throw new RuntimeException(e);
        }
    }

}