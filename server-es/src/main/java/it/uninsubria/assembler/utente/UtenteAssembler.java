package it.uninsubria.assembler.utente;

import it.uninsubria.assembler.AssemblerImpl;
import it.uninsubria.entity.utente.UtenteRegistratoEntity;
import it.uninsubria.model.utente.Utente;

import java.util.List;
import java.util.stream.Collectors;

public class UtenteAssembler implements AssemblerImpl<Utente, UtenteRegistratoEntity> {

    public UtenteAssembler() { }

    @Override
    public Utente toModel(UtenteRegistratoEntity entity) {
        return new Utente(
                entity.getNome(),
                entity.getCognome(),
                entity.getCodFiscale(),
                entity.getIndirizzo(),
                entity.getCap(),
                entity.getProvincia(),
                entity.getComune(),
                entity.getId(),
                entity.getEmail(),
                entity.getPsw()
        );
    }

    @Override
    public List<Utente> toModel(List<UtenteRegistratoEntity> entityList) {
        return entityList.stream().map(this::toModel).collect(Collectors.toList());
    }

    
}
