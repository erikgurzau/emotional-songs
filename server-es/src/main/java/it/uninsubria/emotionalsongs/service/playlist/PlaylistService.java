package it.uninsubria.emotionalsongs.service.playlist;

import it.uninsubria.emotionalsongs.assembler.playlist.PlaylistAssembler;
import it.uninsubria.emotionalsongs.entity.playlist.PlaylistEntity;
import it.uninsubria.emotionalsongs.model.playlist.Playlist;
import it.uninsubria.emotionalsongs.repository.playlist.PlaylistRepository;
import it.uninsubria.emotionalsongs.utils.Logger;

import java.util.List;

/**
 * Questa classe fornisce dei servizi per la gestione delle playlist.
 * @author Erik Gurzau (749400, VA)
 * @author Alessia Metaj (738945, VA)
 * @author Sara Biavaschi (748698, VA)
 * @version 1.0.0
 */
public class PlaylistService {

    /**
     * Repository delle playlist.
     */
    private final PlaylistRepository playlistRepository;

    /**
     * Assemblatore delle playlist.
     */
    private final PlaylistAssembler playlistAssembler;

    /**
     * Costruisce un nuovo servizio di gestione delle playlist inizializzando il repository e l'assemblatore.
     */
    public PlaylistService() {
        playlistRepository = new PlaylistRepository();
        playlistAssembler = new PlaylistAssembler();
    }

    /**
     * Recupera tutte le playlist.
     * @return Una lista di tutte le playlist nel repository.
     */
    public List<Playlist> getAll() {
        Logger.info(this.getClass().getSimpleName() + ": getAll ");
        List<PlaylistEntity> playlistsList = playlistRepository.getAll();
        return playlistAssembler.toModel(playlistsList);
    }

    /**
     * Crea una nuova playlist.
     * @param playlist La playlist da creare.
     * @return true se la playlist è stata creata con successo, false altrimenti.
     */
    public boolean createPlaylist(Playlist playlist) {
        Logger.info(this.getClass().getSimpleName() + ": createPlaylist ");
        return playlistRepository.createPlaylist(playlist);
    }
}
