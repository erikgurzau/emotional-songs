package it.uninsubria.service.canzone.utente;

import it.uninsubria.assembler.canzone.CanzoneAssembler;
import it.uninsubria.assembler.utente.UtenteAssembler;
import it.uninsubria.entity.canzone.CanzoneEntity;
import it.uninsubria.entity.utente.UtenteRegistratoEntity;
import it.uninsubria.model.canzone.Canzone;
import it.uninsubria.model.utente.Utente;
import it.uninsubria.repository.canzone.CanzoneRepository;
import it.uninsubria.repository.utente.UtenteRepository;

import java.util.List;
import java.util.Optional;

public class CanzoneService {

    private final CanzoneRepository canzoneRepository;
    private final CanzoneAssembler canzoneAssembler;

    public CanzoneService() {
        canzoneRepository = new CanzoneRepository();
        canzoneAssembler = new CanzoneAssembler();
    }

    public List<Canzone> findAll() {
        return canzoneAssembler.toModel(canzoneRepository.findAll());
    }
    public Canzone getCanzoneById(Integer id) {
        Optional<CanzoneEntity> optionalUtente = canzoneRepository.findById(id);
        return optionalUtente.isPresent() ? canzoneAssembler.toModel(optionalUtente.get()) : null;
    }
}
