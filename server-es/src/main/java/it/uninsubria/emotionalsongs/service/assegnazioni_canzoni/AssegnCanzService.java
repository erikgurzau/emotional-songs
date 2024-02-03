package it.uninsubria.emotionalsongs.service.assegnazioni_canzoni;

import it.uninsubria.emotionalsongs.assembler.assegnazioni_canzoni.AssegnCanzAssembler;
import it.uninsubria.emotionalsongs.model.assegnazioni_canzoni.AssegnCanzone;
import it.uninsubria.emotionalsongs.repository.assegnazioni_canzoni.AssegnCanzRepository;
import it.uninsubria.emotionalsongs.utils.Logger;

import java.util.List;

/**
 * Questa classe fornisce dei servizi per la gestione delle assegnazioni delle canzoni.
 * @author Erik Gurzau (749400, VA)
 * @author Alessia Metaj (738945, VA)
 * @author Sara Biavaschi (748698, VA)
 * @version 1.0.0
 * @see it.uninsubria.emotionalsongs.assembler.assegnazioni_canzoni.AssegnCanzAssembler
 * @see it.uninsubria.emotionalsongs.model.assegnazioni_canzoni.AssegnCanzone
 * @see it.uninsubria.emotionalsongs.repository.assegnazioni_canzoni.AssegnCanzRepository
 * @see it.uninsubria.emotionalsongs.utils.Logger
 */

public class AssegnCanzService {

    /**
     * Repository per le assegnazioni delle canzoni.
     */
    private final AssegnCanzRepository assegnCanzRepository;

    /**
     * Assemblatore per le assegnazioni delle canzoni.
     */
    private final AssegnCanzAssembler assegnCanzAssembler;

    /**
     * Costruisce un nuovo servizio di assegnazione delle canzoni inizializzando il repository e l'assemblatore.
     */
    public AssegnCanzService() {
        assegnCanzRepository = new AssegnCanzRepository();
        assegnCanzAssembler = new AssegnCanzAssembler();
    }

    /**
     * Inserisce un'assegnazione di canzone.
     * @param assegnazione l'assegnazione di canzone da inserire.
     * @return true se l'assegnazione è stata inserita con successo, false altrimenti.
     */
    public boolean insertAssegnazione(AssegnCanzone assegnazione) {
        Logger.info("AssegnCanzService: insertAssegnazione ");
        return assegnCanzRepository.insertAssegnazione(assegnazione);
    }
}
