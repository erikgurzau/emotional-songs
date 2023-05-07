package it.uninsubria.service.utente;

import it.uninsubria.assembler.utente.UtenteAssembler;
import it.uninsubria.model.utente.Utente;
import it.uninsubria.repository.utente.UtenteRepository;

public class UtenteSerivice {

    private final UtenteRepository utenteRepository;
    private final UtenteAssembler utenteAssembler;

    public UtenteSerivice() {
        utenteRepository = new UtenteRepository();
        utenteAssembler = new UtenteAssembler();
    }

    public Utente getUtenteById(Integer id) {
        return utenteAssembler.toModel(utenteRepository.trovaUtenteById(id));
    }
}
