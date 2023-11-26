package it.uninsubria.emotionalsongs.config;

import it.uninsubria.emotionalsongs.model.api.API;
import it.uninsubria.emotionalsongs.utils.Costanti;

public interface ApiConfig {

    String PATH_SERVER_API = "/emotional-songs";
    String PATH_SESSIONE_API = "/sessione";
    String PATH_UTENTE_API = "/utente";
    String PATH_CANZONE_API = "/canzone";
    String PATH_PLAYLIST_API = "/playlist";
    String PATH_ASSEGNAZIONE_API = "/assegnazione";
    String PATH_REPORT_API = "/report";


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

    interface PlaylistApi {
        String ROOT = PATH_SERVER_API + PATH_PLAYLIST_API;

        API GET_ALL_PLAYLIST = new API(ROOT, Costanti.HttpMethod.GET);

        API CREA_PLAYLIST = new API(ROOT, Costanti.HttpMethod.POST);

    }

    interface AssegnCanzApi {
        String ROOT = PATH_SERVER_API + PATH_ASSEGNAZIONE_API;

        API GET_ALL_ASSEGNAZIONI = new API(ROOT, Costanti.HttpMethod.GET);

        API INSERT_ASSEGNAZIONE = new API(ROOT, Costanti.HttpMethod.POST);
    }

    interface ReportApi {
        String ROOT = PATH_SERVER_API + PATH_REPORT_API;

        API GET_REPORT = new API(ROOT+ "/:id", Costanti.HttpMethod.GET);

    }

}
