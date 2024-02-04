package it.uninsubria.emotionalsongs.service.emozione;

import it.uninsubria.emotionalsongs.assembler.emozione.EmozioneAssembler;
import it.uninsubria.emotionalsongs.model.emozione.Emozione;
import it.uninsubria.emotionalsongs.repository.emozione.EmozioneRepository;

/**
 * Questa classe fornisce dei servizi per la gestione delle recensioni emozionali.
 * @author Erik Gurzau (749400, VA)
 * @author Alessia Metaj (738945, VA)
 * @author Sara Biavaschi (748698, VA)
 * @version 2.0.0
 * @see it.uninsubria.emotionalsongs.assembler.emozione.EmozioneAssembler
 * @see it.uninsubria.emotionalsongs.model.emozione.Emozione
 * @see it.uninsubria.emotionalsongs.repository.emozione.EmozioneRepository
 */
public class EmozioneService {

    /**
     * Repository delle recensioni emozionali.
     */
    private final EmozioneRepository emozioneRepository;

    /**
     * Assemblatore delle recensioni emozionali.
     */
    private final EmozioneAssembler emozioneAssembler;

    /**
     * Costruisce un nuovo servizio di gestione delle recensioni emozionali inizializzando il repository e l'assemblatore.
     */
    public EmozioneService() {
        emozioneRepository = new EmozioneRepository();
        emozioneAssembler = new EmozioneAssembler();
    }

    /**
     * Crea una nuova recensione emozionale.
     * @param emozione I dati della recensione emozionale da creare
     * @return {@code true} se l'emozione è stata creata con successo, {@code false} altrimenti
     */
    public boolean creaEmozione(Emozione emozione) {
        return emozioneRepository.aggiungiEmozione(emozione);
    }

}