package it.uninsubria.emotionalsongs.service.emozione;

import it.uninsubria.emotionalsongs.assembler.emozione.EmozioneAssembler;
import it.uninsubria.emotionalsongs.model.emozione.Emozione;
import it.uninsubria.emotionalsongs.repository.emozione.EmozioneRepository;

/**
 * Questa classe fornisce dei servizi per la gestione delle emozioni.
 * @author Erik Gurzau (749400, VA)
 * @author Alessia Metaj (738945, VA)
 * @author Sara Biavaschi (748698, VA)
 * @version 1.0.0
 */
public class EmozioneService {

    /**
     * Repository delle emozioni.
     */
    private final EmozioneRepository emozioneRepository;

    /**
     * Assemblatore delle emozioni.
     */
    private final EmozioneAssembler emozioneAssembler;

    /**
     * Costruisce un nuovo servizio di gestione delle emozioni inizializzando il repository e l'assemblatore.
     */
    public EmozioneService() {
        emozioneRepository = new EmozioneRepository();
        emozioneAssembler = new EmozioneAssembler();
    }

    /**
     * Crea una nuova emozione.
     * @param emozione L'emozione da creare.
     * @return true se l'emozione è stata creata con successo, false altrimenti.
     */

    public boolean creaEmozione(Emozione emozione) {
        return emozioneRepository.aggiungiEmozione(emozione);
    }


}
