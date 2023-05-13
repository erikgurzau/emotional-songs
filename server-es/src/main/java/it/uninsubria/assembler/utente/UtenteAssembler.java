package it.uninsubria.assembler.utente;

import it.uninsubria.assembler.AssemblerImpl;
import it.uninsubria.entity.utente.UtenteRegistratoEntity;
import it.uninsubria.model.utente.Utente;

import java.util.List;
import java.util.stream.Collectors;

public class UtenteAssembler implements AssemblerImpl<Utente, UtenteRegistratoEntity> {

    public UtenteAssembler() { }

    @Override
    public Utente toModel(UtenteRegistratoEntity utenteRegistratoEntity) {
        return new Utente(
                utenteRegistratoEntity.getNome(),
                utenteRegistratoEntity.getCognome(),
                utenteRegistratoEntity.getCodFiscale(),
                utenteRegistratoEntity.getIndirizzo(),
                utenteRegistratoEntity.getCap(),
                utenteRegistratoEntity.getProvincia(),
                utenteRegistratoEntity.getComune(),
                utenteRegistratoEntity.getId(),
                utenteRegistratoEntity.getEmail(),
                utenteRegistratoEntity.getPsw()
        );
    }

    @Override
    public List<Utente> toModel(List<UtenteRegistratoEntity> utenteRegistratoEntity) {
        return utenteRegistratoEntity.stream().map(this::toModel).collect(Collectors.toList());
    }

    @Override
    public UtenteRegistratoEntity toEntity(Utente model) {
        return new UtenteRegistratoEntity(
                model.getId(),
                model.getCodFiscale(),
                model.getNome(),
                model.getCognome(),
                model.getEmail(),
                model.getPassword(),
                model.getIndirizzo(),
                model.getCap(),
                model.getComune(),
                model.getProvincia()
        );
    }

    @Override
    public List<UtenteRegistratoEntity> toEntity(List<Utente> modelList) {
        return modelList.stream().map(this::toEntity).collect(Collectors.toList());
    }
    
}
