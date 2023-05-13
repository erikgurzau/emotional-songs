package it.uninsubria.assembler.canzone;

import it.uninsubria.assembler.AssemblerImpl;
import it.uninsubria.entity.canzone.CanzoneEntity;
import it.uninsubria.model.canzone.Canzone;

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
                entity.getGenereMusicaleNome(),
                entity.getDurataMs()
        );
    }

    @Override
    public List<Canzone> toModel(List<CanzoneEntity> entityList) {
        return entityList.stream().map(this::toModel).collect(Collectors.toList());
    }

    
}
