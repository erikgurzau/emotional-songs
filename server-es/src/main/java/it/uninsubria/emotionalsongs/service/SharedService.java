package it.uninsubria.emotionalsongs.service;

import it.uninsubria.emotionalsongs.service.assegnazioni_canzoni.AssegnCanzService;
import it.uninsubria.emotionalsongs.service.canzone.CanzoneService;
import it.uninsubria.emotionalsongs.service.emozione.EmozioneService;
import it.uninsubria.emotionalsongs.service.playlist.PlaylistService;
import it.uninsubria.emotionalsongs.service.report.ReportService;
import it.uninsubria.emotionalsongs.service.sessione.SessioneService;
import it.uninsubria.emotionalsongs.service.utente.UtenteService;

/**
 * Questa classe è un gestore dei servizi che fornisce accesso,ai servizi del server.
 * @author Erik Gurzau (749400, VA)
 * @author Alessia Metaj (738945, VA)
 * @author Sara Biavaschi (748698, VA)
 * @version 1.0.0
 * @see it.uninsubria.emotionalsongs.service.assegnazioni_canzoni.AssegnCanzService
 * @see it.uninsubria.emotionalsongs.service.canzone.CanzoneService
 * @see it.uninsubria.emotionalsongs.service.emozione.EmozioneService
 * @see it.uninsubria.emotionalsongs.service.playlist.PlaylistService
 * @see it.uninsubria.emotionalsongs.service.report.ReportService
 * @see it.uninsubria.emotionalsongs.service.sessione.SessioneService
 * @see it.uninsubria.emotionalsongs.service.utente.UtenteService
 */

public class SharedService {

    /**
     * Servizio per la gestione degli utenti.
     */
    private static UtenteService utenteService = new UtenteService();

    /**
     * Servizio per la gestione delle canzoni.
     */
    private static CanzoneService canzoneService = new CanzoneService();

    /**
     * Servizio per la gestione delle playlist.
     */
    private static PlaylistService playlistService = new PlaylistService();

    /**
     * Servizio per la gestione delle sessioni degli utenti.
     */
    private static SessioneService sessioneService = new SessioneService();

    /**
     * Servizio per la gestione delle assegnazioni delle canzoni.
     */
    private static AssegnCanzService assegnCanzService = new AssegnCanzService();

    /**
     * Servizio per la gestione dei report.
     */
    private static ReportService reportService = new ReportService();

    /**
     * Servizio per la gestione delle emozioni.
     */
    private static EmozioneService emozioneService = new EmozioneService();

    /**
     * Restituisce il servizio per la gestione degli utenti.
     * @return Il servizio per la gestione degli utenti.
     */
    public static UtenteService getUtenteService() {
        return utenteService;
    }

    /**
     * Restituisce il servizio per la gestione delle canzoni.
     * @return Il servizio per la gestione delle canzoni.
     */
    public static CanzoneService getCanzoneService() {
        return canzoneService;
    }

    /**
     * Restituisce il servizio per la gestione delle playlist.
     * @return Il servizio per la gestione delle playlist.
     */
    public static PlaylistService getPlaylistService() {
        return playlistService;
    }

    /**
     * Restituisce il servizio per la gestione delle assegnazioni delle canzoni.
     * @return Il servizio per la gestione delle assegnazioni delle canzoni.
     */
    public static AssegnCanzService getAssegnCanzService() {
        return assegnCanzService;
    }

    /**
     * Restituisce il servizio per la gestione delle emozioni.
     * @return Il servizio per la gestione delle emozioni.
     */
    public static EmozioneService getEmozioneService(){
        return emozioneService; 
    }

    /**
     * Restituisce il servizio per la gestione dei report.
     * @return Il servizio per la gestione dei report.
     */
    public static ReportService getReportService() { return reportService; }

    /**
     * Restituisce il servizio per la gestione delle sessioni degli utenti.
     * Questo metodo è sincronizzato per garantire la corretta gestione delle sessioni in contesti multi-thread.
     * @return Il servizio per la gestione delle sessioni degli utenti.
     */
    public static synchronized SessioneService getSessioneService() {
        return sessioneService;
    }

}
