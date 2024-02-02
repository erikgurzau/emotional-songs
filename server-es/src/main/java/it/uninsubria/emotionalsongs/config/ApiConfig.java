package it.uninsubria.emotionalsongs.config;

import it.uninsubria.emotionalsongs.model.api.API;
import it.uninsubria.emotionalsongs.utils.Costanti;

/**
 * Questa interfaccia definisce i percorsi delle API e i metodi HTTP ad esse associati.
 * @author Erik Gurzau (749400, VA)
 * @author Alessia Metaj (738945, VA)
 * @author Sara Biavaschi (748698, VA)
 * @version 2.0.0
 * @see it.uninsubria.emotionalsongs.model.api.API
 * @see it.uninsubria.emotionalsongs.utils.Costanti
 */
public interface ApiConfig {

    /**
     * Percorso principale delle API.
     */
    String PATH_SERVER_API = "/emotional-songs";

    /**
     * Percorso relativo alla sessione.
     */
    String PATH_SESSIONE_API = "/sessione";

    /**
     * Percorso relativo all'utente.
     */
    String PATH_UTENTE_API = "/utente";

    /**
     * Percorso relativo alla canzone.
     */
    String PATH_CANZONE_API = "/canzone";

    /**
     * Percorso relativo alla playlist.
     */
    String PATH_PLAYLIST_API = "/playlist";

    /**
     * Percorso relativo all'assegnazione di canzoni ad una playlist.
     */
    String PATH_ASSEGNAZIONE_API = "/assegnazione";

    /**
     * Percorso relativo al report emozionale.
     */
    String PATH_REPORT_API = "/report";

    /**
     * Percorso relativo all'emozione.
     */
    String PATH_EMOZIONE_API = "/emozione";

    /**
     * Interfaccia relativa all'utente.
     */
    interface UtenteApi {

        /**
         * Radice del percorso delle API relative all'utente.
         */
        String ROOT = PATH_SERVER_API + PATH_UTENTE_API;

        /**
         * API che richiede la lista di tutti gli utenti registrati.
         */
        API GET_ALL_UTENTI = new API(ROOT, Costanti.HttpMethod.GET);

        /**
         * API che richiede la ricerca dell'utente registrato con l'ID fornito.
         */
        API GET_UTENTE_BY_ID = new API(ROOT + "/:userId", Costanti.HttpMethod.GET);

        /**
         * API che richiede la registrazione di un nuovo utente.
         */
        API REGISTRA_UTENTE = new API(ROOT + "/registra", Costanti.HttpMethod.POST);

    }

    /**
     * Interfaccia relativa alla canzone.
     */
    interface CanzoneApi {

        /**
         * Radice del percorso delle API relative alla canzone.
         */
        String ROOT = PATH_SERVER_API + PATH_CANZONE_API;

        /**
         * API che richiede la lista di tutte le canzoni.
         */
        API GET_ALL_CANZONI = new API(ROOT, Costanti.HttpMethod.GET);

        /**
         * API che richiede la ricerca della canzone con l'ID fornito.
         */
        API GET_CANZONE_BY_ID = new API(ROOT + "/:id", Costanti.HttpMethod.GET);

        /**
         * API che richiede la ricerca della canzone corrispondente/contenente il titolo fornito.
         */
        API GET_CANZONE_BY_TITOLO = new API(ROOT + "/ricercaTitolo" + "/:titolo", Costanti.HttpMethod.GET);

        /**
         * API che richiede la ricerca della canzone con l'autore e l'anno di produzione forniti.
         */
        API GET_CANZONE_BY_AUTORE_E_ANNO = new API(ROOT + "/ricercaAutoreAnno" + "/:autore/:anno", Costanti.HttpMethod.GET);

    }

    /**
     * Interfaccia relativa alla sessione.
     */
    interface SessioneApi {

        /**
         * Radice del percorso delle API relative alla sessione.
         */
        String ROOT = PATH_SERVER_API + PATH_SESSIONE_API;

        /**
         * API che richiede la creazione di una nuova sessione con ID automatico.
         */
        API CREA_SESSIONE_AUTO_ID = new API(ROOT, Costanti.HttpMethod.POST);

        /**
         * API che richiede la creazione di una nuova sessione con l'ID fornito.
         */
        API CREA_SESSIONE_ID = new API(ROOT + "/:sessionId", Costanti.HttpMethod.POST);

        /**
         * API che richiede la ricerca della sessione con l'ID fornito.
         */
        API GET_SESSIONE_BY_ID = new API(ROOT + "/:sessionId", Costanti.HttpMethod.GET);
    }

    /**
     * Interfaccia relativa alla playlist.
     */
    interface PlaylistApi {

        /**
         * Radice del percorso delle API relative alla playlist.
         */
        String ROOT = PATH_SERVER_API + PATH_PLAYLIST_API;

        /**
         * API che richiede la lista di tutte le playlist.
         */
        API GET_ALL_PLAYLIST = new API(ROOT, Costanti.HttpMethod.GET);

        /**
         * API che richiede la creazione di una nuova playlist.
         */
        API CREA_PLAYLIST = new API(ROOT, Costanti.HttpMethod.POST);

    }

    /**
     * Interfaccia relativa all'assegnazione di canzoni.
     */
    interface AssegnCanzApi {

        /**
         * Radice del percorso delle API relative alla canzone.
         */
        String ROOT = PATH_SERVER_API + PATH_ASSEGNAZIONE_API;

        /**
         * API che richiede l'inserimento di una nuova assegnazione di canzoni ad una playlist.
         */
        API INSERT_ASSEGNAZIONE = new API(ROOT, Costanti.HttpMethod.POST);
    }

    /**
     * Interfaccia relativa al report emozionale.
     */
    interface ReportApi {

        /**
         * Radice del percorso delle API relative al report emozionale.
         */
        String ROOT = PATH_SERVER_API + PATH_REPORT_API;

        /**
         * API che richiede la visualizzazione del report emozionale relativo alla canzone con l'ID fornito.
         */
        API GET_REPORT = new API(ROOT+ "/:id", Costanti.HttpMethod.GET);

    }

    /**
     * Interfaccia relativa alla recensione emozionale di una canzone.
     */
     interface EmozioneApi {

        /**
         * Radice del percorso delle API relative all'emozione.
         */
        String ROOT = PATH_SERVER_API + PATH_EMOZIONE_API;

        /**
         * API che richiede l'inserimento di una nuova recensione emozionale di una o più canzoni.
         */
        API CREA_RECENSIONE = new API(ROOT, Costanti.HttpMethod.POST);
    }

}
