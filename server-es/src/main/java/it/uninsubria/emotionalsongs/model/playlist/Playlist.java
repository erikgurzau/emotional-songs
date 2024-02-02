package it.uninsubria.emotionalsongs.model.playlist;

import com.fasterxml.jackson.annotation.JsonProperty;
import it.uninsubria.emotionalsongs.model.canzone.Canzone;

import java.io.Serializable;
import java.util.List;

/**
 * Questa classe è responsabile della rappresentazione di una playlist creata da un utente del sistema.
 * Implementa l'interfaccia Serializable.
 * @author Erik Gurzau (749400, VA)
 * @author Alessia Metaj (738945, VA)
 * @author Sara Biavaschi (748698, VA)
 * @version 2.0.0
 * @see it.uninsubria.emotionalsongs.model.canzone.Canzone
 */
public class Playlist implements Serializable {

    /**
     * L'ID della playlist.
     */
    @JsonProperty("id")
    private Integer id;

    /**
     * LID dell'utente che ha creato la playlist.
     */
    @JsonProperty("utente")
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
    private List<Canzone> canzoni;

    /**
     * Costruttore di default della classe.
     */
    public Playlist() { }

    /**
     * Costruttore con parametri della classe.
     * @param id L'ID della playlist
     * @param idUtente L'ID dell'utente creatore della playlist
     * @param nome Il nome della playlist
     */
    public Playlist(Integer id, Integer idUtente, String nome, List<Canzone> canzoni) {
        this.id = id;
        this.idUtente = idUtente;
        this.nome = nome;
        this.canzoni = canzoni;
    }

    /**
     * Getter dell'ID della playlist.
     * @return L'intero che rappresenta l'ID della playlist
     */
    public Integer getId() {
        return id;
    }

    /**
     * Setter dell'ID della playlist.
     * @param id L'intero che rappresenta l'ID della playlist
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Getter dell'ID dell'utente creatore playlist.
     * @return L'intero che rappresenta l'ID dell'utente creatore della playlist
     */
    public Integer getUtente() {
        return idUtente;
    }

    /**
     * Setter l'ID dell'utente creatore della playlist.
     * @param idUtente L'intero che rappresenta l'ID dell'utente creatore della playlist
     */
    public void setUtente(Integer idUtente) {
        this.idUtente = idUtente;
    }

    /**
     * Getter del nome della playlist.
     * @return La stringa che rappresenta  il nome della playlist
     */
    public String getNome() {
        return nome;
    }

    /**
     * Setter il nome della playlist.
     * @param nome La stringa che rappresenta il nome della playlist
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Getter della lista di canzoni assegnate alla playlist.
     * @return La lista di canzoni assegnate alla playlist
     */
    public List<Canzone> getCanzoni() { return canzoni; }

    /**
     * Setter della lista di canzoni da assegnare alla playlist.
     * @param canzoni La lista delle canzoni da assegnare alla playlist
     */
    public void setCanzoni(List<Canzone> canzoni) { this.canzoni = canzoni; }

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