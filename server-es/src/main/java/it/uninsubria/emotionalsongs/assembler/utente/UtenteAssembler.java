package it.uninsubria.emotionalsongs.assembler.utente;

import it.uninsubria.emotionalsongs.assembler.AssemblerImpl;
import it.uninsubria.emotionalsongs.entity.utente.UtenteRegistratoEntity;
import it.uninsubria.emotionalsongs.model.utente.Utente;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Questa classe è responsabile della conversione tra l'entità UtenteRegistratoEntity e il modello Utente.
 * Implementa l'interfaccia AssemblerImpl per definire le operazioni di conversione.
 * @author Erik Gurzau (749400, VA)
 * @author Alessia Metaj (738945, VA)
 * @author Sara Biavaschi (748698, VA)
 * @version 2.0.0
 * @see it.uninsubria.emotionalsongs.assembler.AssemblerImpl
 * @see it.uninsubria.emotionalsongs.entity.utente.UtenteRegistratoEntity
 * @see it.uninsubria.emotionalsongs.model.utente.Utente
 */
public class UtenteAssembler implements AssemblerImpl<UtenteRegistratoEntity, Utente> {

    /**
     * Costruttore della classe.
     */
    public UtenteAssembler() { }

    /**
     * Converte un'istanza di UtenteRegistratoEntity in un'istanza di Utente.
     * @param entity L'entità da convertire
     * @return Un utente
     */
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

    /**
     * Converte una lista di UtenteRegistratoEntity in una lista di Utente
     * utilizzando il metodo toModel per ogni elemento della lista.
     * @param entityList La lista da convertire
     * @return Una lista di utenti
     */
    @Override
    public List<Utente> toModel(List<UtenteRegistratoEntity> entityList) {
        return entityList.stream().map(this::toModel).collect(Collectors.toList());
    }
    
}
