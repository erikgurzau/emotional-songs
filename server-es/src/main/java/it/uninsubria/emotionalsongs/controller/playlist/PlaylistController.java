package it.uninsubria.emotionalsongs.controller.playlist;

import com.sun.net.httpserver.HttpExchange;
import it.uninsubria.emotionalsongs.config.ApiConfig;
import it.uninsubria.emotionalsongs.controller.Controller;
import it.uninsubria.emotionalsongs.model.playlist.Playlist;
import it.uninsubria.emotionalsongs.service.SharedService;
import it.uninsubria.emotionalsongs.service.playlist.PlaylistService;
import it.uninsubria.emotionalsongs.utils.Costanti;
import it.uninsubria.emotionalsongs.utils.Logger;

import java.io.IOException;
import java.util.List;

public class PlaylistController extends Controller implements ApiConfig {

    private final PlaylistService playlistService;

    public PlaylistController() {
        playlistService = SharedService.getPlaylistService();
    }

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

    private void gestisciGetPlaylist(HttpExchange exchange) throws IOException {
        List<Playlist> playlists = playlistService.getAll();
        sendResponse(exchange, playlists, Costanti.StatusCode.OK);
    }

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
