package it.uninsubria.emotionalsongs.service.sessione;

import it.uninsubria.emotionalsongs.assembler.utente.UtenteAssembler;
import it.uninsubria.emotionalsongs.model.sessione.Sessione;
import it.uninsubria.emotionalsongs.model.utente.Utente;
import it.uninsubria.emotionalsongs.repository.utente.UtenteRepository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static it.uninsubria.emotionalsongs.utils.Utils.isNull;

/**
 * Questa classe fornisce dei servizi per la gestione delle sessioni.
 * @author Erik Gurzau (749400, VA)
 * @author Alessia Metaj (738945, VA)
 * @author Sara Biavaschi (748698, VA)
 * @version 2.0.0
 * @see it.uninsubria.emotionalsongs.assembler.utente.UtenteAssembler
 * @see it.uninsubria.emotionalsongs.model.sessione.Sessione
 * @see it.uninsubria.emotionalsongs.model.utente.Utente
 * @see it.uninsubria.emotionalsongs.repository.utente.UtenteRepository
 */
public class SessioneService {

    /**
     * Mappa che memorizza le sessioni attive degli utenti.
     */
    private static Map<String, Sessione> mappaSessioni = new ConcurrentHashMap<>(); //sessionId -> session

    /**
     * Repository degli utenti.
     */
    private UtenteRepository utenteRepository;

    /**
     * Assemblatore degli utenti.
     */
    private UtenteAssembler utenteAssembler;

    /**
     * Costruisce un nuovo servizio di gestione delle sessioni inizializzando il repository e l'assemblatore.
     */
    public SessioneService() {
        mappaSessioni = new ConcurrentHashMap<>();
        utenteRepository = new UtenteRepository();
        utenteAssembler = new UtenteAssembler();
    }

    /**
     * Crea una nuova sessione per l'utente specificato.
     * @param sessionId L'ID della sessione
     * @param email     L'email dell'utente
     * @param password  La password dell'utente
     * @return La sessione creata, se l'utente è autenticato correttamente; altrimenti, {@code null}
     */
    public Sessione creaSessione(String sessionId, String email, String password) {
        Optional<Sessione> optSessione = findSessioneAttiva(email);
        if (optSessione.isPresent())
            return optSessione.get();

        Optional<Utente> optUtente = login(email, password);
        if (optUtente.isPresent()) {
            Sessione sessione = isNull(sessionId)
                ? new Sessione(optUtente.get())
                : new Sessione(sessionId, optUtente.get());
            mappaSessioni.put(sessione.getSessionId(), sessione);
            return sessione;
        }
        else return null;
    }

    /**
     * Recupera la sessione corrispondente all'ID specificato.
     * @param sessionId L'ID della sessione da recuperare
     * @return La sessione corrispondente all'ID specificato, se presente; altrimenti, {@code null}
     */
    public Sessione getSessione(String sessionId) {
        return mappaSessioni.get(sessionId);
    }

    /**
     * Aggiorna la sessione specificata nella mappa delle sessioni.
     * @param session La sessione da aggiornare
     */
    public void aggiornaSessione(Sessione session) {
        mappaSessioni.put(session.getSessionId(), session);
    }

    /**
     * Cancella la sessione corrispondente all'ID specificato dalla mappa delle sessioni.
     * @param sessionId L'ID della sessione da cancellare
     */
    public void cancellaSessione(String sessionId) {
        mappaSessioni.remove(sessionId);
    }

    /**
     * Effettua il login dell'utente utilizzando l'email e la password specificate.
     * @param email    L'email dell'utente
     * @param password La password dell'utente
     * @return Un {@code Optional} contenente l'utente autenticato, se presente; altrimenti, un'istanza vuota di {@code Optional}
     */
    public Optional<Utente> login(String email, String password) {
        return utenteRepository.findByEmailAndPassword(email, password)
                .map(utenteAssembler::toModel);
    }

    /**
     * Trova una sessione attiva per l'utente corrispondente all'email specificata.
     * @param email L'email dell'utente
     * @return Un {@code Optional} contenente la prima sessione attiva trovata per l'utente, se presente;
     * altrimenti, un'istanza vuota di {@code Optional}
     */
    public Optional<Sessione> findSessioneAttiva(String email) {
        return mappaSessioni.values().stream()
                .filter(s -> s.getUtente().getEmail().equals(email) && !s.isScaduta())
                .findFirst();
    }

    /**
     * Verifica se una sessione con l'ID specificato è attiva.
     * @param sessionId L'ID della sessione da verificare
     * @return {@code true} se la sessione è attiva, {@code false} altrimenti
     */
    public boolean hasSessioneAttiva(String sessionId) {
        return mappaSessioni.containsKey(sessionId) &&
                !mappaSessioni.get(sessionId).isScaduta();
    }

}