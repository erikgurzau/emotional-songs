package it.uninsubria.emotionalsongs.service.utente;

import it.uninsubria.emotionalsongs.assembler.utente.UtenteAssembler;
import it.uninsubria.emotionalsongs.entity.utente.UtenteRegistratoEntity;
import it.uninsubria.emotionalsongs.model.utente.Utente;
import it.uninsubria.emotionalsongs.repository.utente.UtenteRepository;
import it.uninsubria.emotionalsongs.utils.Logger;

import java.util.List;
import java.util.Optional;

/**
 * Questa classe fornisce dei servizi per la gestione degli utenti.
 * @author Erik Gurzau (749400, VA)
 * @author Alessia Metaj (738945, VA)
 * @author Sara Biavaschi (748698, VA)
 * @version 2.0.0
 */
public class UtenteService {

    private final UtenteRepository utenteRepository;
    private final UtenteAssembler utenteAssembler;

    /**
     * Costruisce un nuovo servizio utente inizializzando il repository e l'assemblatore degli utenti.
     */
    public UtenteService() {
        utenteRepository = new UtenteRepository();
        utenteAssembler = new UtenteAssembler();
    }

    /**
     * Fornisce una lista di tutti gli utenti.
     * @return una lista di tutti gli utenti.
     */
    public List<Utente> getAll() {
        Logger.info(this.getClass().getSimpleName() + ": getAll ");
        return utenteAssembler.toModel(utenteRepository.findAll());
    }

    /**
     * Trova un utente grazie al suo ID.
     * @param id L'ID dell'utente da recuperare.
     * @return L'utente corrispondente all'ID specificato, se presente; altrimenti, null.
     */
    public Utente getUtenteById(Integer id) {
        Logger.info(this.getClass().getSimpleName() + ": getUtenteById " + id);
        Optional<UtenteRegistratoEntity> optionalUtente = utenteRepository.findById(id);
        return optionalUtente.isPresent() ? utenteAssembler.toModel(optionalUtente.get()) : null;
    }

    /**
     * Crea un nuovo utente.
     * @param utente L'utente da creare.
     * @return true se l'utente è stato creato con successo, false altrimenti.
     */
    public boolean createUtente(Utente utente) {
        return utenteRepository.createUtente(utente);
    }


    /**
     * Trova un utente grazie a email e password associate.
     * @param email    L'email dell'utente.
     * @param password La password dell'utente.
     * @return L'utente corrispondente all'email e alla password specificate, se presente; altrimenti, null.
     */
    public Utente getUtenteByEmailAndPassword(String email, String password) {
        Logger.info(this.getClass().getSimpleName() + ": getUtenteByEmailAndPassword ", email, password);
        Optional<UtenteRegistratoEntity> optionalUtente = utenteRepository.findByEmailAndPassword(email, password);
        return optionalUtente.isPresent() ? utenteAssembler.toModel(optionalUtente.get()) : null;
    }
}
