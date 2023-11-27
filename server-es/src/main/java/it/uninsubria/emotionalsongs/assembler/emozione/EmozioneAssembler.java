package it.uninsubria.emotionalsongs.assembler.emozione;

import it.uninsubria.emotionalsongs.entity.emozione.EmozioneEntity;
import it.uninsubria.emotionalsongs.model.emozione.Emozione;

import java.util.List;
import java.util.stream.Collectors;

public class EmozioneAssembler {

    public EmozioneAssembler(){}

    public Emozione toModel(EmozioneEntity emozioneEntity) {
        return new Emozione(
                emozioneEntity.getId(),
                emozioneEntity.getIdAssegnazioneCanzone(),
                emozioneEntity.getIdStatoEmozionale(),
                emozioneEntity.getIntensita(),
                emozioneEntity.getNota()

        );
    }


    public List<Emozione> toModel(List<EmozioneEntity> entityList) {
        return entityList.stream().map(this::toModel).collect(Collectors.toList());
    }


}

