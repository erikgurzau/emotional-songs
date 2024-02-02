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
 * @version 2.0.0
 * @see it.uninsubria.emotionalsongs.assembler.AssemblerImpl
 * @see it.uninsubria.emotionalsongs.entity.assegnazioni_canzoni.AssegnCanzEntity
 * @see it.uninsubria.emotionalsongs.model.assegnazioni_canzoni.AssegnCanzone
 */
public class AssegnCanzAssembler implements AssemblerImpl<AssegnCanzEntity, AssegnCanzone> {

    /**
     * Costruttore della classe.
     */
    public AssegnCanzAssembler() { }

    /**
     * Converte un'istanza di AssegnCanzEntity in un'istanza di AssegnCanzone.
     * @param entity L'entità da convertire
     * @return Un'assegnazione
     */
    @Override
    public AssegnCanzone toModel(AssegnCanzEntity entity) {
        return new AssegnCanzone(
                entity.getId(),
                entity.getIdPlaylist(),
                entity.getIdCanzone()
        );
    }

    /**
     * Converte una lista di AssegnCanzEntity in una lista di AssegnCanzone
     * utilizzando il metodo toModel per ogni elemento della lista.
     * @param entityList La lista da convertire
     * @return Una lista di assegnazioni di canzoni
     */
    @Override
    public List<AssegnCanzone> toModel(List<AssegnCanzEntity> entityList) {
        return entityList.stream().map(this::toModel).collect(Collectors.toList());
    }

}