package it.uninsubria.emotionalsongs.controller;

import com.sun.net.httpserver.HttpExchange;
import it.uninsubria.emotionalsongs.config.ApiConfig;
import it.uninsubria.emotionalsongs.controller.assegnazioni_canzoni.AssegnCanzController;
import it.uninsubria.emotionalsongs.controller.canzone.CanzoneController;
import it.uninsubria.emotionalsongs.controller.playlist.PlaylistController;
import it.uninsubria.emotionalsongs.controller.sessione.SessioneController;
import it.uninsubria.emotionalsongs.controller.utente.UtenteController;
import it.uninsubria.emotionalsongs.utils.Costanti;
import it.uninsubria.emotionalsongs.utils.Logger;

import java.io.IOException;

public class ServerController extends Controller implements ApiConfig {

    private final UtenteController utenteController;
    private final CanzoneController canzoneController;
    private final SessioneController sessioneController;
    private final PlaylistController playlistController;
    private final AssegnCanzController assegnCanzController;

    public ServerController() {
        utenteController = new UtenteController();
        canzoneController = new CanzoneController();
        sessioneController = new SessioneController();
        playlistController = new PlaylistController();
        assegnCanzController = new AssegnCanzController();
    }

    @Override
    public void handle(HttpExchange exchange) {
        try {
            redirectToController(exchange);
        }
        catch (IOException e) {
           e.printStackTrace();
        }
    }

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
            default -> sendResponse(exchange, Costanti.ErrorCode.PAGE_NOT_FOUND, 404);
        }
    }
}
