package it.uninsubria.emotionalsongs.service.canzone;

import it.uninsubria.emotionalsongs.assembler.canzone.CanzoneAssembler;
import it.uninsubria.emotionalsongs.entity.canzone.CanzoneEntity;
import it.uninsubria.emotionalsongs.model.canzone.Canzone;
import it.uninsubria.emotionalsongs.model.pagina.Pagina;
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

    public List<Canzone> getAll(Pagina<Canzone> pagina) {
        List<CanzoneEntity> canzoniList = canzoneRepository.getAll(pagina.getNumeroPagina(), pagina.getDimensionePagina());
        return canzoneAssembler.toModel(canzoniList);
    }
    public Canzone getCanzoneById(Integer id) {
        Optional<CanzoneEntity> optionalUtente = canzoneRepository.findById(id);
        return optionalUtente.isPresent() ? canzoneAssembler.toModel(optionalUtente.get()) : null;
    }

    public Integer getTotaleCanzoni() {
        return canzoneRepository.getTotaleCanzoni();
    }
}
