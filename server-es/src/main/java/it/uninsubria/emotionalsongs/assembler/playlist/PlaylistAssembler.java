package it.uninsubria.emotionalsongs.assembler.playlist;

import it.uninsubria.emotionalsongs.assembler.AssemblerImpl;
import it.uninsubria.emotionalsongs.entity.playlist.PlaylistEntity;
import it.uninsubria.emotionalsongs.model.playlist.Playlist;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Questa classe è responsabile della conversione tra l'entità PlaylistEntity e il modello Playlist.
 * Implementa l'interfaccia AssemblerImpl per definire le operazioni di conversione.
 * @author Erik Gurzau (749400, VA)
 * @author Alessia Metaj (738945, VA)
 * @author Sara Biavaschi (748698, VA)
 * @version 1.0.0
 */
public class PlaylistAssembler implements AssemblerImpl<PlaylistEntity, Playlist> {

    /*
     * Costruttore della classe.
     */
    public PlaylistAssembler() { }

    /*
     * Converte un'istanza di PlaylistEntity in un'istanza di Playlist.
     */
    @Override
    public Playlist toModel(PlaylistEntity entity) {
        return new Playlist(
                entity.getId(),
                //entity.getIdUtenteRegistratoEntity(),
                entity.getIdUtente(),
                entity.getNome(),
                entity.getCanzoni()
        );
    }

    /*
     * Converte una lista di PlaylistEntity in una lista di Playlist
     * utilizzando il metodo toModel per ogni elemento della lista.
     */
    @Override
    public List<Playlist> toModel(List<PlaylistEntity> entityList) {
        return entityList.stream().map(this::toModel).collect(Collectors.toList());
    }


}