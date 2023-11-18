package it.uninsubria.emotionalsongs.model.sessione;

import it.uninsubria.emotionalsongs.model.utente.Utente;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * La classe Sessione rappresenta una sessione aperta da un utente.
 * Implementa l'interfaccia Serializable.
 * @author Erik Gurzau (749400, VA)
 * @author Alessia Metaj (738945, VA)
 * @author Sara Biavaschi (748698, VA)
 * @version 1.0.0
 */
public class Sessione implements Serializable {
    /**
     * L'ID della sessione.
     */
    private String sessionId;

    /**
     * L'utente che ha aperto la sessione.
     */
    private Utente utente;

    /**
     * La data di apertura della sessione.
     */
    private Date dataCreazione;

    /**
     * La data dell'ultimo accesso.
     */
    private Date dataUltimoAccesso;

    /**
     * La durata massima di una sessione.
     */
    private static final long SESSION_TIMEOUT = 10 * 60 * 1000; // 10 minuti

    /**
     * Costruttore con parametri.
     * @param sessionId l'ID della sessione
     * @param utente l'utente che ha aperto la sessione
     */
    public Sessione(String sessionId, Utente utente) {
        this.sessionId = sessionId;
        this.utente = utente;
        this.dataCreazione = new Date();
        this.dataUltimoAccesso = new Date();
    }

    /**
     * Costruttore con parametri.
     * @param utente l'utente che ha aperto la sessione
     */
    public Sessione(Utente utente) {
        this.sessionId = generaSessionId();
        this.utente = utente;
        this.dataCreazione = new Date();
        this.dataUltimoAccesso = new Date();
    }

    /**
     * Restituisce l'ID della sessione.
     * @return l'ID della sessione
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * Restituisce l'utente che ha aperto la sessione.
     * @return l'utente che ha aperto la sessione
     */
    public Utente getUtente() {
        return utente;
    }

    /**
     * Restituisce la data della creazioone della sessione.
     * @return la data della creazioone della sessione
     */
    public Date getDataCreazione() {
        return dataCreazione;
    }

    /**
     * Restituisce la data dell'ultimo accesso.
     * @return la data dell'ultimo accesso
     */
    public Date getDataUltimoAccesso() {
        return dataUltimoAccesso;
    }

    /**
     * Aggiorna la data dell'ultimo accesso.
     */
    public void aggiornaUltimoAccesso() {

        this.dataUltimoAccesso = new Date();
    }

    /**
     * Restituisce l'indicazione del fatto che la sessione in corso sia scaduta o meno.
     * @return un booleano settato a true se la sessione è scaduta
     */
    public boolean isScaduta() {
        long currentTime = System.currentTimeMillis();
        return (currentTime - dataUltimoAccesso.getTime()) > SESSION_TIMEOUT;
    }

    /**
     * Restituisce un ID randomico per la generazione di una nuova sessione.
     * @return una stringa rappresentante l'UUID creato.
     */
    private String generaSessionId() {
        return UUID.randomUUID().toString();
    }



}
