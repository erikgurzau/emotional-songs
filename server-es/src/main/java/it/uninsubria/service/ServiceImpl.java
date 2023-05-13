package it.uninsubria.service;

import it.uninsubria.config.APIConfig;
import it.uninsubria.model.api.API;

import java.util.Arrays;
import java.util.Optional;

public abstract class ServiceImpl implements APIConfig {
    public API getAPIByNome(String nome) {
        Optional<API> resultAPI = Arrays.stream(api_catalog)
                .filter(api -> api.getNome().equals(nome)).findFirst();
        return resultAPI.orElse(null);
    }

    @Override
    public API[] getAPIByCodGruppo(String codGruppo) {
        return (API[]) Arrays.stream(api_catalog)
                .filter(api -> api.getCodiceGruppo().equals(codGruppo)).toArray();
    }
}
