package it.uninsubria.emotionalsongs.config;

import it.uninsubria.emotionalsongs.model.api.API;
import it.uninsubria.emotionalsongs.utils.Costanti;

public interface ApiConfig {

    String PATH_SERVER_API = "/emotional-songs";
    String PATH_SESSIONE_API = "/sessione";
    String PATH_UTENTE_API = "/utente";
    String PATH_CANZONE_API = "/canzone";


    interface UtenteApi {
        String ROOT = PATH_SERVER_API + PATH_UTENTE_API;
        API GET_ALL_UTENTI = new API(ROOT, Costanti.HttpMethod.GET);
        API GET_UTENTE_BY_ID = new API(ROOT + "/:userId", Costanti.HttpMethod.GET);
        API REGISTRA_UTENTE = new API(ROOT + "/registra", Costanti.HttpMethod.POST);

    }

    interface CanzoneApi {
        String ROOT = PATH_SERVER_API + PATH_CANZONE_API;
        API GET_ALL_CANZONI = new API(ROOT, Costanti.HttpMethod.GET);
        API GET_CANZONE_BY_ID = new API(ROOT + "/:id", Costanti.HttpMethod.GET);
    }

    interface SessioneApi {
        String ROOT = PATH_SERVER_API + PATH_SESSIONE_API;
        API CREA_SESSIONE_AUTO_ID = new API(ROOT, Costanti.HttpMethod.POST);
        API CREA_SESSIONE_ID = new API(ROOT + "/:sessionId", Costanti.HttpMethod.POST);
        API GET_SESSIONE_BY_ID = new API(ROOT + "/:sessionId", Costanti.HttpMethod.GET);
    }
    

}
