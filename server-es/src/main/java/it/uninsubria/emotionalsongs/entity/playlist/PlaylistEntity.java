package it.uninsubria.emotionalsongs.entity.playlist;

import it.uninsubria.emotionalsongs.entity.utente.UtenteRegistratoEntity;
import it.uninsubria.emotionalsongs.model.canzone.Canzone;

import java.util.List;

/**
 * Questa classe è responsabile della rappresentazione dell'entità relativa ad una playlist.
 * @author Erik Gurzau (749400, VA)
 * @author Alessia Metaj (738945, VA)
 * @author Sara Biavaschi (748698, VA)
 * @version 2.0.0
 * @see it.uninsubria.emotionalsongs.model.canzone.Canzone
 */
public class PlaylistEntity {

    /**
     * L'ID della playlist.
     */
    private Integer id;

    /**
     * L'ID dell'utente creatore della playlist.
     */
    private Integer idUtente;
    //private UtenteRegistratoEntity utenteRegistratoEntity;

    /**
     * Il nome della playlist
     */
    private String nome;

    /**
     * La lista di canzoni
     */
    private List<Canzone> canzoni;

    /**
     * Costruttore vuoto della classe.
     */
    public PlaylistEntity() { }
    //public PlaylistEntity() { utenteRegistratoEntity = new UtenteRegistratoEntity();   }

    /**
     * Costruttore con parametri della classe.
     * @param id L'ID della playlist
     * @param idUtente L'ID dell'utente creatore della playlist
     * @param nome Il nome della playlist
     * @param canzoni Le canzoni della playlist
     */
    public PlaylistEntity(Integer id, Integer idUtente, String nome, List<Canzone> canzoni) {
        this.id = id;
        this.idUtente = idUtente;
        this.nome = nome;
        this.canzoni = canzoni;
    }
    /*public PlaylistEntity(Integer id, UtenteRegistratoEntity utenteRegistratoEntity, String nome) {
        this.id = id;
        this.utenteRegistratoEntity = utenteRegistratoEntity;
        this.nome = nome;
    }*/

    /**
     * Getter dell'ID della playlist.
     * @return L'intero che rappresenta l'ID associato alla playlist
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
     * Getter dell'ID dell'utente creatore della playlist.
     * @return L'intero che rappresenta l'ID dell'utente
     */
    public Integer getIdUtente() {
        return idUtente;
    }
    //public Integer getIdUtenteRegistratoEntity() { return utenteRegistratoEntity.getId(); }

    /**
     * Setter dell'ID dell'utente creatore della playlist.
     * @param idUtente L'intero che rappresenta l'ID dell'utente
     */
    public void setIdUtente(Integer idUtente) {
        this.idUtente = idUtente;
    }
    //public void setIdUtenteRegistratoEntity(Integer id) { utenteRegistratoEntity.setId(id); }

    /**
     * Getter del nome della playlist.
     * @return La stringa che rappresenta il nome della playlist
     */
    public String getNome() {
        return nome;
    }

    /**
     * Setter del nome della playlist.
     * @param nome La stringa che rappresenta il nome della playlist
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Getter della lista di canzoni della playlist.
     * @return La lista di canzoni
     */
    public List<Canzone> getCanzoni() { return canzoni; }

    /**
     * Setter delle canzoni della playlist.
     * @param canzoni La lista di canzoni
     */
    public void setCanzoni(List<Canzone> canzoni) { this.canzoni = canzoni; }

}