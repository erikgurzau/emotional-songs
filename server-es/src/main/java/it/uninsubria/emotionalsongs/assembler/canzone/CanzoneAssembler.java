package it.uninsubria.emotionalsongs.assembler.canzone;

import it.uninsubria.emotionalsongs.assembler.AssemblerImpl;
import it.uninsubria.emotionalsongs.entity.canzone.CanzoneEntity;
import it.uninsubria.emotionalsongs.model.canzone.Canzone;

import java.util.List;
import java.util.stream.Collectors;

public class CanzoneAssembler implements AssemblerImpl<Canzone, CanzoneEntity> {

    public CanzoneAssembler() { }

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
    @Override
    public List<Canzone> toModel(List<CanzoneEntity> entityList) {
        return entityList.stream().map(this::toModel).collect(Collectors.toList());
    }

    
}
