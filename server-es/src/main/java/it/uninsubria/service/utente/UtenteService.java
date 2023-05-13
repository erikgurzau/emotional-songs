package it.uninsubria.service.utente;

import it.uninsubria.assembler.utente.UtenteAssembler;
import it.uninsubria.entity.utente.UtenteRegistratoEntity;
import it.uninsubria.model.utente.Utente;
import it.uninsubria.repository.utente.UtenteRepository;

import java.util.List;
import java.util.Optional;

public class UtenteService {

    private final UtenteRepository utenteRepository;
    private final UtenteAssembler utenteAssembler;

    public UtenteService() {
        utenteRepository = new UtenteRepository();
        utenteAssembler = new UtenteAssembler();
    }

    public List<Utente> findAll() {
        return utenteAssembler.toModel(utenteRepository.findAll());
    }
    public Utente getUtenteById(Integer id) {
        Optional<UtenteRegistratoEntity> optionalUtente = utenteRepository.findById(id);
        return optionalUtente.isPresent() ? utenteAssembler.toModel(optionalUtente.get()) : null;
    }
}
