package it.uninsubria.emotionalsongs.assembler.assegnazioni_canzoni;

import it.uninsubria.emotionalsongs.assembler.AssemblerImpl;
import it.uninsubria.emotionalsongs.entity.assegnazioni_canzoni.AssegnCanzEntity;
import it.uninsubria.emotionalsongs.model.assegnazioni_canzoni.AssegnCanzone;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Questa classe è responsabile della conversione tra l'entità AssegnaCanzEntity e il modello AssegnCanzone.
 * Implementa l'interfaccia AssemblerImpl per definire le operazioni di conversione.
 * @author Erik Gurzau (749400, VA)
 * @author Alessia Metaj (738945, VA)
 * @author Sara Biavaschi (748698, VA)
 * @version 1.0.0
 */
public class AssegnCanzAssembler implements AssemblerImpl<AssegnCanzEntity, AssegnCanzone> {
    /*
     * Costruttore della classe.
     */
    public AssegnCanzAssembler() { }

    /*
     * Converte un'istanza di UtenteRegistratoEntity in un'istanza di Utente.
     */
    @Override
    public AssegnCanzone toModel(AssegnCanzEntity entity) {
        return new AssegnCanzone(
                entity.getId(),
                entity.getIdPlaylist(),
                entity.getIdCanzone()
        );
    }

    /*
     * Converte una lista di UtenteRegistratoEntity in una lista di Utente
     * utilizzando il metodo toModel per ogni elemento della lista.
     */
    @Override
    public List<AssegnCanzone> toModel(List<AssegnCanzEntity> entityList) {
        return entityList.stream().map(this::toModel).collect(Collectors.toList());
    }

}
