package it.uninsubria.emotionalsongs.model.assegnazioni_canzoni;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Questa classe è responsabile della rappresentazione dell'assegnazione di una o più
 * canzoni ad una playlist creata da un utente.
 * Implementa l'interfaccia Serializable.
 * @author Erik Gurzau (749400, VA)
 * @author Alessia Metaj (738945, VA)
 * @author Sara Biavaschi (748698, VA)
 * @version 2.0.0
 */
public class AssegnCanzone implements Serializable {

    /**
     * L'ID dell'assegnazione.
     */
    @JsonProperty("id")
    private Integer id;

    /**
     * L'id della playlist a cui si riferisce l'assegnazione.
     */
    @JsonProperty("idPlaylist")
    private Integer idPlaylist;

    /**
     * L'id della/e canzone/i da assegnare alla playlist.
     */
    @JsonProperty("idCanzone")
    private List<Integer> idCanzone;

    /**
     * Costruttore di default della classe.
     */
    public AssegnCanzone() { }

    /**
     * Costruttore con parametri della classe.
     * @param id L'id dell'assegnazione
     * @param idPlaylist L'id della playlist a cui si riferisce l'assegnazione
     * @param idCanzone L'id della/e canzone/i da assegnare alla playlist.
     */
    public AssegnCanzone(Integer id, Integer idPlaylist, List<Integer> idCanzone) {
        this.id = id;
        this.idPlaylist = idPlaylist;
        this.idCanzone = idCanzone;
    }

    /**
     * Getter dell'ID dell'assegnazione.
     * @return L'intero che rappresenta l'ID dell'assegnazione
     */
    public Integer getId() {
        return id;
    }

    /**
     * Setter dell'ID dell'assegnazione.
     * @param id L'intero che rappresenta l'ID dell'assegnazione
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Getter dell'ID della playlist.
     * @return L'intero che rappresenta l'ID della playlist
     */
    public Integer getIdPlaylist() {
        return idPlaylist;
    }

    /**
     * Setter dell'ID della playlist.
     * @param idPlaylist L'intero che rappresenta l'ID della playlist
     */
    public void setIdPlaylist(Integer idPlaylist) {
        this.idPlaylist = idPlaylist;
    }

    /**
     * Getter degli ID delle canzoni da assegnare alla playlist.
     * @return La lista degli ID delle canzoni da assegnare alla playlist
     */
    public List<Integer> getIdCanzone() { return idCanzone; }

    /**
     * Setter degli ID delle canzoni da assegnare alla playlist.
     * @param idCanzone La lista di ID delle canzoni da assegnare alla playlist
     */
    public void setIdCanzone(List<Integer> idCanzone) { this.idCanzone = idCanzone; }

    /**
     * Restituisce una rappresentazione testuale dell'oggetto AssegnCanzoni.
     * @return Una stringa contenente l'ID, l'ID della playlist, l'ID delle canzoni.
     */
    public String toString() {
        return String.join(",",
                !Objects.isNull(getId()) ? getId().toString() : null,
                getIdPlaylist().toString(),
                getIdCanzone().toString()
        );
    }

}