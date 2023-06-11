package it.uninsubria.emotionalsongs.model.sessione;

import it.uninsubria.emotionalsongs.model.utente.Utente;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;


public class Sessione implements Serializable {
    private String sessionId;
    private Utente utente;
    private Date dataCreazione;
    private Date dataUltimoAccesso;

    private static final long SESSION_TIMEOUT = 10 * 60 * 1000; // 10 minuti

    public Sessione(String sessionId, Utente utente) {
        this.sessionId = sessionId;
        this.utente = utente;
        this.dataCreazione = new Date();
        this.dataUltimoAccesso = new Date();
    }

    public Sessione(Utente utente) {
        this.sessionId = generaSessionId();
        this.utente = utente;
        this.dataCreazione = new Date();
        this.dataUltimoAccesso = new Date();
    }

    public String getSessionId() {
        return sessionId;
    }

    public Utente getUtente() {
        return utente;
    }

    public Date getDataCreazione() {
        return dataCreazione;
    }

    public Date getDataUltimoAccesso() {
        return dataUltimoAccesso;
    }

    public void aggiornaUltimoAccesso() {

        this.dataUltimoAccesso = new Date();
    }

    public boolean isScaduta() {
        long currentTime = System.currentTimeMillis();
        return (currentTime - dataUltimoAccesso.getTime()) > SESSION_TIMEOUT;
    }

    private String generaSessionId() {
        return UUID.randomUUID().toString();
    }



}
