package it.uninsubria.emotionalsongs.entity.assegnazioni_canzoni;

import java.util.List;

/**
 * Questa classe è responsabile della rappresentazione dell'entità relativa all'assegnazione
 * di una o più canzoni ad una playlist.
 * @author Erik Gurzau (749400, VA)
 * @author Alessia Metaj (738945, VA)
 * @author Sara Biavaschi (748698, VA)
 * @version 2.0.0
 */
public class AssegnCanzEntity {

    /**
     * L'ID dell'assegnazione.
     */
    private Integer id;

    /**
     * L'ID della playlist a cui assegnare la/le canzone/i.
     */
    private Integer idPlaylist;

    /**
     * La lista di ID di canzoni da assegnare alla playlist.
     */
    private List<Integer> idCanzone;

    /**
     * Il costruttore vuoto della classe.
     */
    public AssegnCanzEntity() { }

    /**
     * Il costruttore con parametri della classe.
     * @param id L'ID dell'assegnazione
     * @param idPlaylist L'ID della playlist
     * @param idCanzone La lista di ID delle canzoni
     */
    public AssegnCanzEntity(Integer id, Integer idPlaylist, List<Integer> idCanzone) {
        this.id = id;
        this.idPlaylist = idPlaylist;
        this.idCanzone = idCanzone;
    }

    /**
     * Getter dell'ID dell'assegnazione.
     * @return L'intero che rappresenta l'ID associato all'assegnazione
     */
    public Integer getId() {
        return id;
    }

    /**
     * Setter dell'ID dell'assegnazione.
     * @param id L'ID da assegnare all'assegnazione
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Getter dell'ID della playlist a cui assegnare le canzoni.
     * @return L'intero che rappresenta l'ID associato alla playlist
     */
    public Integer getIdPlaylist() { return idPlaylist; }

    /**
     * Setter dell'ID della playlist.
     * @param idPlaylist L'ID della playlist a cui vengono assegnate le canzoni
     */
    public void setIdPlaylist(Integer idPlaylist) {
        this.idPlaylist = idPlaylist;
    }

    /**
     * Getter degli ID delle canzoni assegnate alla playlist.
     * @return La lista di interi che rappresenta gli ID delle canzoni assegnate alla playlist
     */
    public List<Integer> getIdCanzone() {
        return idCanzone;
    }

    /**
     * Setter degli ID delle canzoni da assegnare alla playlist.
     * @param idCanzone La lista degli ID delle canzoni da assegnare alla playlist
     */
    public void setIdCanzone(List<Integer> idCanzone) { this.idCanzone = idCanzone; }

}