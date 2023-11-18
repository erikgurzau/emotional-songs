package it.uninsubria.emotionalsongs.service.assegnazioni_canzoni;

import it.uninsubria.emotionalsongs.assembler.assegnazioni_canzoni.AssegnCanzAssembler;
import it.uninsubria.emotionalsongs.model.assegnazioni_canzoni.AssegnCanzone;
import it.uninsubria.emotionalsongs.repository.assegnazioni_canzoni.AssegnCanzRepository;
import it.uninsubria.emotionalsongs.utils.Logger;

import java.util.List;

public class AssegnCanzService {

    private final AssegnCanzRepository assegnCanzRepository;
    private final AssegnCanzAssembler assegnCanzAssembler;

    public AssegnCanzService() {
        assegnCanzRepository = new AssegnCanzRepository();
        assegnCanzAssembler = new AssegnCanzAssembler();
    }

    public List<AssegnCanzone> getAll() {
        Logger.info(this.getClass().getSimpleName() + ": getAll ");
        return assegnCanzAssembler.toModel(assegnCanzRepository.getAll());
    }

    public boolean insertAssegnazione(AssegnCanzone assegnazione) {
        Logger.info("AssegnCanzService: insertAssegnazione ");
        return assegnCanzRepository.insertAssegnazione(assegnazione);
    }
}
