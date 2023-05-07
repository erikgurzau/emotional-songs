package it.uninsubria.config;

import it.uninsubria.model.api.API;

public interface APIConfig {

    API[] apis = {
        new API("UTENTE","get-utente", ServerConfig.HOST, "/utente", "", "GET", ServerConfig.TIMEOUT),
    };

    API getAPIByNome(String nome);
    API[] getAPIByCodGruppo(String codGruppo);

}
