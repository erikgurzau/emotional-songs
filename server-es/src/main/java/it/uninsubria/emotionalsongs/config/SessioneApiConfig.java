package it.uninsubria.emotionalsongs.config;

import it.uninsubria.emotionalsongs.utils.Costanti;

public interface SessioneApiConfig {

    String PATH_BASE_CONTROLLER = Costanti.PATH_ROOT_API + Costanti.PATH_SESSIONE_API;

    String CREA_SESSIONE = PATH_BASE_CONTROLLER;

    String GET_SESSIONE_BY_ID = PATH_BASE_CONTROLLER + "/:sessionId";
}
