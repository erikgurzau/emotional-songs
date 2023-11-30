package it.uninsubria.emotionalsongs.assembler.canzone;

import it.uninsubria.emotionalsongs.assembler.AssemblerImpl;
import it.uninsubria.emotionalsongs.entity.canzone.CanzoneEntity;
import it.uninsubria.emotionalsongs.model.canzone.Canzone;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Questa classe è responsabile della conversione tra l'entità CanzoneEntity e il modello Canzone.
 * Implementa l'interfaccia AssemblerImpl per definire le operazioni di conversione.
 * @author Erik Gurzau (749400, VA)
 * @author Alessia Metaj (738945, VA)
 * @author Sara Biavaschi (748698, VA)
 * @version 1.0.0
 */
public class CanzoneAssembler implements AssemblerImpl<CanzoneEntity, Canzone> {

    /*
     * Costruttore della classe.
     */
    public CanzoneAssembler() { }

    /*
     * Converte un'istanza di CanzoneEntity in un'istanza di Canzone.
     */
    @Override
    public Canzone toModel(CanzoneEntity entity) {
        return new Canzone(
                entity.getId(),
                entity.getAutore(),
                entity.getTitolo(),
                entity.getAnno(),
                entity.getNomeGenereMusicale(),
                entity.getDurataMs()
        );
    }

    /*
     * Converte una lista di CanzoneEntity in una lista di Canzone
     * utilizzando il metodo toModel per ogni elemento della lista.
     */
    @Override
    public List<Canzone> toModel(List<CanzoneEntity> entityList) {
        return entityList.stream().map(this::toModel).collect(Collectors.toList());
    }

    
}
