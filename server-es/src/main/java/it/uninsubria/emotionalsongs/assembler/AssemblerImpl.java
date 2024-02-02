package it.uninsubria.emotionalsongs.assembler;

import java.util.List;

/**
 * Questa interfaccia fornisce i metodi di conversione tra entità e modelli
 * o tra liste di entità e liste di modelli.
 * @author Erik Gurzau (749400, VA)
 * @author Alessia Metaj (738945, VA)
 * @author Sara Biavaschi (748698, VA)
 * @version 2.0.0
 */
public interface AssemblerImpl<Entity, Model>  {

    /**
     * Effettua la conversione da un'entità ad un modello.
     * @param entity L'entità da convertire
     * @return Il modello risultante dalla conversione
     */
    Model toModel(Entity entity);

    /**
     * Effettua la conversione da una lista di entità ad una lista di modelli.
     * @param entityList La lista di entità da convertire
     * @return La lista di modelli risultante dalla conversione
     */
    List<Model> toModel(List<Entity> entityList);

}
