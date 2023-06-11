package it.uninsubria.emotionalsongs.config;

import it.uninsubria.emotionalsongs.utils.Costanti;

public interface UtenteApiConfig {

    String PATH_BASE_CONTROLLER = Costanti.PATH_ROOT_API + Costanti.PATH_UTENTE_API;

    String GET_UTENTE_BY_ID = PATH_BASE_CONTROLLER + "/:userId";
    String CREA_UTENTE = PATH_BASE_CONTROLLER + "/crea";
}
