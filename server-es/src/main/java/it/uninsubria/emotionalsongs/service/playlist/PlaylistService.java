package it.uninsubria.emotionalsongs.service.playlist;

import it.uninsubria.emotionalsongs.assembler.playlist.PlaylistAssembler;
import it.uninsubria.emotionalsongs.entity.playlist.PlaylistEntity;
import it.uninsubria.emotionalsongs.model.playlist.Playlist;
import it.uninsubria.emotionalsongs.repository.playlist.PlaylistRepository;
import it.uninsubria.emotionalsongs.utils.Logger;

import java.util.List;

public class PlaylistService {

    private final PlaylistRepository playlistRepository;
    private final PlaylistAssembler playlistAssembler;
    //private final CanzoneAssembler canzoneAssembler;

    public PlaylistService() {
        playlistRepository = new PlaylistRepository();
        playlistAssembler = new PlaylistAssembler();
        //canzoneAssembler = new CanzoneAssembler();
    }

    public List<Playlist> getAll() {
        Logger.info(this.getClass().getSimpleName() + ": getAll ");
        List<PlaylistEntity> playlistsList = playlistRepository.getAll();
        return playlistAssembler.toModel(playlistsList);
    }

    public boolean createPlaylist(Playlist playlist) {
        Logger.info(this.getClass().getSimpleName() + ": createPlaylist ");
        return playlistRepository.createPlaylist(playlist);
    }

    /*public List<Canzone> addCanzoni() {
        Logger.info(this.getClass().getSimpleName() + ": addCanzoni ");
        List<Canzone> canzoniList = playlistRepository.addCanzoni();
        return canzoneAssembler.toModel(canzoniList);
    }*/
}
