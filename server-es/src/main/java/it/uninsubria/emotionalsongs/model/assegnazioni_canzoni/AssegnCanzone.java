package it.uninsubria.emotionalsongs.model.assegnazioni_canzoni;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * La classe AssegnCanzoni rappresenta l'assegnazione di una o più
 * canzoni ad una playlist creata da un utente.
 * Implementa l'interfaccia Serializable.
 * @author Erik Gurzau (749400, VA)
 * @author Alessia Metaj (738945, VA)
 * @author Sara Biavaschi (748698, VA)
 * @version 1.0.0
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
     * Costruttore di default.
     */
    public AssegnCanzone() { }

    /**
     * Costruttore con parametri.
     * @param id l'id dell'assegnazione
     * @param idPlaylist l'id della playlist a cui si riferisce l'assegnazione
     * @param idCanzone l'id della/e canzone/i da assegnare alla playlist.
     */
    public AssegnCanzone(Integer id, Integer idPlaylist, List<Integer> idCanzone) {
        this.id = id;
        this.idPlaylist = idPlaylist;
        this.idCanzone = idCanzone;
    }

    /**
     * Restituisce l'ID dell'assegnazione.
     * @return l'ID dell'assegnazione
     */
    public Integer getId() {
        return id;
    }

    /**
     * Imposta l'ID dell'assegnazione.
     * @param id l'ID dell'assegnazione
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Restituisce l'id della playlist.
     * @return l'id della playlist
     */
    public Integer getIdPlaylist() {
        return idPlaylist;
    }

    /**
     * Imposta l'id della playlist.
     * @param idPlaylist l'id della playlist
     */
    public void setIdPlaylist(Integer idPlaylist) {
        this.idPlaylist = idPlaylist;
    }

    /**
     * Restituisce l'id della/e canzone/i da assegnare alla playlist.
     * @return l'id della/e canzone/i da assegnare alla playlist
     */
    public List<Integer> getIdCanzone() { return idCanzone; }

    /**
     * Imposta l'id della/e canzone/i da assegnare alla playlist.
     * @param idCanzone l'id della/e canzone/i da assegnare alla playlist
     */
    public void setIdCanzone(List<Integer> idCanzone) { this.idCanzone = idCanzone; }

    /**
     * Restituisce una rappresentazione testuale dell'oggetto AssegnCanzoni.
     * @return una stringa contenente l'ID, l'ID della playlist, l'ID della/e canzone/i.
     */
    public String toString() {
        return String.join(",",
                !Objects.isNull(getId()) ? getId().toString() : null,
                getIdPlaylist().toString(),
                getIdCanzone().toString()
        );
    }
}
