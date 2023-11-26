package it.uninsubria.emotionalsongs.model.playlist;

import com.fasterxml.jackson.annotation.JsonProperty;
import it.uninsubria.emotionalsongs.entity.canzone.CanzoneEntity;

import java.io.Serializable;
import java.util.List;

/**
 * La classe Playlist rappresenta una playlist creata da un utente del sistema.
 * Implementa l'interfaccia Serializable.
 * @author Erik Gurzau (749400, VA)
 * @author Alessia Metaj (738945, VA)
 * @author Sara Biavaschi (748698, VA)
 * @version 1.0.0
 */
public class Playlist implements Serializable {

    /**
     * L'ID della playlist.
     */
    //@JsonProperty("id")
    private Integer id;

    /**
     * LID dell'utente che ha creato la playlist.
     */
    //@JsonProperty("idUtente")
    private Integer idUtente;

    /**
     * Il nome della playlist.
     */
    @JsonProperty("nome")
    private String nome;

    /**
     * Le canzoni assegnate alla playlist.
     */
    @JsonProperty("canzoni")
    private List<CanzoneEntity> canzoni;

    /**
     * Costruttore di default.
     */
    public Playlist() { }

    /**
     * Costruttore con parametri.
     * @param id l'ID della playlist
     * @param idUtente l'ID dell'utente creatore della playlist
     * @param nome il nome della playlist
     */
    public Playlist(Integer id, Integer idUtente, String nome, List<CanzoneEntity> canzoni) {
        this.id = id;
        this.idUtente = idUtente;
        this.nome = nome;
        this.canzoni = canzoni;
    }

    /**
     * Restituisce l'ID della playlist.
     * @return l'ID della playlist
     */
    public Integer getId() {
        return id;
    }

    /**
     * Imposta l'ID della playlist.
     * @param id l'ID della playlist
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Restituisce l'ID dell'utente creatore playlist.
     * @return l'ID dell'utente creatore della playlist
     */
    public Integer getUtente() {
        return idUtente;
    }

    /**
     * Imposta l'ID dell'utente creatore della playlist.
     * @param idUtente l'ID dell'utente creatore della playlist
     */
    public void setUtente(Integer idUtente) {
        this.idUtente = idUtente;
    }

    /**
     * Restituisce il nome della playlist.
     * @return il nome della playlist
     */
    public String getNome() {
        return nome;
    }

    /**
     * Imposta il nome della playlist.
     * @param nome il nome della playlist
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Restituisce la lista di canzoni assegnate alla playlist.
     * @return la lista di canzoni assegnate della playlist
     */
    public List<CanzoneEntity> getCanzoni() { return canzoni; }

    /**
     * Imposta la lista di canzoni da assegnare alla playlist.
     * @param canzoni la lista delle canzoni da assegnare alla playlist
     */
    public void setCanzoni(List<CanzoneEntity> canzoni) { this.canzoni = canzoni; }

    /**
     * Restituisce una rappresentazione testuale dell'oggetto Playlist.
     * @return una stringa contenente l'ID della playlist, l'ID dell'utente
     * creatore della playlist e il nome della playlist.
     */
    public String toString() {
        return String.join(",",
                getId().toString(), getUtente().toString(), getNome(), getCanzoni().toString()
        );
    }
}
