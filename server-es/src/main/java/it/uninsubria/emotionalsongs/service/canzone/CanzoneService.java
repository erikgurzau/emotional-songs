package it.uninsubria.emotionalsongs.service.canzone;

import it.uninsubria.emotionalsongs.assembler.canzone.CanzoneAssembler;
import it.uninsubria.emotionalsongs.entity.canzone.CanzoneEntity;
import it.uninsubria.emotionalsongs.model.canzone.Canzone;
import it.uninsubria.emotionalsongs.repository.canzone.CanzoneRepository;

import java.util.List;
import java.util.Optional;

public class CanzoneService {

    private final CanzoneRepository canzoneRepository;
    private final CanzoneAssembler canzoneAssembler;

    public CanzoneService() {
        canzoneRepository = new CanzoneRepository();
        canzoneAssembler = new CanzoneAssembler();
    }

    public List<Canzone> getAll() {
        return canzoneAssembler.toModel(canzoneRepository.findAll());
    }
    public Canzone getCanzoneById(Integer id) {
        Optional<CanzoneEntity> optionalUtente = canzoneRepository.findById(id);
        return optionalUtente.isPresent() ? canzoneAssembler.toModel(optionalUtente.get()) : null;
    }
}
