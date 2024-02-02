package it.uninsubria.emotionalsongs.service.utente;

import it.uninsubria.emotionalsongs.assembler.utente.UtenteAssembler;
import it.uninsubria.emotionalsongs.entity.utente.UtenteRegistratoEntity;
import it.uninsubria.emotionalsongs.model.utente.Utente;
import it.uninsubria.emotionalsongs.repository.utente.UtenteRepository;
import it.uninsubria.emotionalsongs.utils.Logger;

import java.util.List;
import java.util.Optional;

/**
 * Questa classe contiene le costanti utilizzate nell'applicazione.
 * @author Erik Gurzau (749400, VA)
 * @author Alessia Metaj (738945, VA)
 * @author Sara Biavaschi (748698, VA)
 * @version 2.0.0
 */
public class UtenteService {

    private final UtenteRepository utenteRepository;
    private final UtenteAssembler utenteAssembler;

    public UtenteService() {
        utenteRepository = new UtenteRepository();
        utenteAssembler = new UtenteAssembler();
    }

    public List<Utente> getAll() {
        Logger.info(this.getClass().getSimpleName() + ": getAll ");
        return utenteAssembler.toModel(utenteRepository.findAll());
    }
    public Utente getUtenteById(Integer id) {
        Logger.info(this.getClass().getSimpleName() + ": getUtenteById " + id);
        Optional<UtenteRegistratoEntity> optionalUtente = utenteRepository.findById(id);
        return optionalUtente.isPresent() ? utenteAssembler.toModel(optionalUtente.get()) : null;
    }

    public boolean createUtente(Utente utente) {
        return utenteRepository.createUtente(utente);
    }

    public Utente getUtenteByEmailAndPassword(String email, String password) {
        Logger.info(this.getClass().getSimpleName() + ": getUtenteByEmailAndPassword ", email, password);
        Optional<UtenteRegistratoEntity> optionalUtente = utenteRepository.findByEmailAndPassword(email, password);
        return optionalUtente.isPresent() ? utenteAssembler.toModel(optionalUtente.get()) : null;
    }
}
