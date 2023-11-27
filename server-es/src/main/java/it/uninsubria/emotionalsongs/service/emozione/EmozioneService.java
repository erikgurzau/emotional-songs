package it.uninsubria.emotionalsongs.service.emozione;

import it.uninsubria.emotionalsongs.assembler.emozione.EmozioneAssembler;
import it.uninsubria.emotionalsongs.model.emozione.Emozione;
import it.uninsubria.emotionalsongs.repository.emozione.EmozioneRepository;

public class EmozioneService {
    private final EmozioneRepository emozioneRepository;
    private final EmozioneAssembler emozioneAssembler;

    public EmozioneService() {
        emozioneRepository = new EmozioneRepository();
        emozioneAssembler = new EmozioneAssembler();
    }


    public boolean creaEmozione(Emozione emozione) {
        return emozioneRepository.aggiungiEmozione(emozione);
    }


}
