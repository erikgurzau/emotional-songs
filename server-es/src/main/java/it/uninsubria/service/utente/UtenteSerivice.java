package it.uninsubria.service.utente;

import it.uninsubria.model.utente.Utente;
import it.uninsubria.repository.utente.UtenteRepository;

public class UtenteSerivice {

    private UtenteRepository utenteRepository;

    UtenteSerivice() {
        utenteRepository = new UtenteRepository();
    }

    public Utente getUtenteById(int id) {
        return utenteRepository.trovaUtenteById(id);
    }
}
