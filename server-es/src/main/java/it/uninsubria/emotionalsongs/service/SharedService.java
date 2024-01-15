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
 * Implementa un pattern Singleton garantendo che venga creata una sola istanza del servizio per ogni tipo.
 * @author Erik Gurzau (749400, VA)
 * @author Alessia Metaj (738945, VA)
 * @author Sara Biavaschi (748698, VA)
 * @version 1.0.0
 */
public class SharedService {
    private static UtenteService utenteService = new UtenteService();
    private static CanzoneService canzoneService = new CanzoneService();
    private static PlaylistService playlistService = new PlaylistService();
    private static SessioneService sessioneService = new SessioneService();
    private static AssegnCanzService assegnCanzService = new AssegnCanzService();
    private static ReportService reportService = new ReportService();
    private static EmozioneService emozioneService = new EmozioneService();


    public static UtenteService getUtenteService() {
        return utenteService;
    }
    public static CanzoneService getCanzoneService() {
        return canzoneService;
    }
    public static PlaylistService getPlaylistService() {
        return playlistService;
    }
    public static AssegnCanzService getAssegnCanzService() {
        return assegnCanzService;
    }
    public static EmozioneService getEmozioneService(){
        return emozioneService; 
    }
    public static ReportService getReportService() { return reportService; }
    public static synchronized SessioneService getSessioneService() {
        return sessioneService;
    }

}
