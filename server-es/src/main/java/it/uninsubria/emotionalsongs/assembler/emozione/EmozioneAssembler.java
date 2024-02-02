package it.uninsubria.emotionalsongs.assembler.emozione;

import it.uninsubria.emotionalsongs.entity.emozione.EmozioneEntity;
import it.uninsubria.emotionalsongs.model.emozione.Emozione;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Questa classe è responsabile della conversione tra l'entità CanzoneEntity e il modello Canzone.
 * Implementa l'interfaccia AssemblerImpl per definire le operazioni di conversione.
 * @author Erik Gurzau (749400, VA)
 * @author Alessia Metaj (738945, VA)
 * @author Sara Biavaschi (748698, VA)
 * @version 2.0.0
 * @see it.uninsubria.emotionalsongs.entity.emozione.EmozioneEntity
 * @see it.uninsubria.emotionalsongs.model.emozione.Emozione
 */
public class EmozioneAssembler {

    /**
     * Costruttore della classe.
     */
    public EmozioneAssembler(){}

    /**
     * Converte un'istanza di EmozioneEntity in un'istanza di Emozione.
     * @param emozioneEntity L'entità da convertire
     * @return Un'emozione
     */
    public Emozione toModel(EmozioneEntity emozioneEntity) {
        return new Emozione(
                emozioneEntity.getId(),
                emozioneEntity.getIdAssegnazioneCanzone(),
                emozioneEntity.getIdStatoEmozionale(),
                emozioneEntity.getIntensita(),
                emozioneEntity.getNota()
        );
    }

    /**
     * Converte una lista di EmozioneEntity in una lista di Emozione
     * utilizzando il metodo toModel per ogni elemento della lista.
     * @param entityList La lista da convertire
     * @return Una lista di canzoni
     */
    public List<Emozione> toModel(List<EmozioneEntity> entityList) {
        return entityList.stream().map(this::toModel).collect(Collectors.toList());
    }

}

