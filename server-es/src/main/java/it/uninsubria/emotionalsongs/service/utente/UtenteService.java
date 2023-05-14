package it.uninsubria.emotionalsongs.service.utente;

import it.uninsubria.emotionalsongs.assembler.utente.UtenteAssembler;
import it.uninsubria.emotionalsongs.entity.utente.UtenteRegistratoEntity;
import it.uninsubria.emotionalsongs.model.utente.Utente;
import it.uninsubria.emotionalsongs.repository.utente.UtenteRepository;

import java.util.List;
import java.util.Optional;

public class UtenteService {

    private final UtenteRepository utenteRepository;
    private final UtenteAssembler utenteAssembler;

    public UtenteService() {
        utenteRepository = new UtenteRepository();
        utenteAssembler = new UtenteAssembler();
    }

    public List<Utente> getAll() {
        return utenteAssembler.toModel(utenteRepository.findAll());
    }
    public Utente getUtenteById(Integer id) {
        Optional<UtenteRegistratoEntity> optionalUtente = utenteRepository.findById(id);
        return optionalUtente.isPresent() ? utenteAssembler.toModel(optionalUtente.get()) : null;
    }

    public boolean createUtente(Utente utente) {
        return utenteRepository.createUtente(utente);
    }
}
