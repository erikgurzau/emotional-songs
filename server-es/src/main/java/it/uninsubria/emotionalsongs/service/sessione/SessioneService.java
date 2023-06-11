package it.uninsubria.emotionalsongs.service.sessione;

import it.uninsubria.emotionalsongs.assembler.utente.UtenteAssembler;
import it.uninsubria.emotionalsongs.entity.utente.UtenteRegistratoEntity;
import it.uninsubria.emotionalsongs.model.sessione.Sessione;
import it.uninsubria.emotionalsongs.model.utente.Utente;
import it.uninsubria.emotionalsongs.repository.utente.UtenteRepository;
import it.uninsubria.emotionalsongs.service.SharedService;
import it.uninsubria.emotionalsongs.utils.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static it.uninsubria.emotionalsongs.utils.Utils.isNull;

public class SessioneService {

    private static Map<String, Sessione> mappaSessioni = new ConcurrentHashMap<>(); //sessionId -> session

    private UtenteRepository utenteRepository;

    private UtenteAssembler utenteAssembler;


    public SessioneService() {
        mappaSessioni = new ConcurrentHashMap<>();
        utenteRepository = new UtenteRepository();
        utenteAssembler = new UtenteAssembler();
    }

    public Sessione creaSessione(String email, String password) {
        Optional<Sessione> optSessione = findSessioneAttiva(email);
        if (optSessione.isPresent())
            return optSessione.get();

        Optional<Utente> optUtente = login(email, password);
        if (optUtente.isPresent()) {
            Sessione sessione = new Sessione(optUtente.get());
            mappaSessioni.put(sessione.getSessionId(), sessione);
            return sessione;
        }
        else return null;
    }

    public Sessione getSessione(String sessionId) {
        return mappaSessioni.get(sessionId);
    }

    public void aggiornaSessione(Sessione session) {
        mappaSessioni.put(session.getSessionId(), session);
    }

    public void cancellaSessione(String sessionId) {
        mappaSessioni.remove(sessionId);
    }

    public Optional<Utente> login(String email, String password) {
        return utenteRepository.findByEmailAndPassword(email, password)
                .map(utenteAssembler::toModel);
    }


    public Optional<Sessione> findSessioneAttiva(String email) {
        return mappaSessioni.values().stream()
                .filter(s -> s.getUtente().getEmail().equals(email) && !s.isScaduta())
                .findFirst();
    }



}
