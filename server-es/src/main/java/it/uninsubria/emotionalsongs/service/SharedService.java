package it.uninsubria.emotionalsongs.service;

import it.uninsubria.emotionalsongs.service.canzone.CanzoneService;
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
    private static SessioneService sessioneService = new SessioneService();

    public static UtenteService getUtenteService() {
        return utenteService;
    }
    public static CanzoneService getCanzoneService() {
        return canzoneService;
    }
    public static synchronized SessioneService getSessioneService() {
        return sessioneService;
    }

}
