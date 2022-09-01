package it.uninsubria.app.managers;

import it.uninsubria.app.emotionalsongs.Emotion;
import it.uninsubria.app.emotionalsongs.Feedback;
import it.uninsubria.app.songs.Playlist;
import it.uninsubria.app.songs.Song;
import it.uninsubria.app.users.User;
import it.uninsubria.app.users.exceptions.UserException;

import java.util.Vector;

/**
 * Classe che definisce il centro di gestione di tutti i manager dell'applicazione
 * @author  Erik Gurzau (749400, VA)
 * @author  Alessia Metaj (738945, VA)
 * @author  Sara Biavaschi (748698, VA)
 * @version 1.0.0
 * @see     it.uninsubria.app.managers.CommandManager
 * @see     it.uninsubria.app.managers.SongsManager
 * @see     it.uninsubria.app.managers.UsersManager
 * @see     it.uninsubria.app.managers.EmotionsManager
 * @see     it.uninsubria.app.managers.PlaylistsManager
 * @see     it.uninsubria.app.managers.FeedbackManager
 * @see     it.uninsubria.app.songs.Song
 * @see     it.uninsubria.app.songs.Playlist
 * @see     it.uninsubria.app.users.User
 * @see     it.uninsubria.app.emotionalsongs.Emotion
 * @see     it.uninsubria.app.emotionalsongs.Feedback
 * @see     it.uninsubria.app.input.Input
 */
public class CommandManager {

    /**
     * Gestore delle canzoni
     */
    private SongsManager songsManager;

    /**
     * Gestore degli utenti
     */
    private UsersManager usersManager;

    /**
     * Gestore delle emozioni
     */
    private EmotionsManager emotionsManager;

    /**
     * Gestore delle playlist
     */
    private PlaylistsManager playlistsManager;

    /**
     * Gestore delle recensioni degli utenti
     */
    private FeedbackManager feedbackManager;

    /**
     * Utente che ha effettuato l'accesso
     */
    private User sessionUser;

    /**
     * {@code true} Se e solo se, l'utente si è loggato all'app. Altrimenti {@code false}
     */
    private boolean isLogged = false;


    /**
     * Costruttore dell'hub di controllo di tutti i manager
     */
    public CommandManager() {
        usersManager = new UsersManager();
        songsManager = new SongsManager();
        emotionsManager = new EmotionsManager();
        playlistsManager = new PlaylistsManager();
        feedbackManager = new FeedbackManager();
    }

    /**
     * Getter per l'utente loggato
     * @return L'utente che eseguito il login
     */
    public User getSessionUser() {
        return sessionUser;
    }

    /**
     * Ritorna lo stato dell'avvenuta autenticazione dell'utente nell'applicazione
     * @return Restituisce {@code true} Se e solo se, l'utente si è autenticato nell'applicazione.
     *         Altrimenti {@code false}
     */
    public boolean isLogged() {
        return isLogged;
    }

    /**
     * Effettua l'accesso all'applicazione verificando i seguenti parametri:
     * - Se l'email specificata esiste;
     * - Se la password coincide esattamente con quella associata all'email;
     * @param email Stringa che contiene l'email dell'utente che vuole eseguire l'accesso
     * @param psw Stringa che contiene la password criptata da confrontare con quella associata all'email specificata
     * @return {@code true} Se e solo 
     * @throws UserException Se l'email non esiste, ovvero non ha mai effettuato la registrazione, e se la password è errata
     */
    public boolean login(String email, String psw) throws UserException {
        if (!usersManager.contains(email))
            throw new UserException("E-mail inesistente!");
        if(!usersManager.login(email, psw))
            throw new UserException("Password non corretta! Riprova");

        this.sessionUser = this.usersManager.getUserByEmail(email);
        return isLogged = true;
    }

    /**
     * Esegue il logout dell'utente che ha eseguito l'accesso
     */
    public void logout() {
        this.sessionUser = null;
        this.isLogged = false;
    }



    /* SongManager ---------------------------------------------------------------------------------------------------------------------------------------- */

    /**
     * Getter del manager delle canzoni
     * @return
     */
    public SongsManager getSongsManager() {
        return songsManager;
    }

    /**
     * Ritorna una lista di canzoni
     * @return Una lista di canzoni
     */
    public Vector<Song> getListSongs(){
        return songsManager.getListSongs();
    }

    /**
     * Cerca una canzone nella lista in base ad un parametro di filtraggio, ovvero il titolo della canzone.
     * Una canzone viene inserita nella lista dei risultati se nel titolo contiene rscTitleSong
     * @param rscTitleSong Stringa che contiene il titolo della canzone
     * @return Una lista con le canzoni trovate in base ai parametri di ricerca specificati
     */
    public Vector<Song> findSongsByTitle(String rscTitleSong) {
        return songsManager.findSongsByTitle(rscTitleSong);
    }

    /**
     * Cerca una canzone nella lista in base a dei parametri di filtraggio, ovvero l'autore e l'anno della canzone.
     * Una canzone viene inserita nella lista dei risultati se il nome dell'autore/i contiene rscAuth e
     * ha come anno lo stesso di rscYear
     * @param rscAuth Stringa che contiene il nome dell'autore
     * @param rscYear Stringa che contiene l'anno della canzone
     * @return Una lista con le canzoni trovate in base ai parametri di ricerca specificati
     */
    public Vector<Song> findSongsByAuthorAndYear(String rscAuth, int rscYear) {
        return songsManager.findSongsByAuthorAndYear(rscAuth, rscYear);
    }



    /* UserManager ---------------------------------------------------------------------------------------------------------------------------------------- */

    /**
     * Assegna ad un nuovo utente che si vuole registrare, il prossimo ID disponibile
     * @return Intero che corrisponde all'ID dell'utente
     */
    public int nextUserId(){
        return usersManager.nextUserId();
    }

    /**
     * Ricerca un utente per un ID specificato
     * @param userId Intero che rappresenta l'ID dell'utente
     * @return L'utente con l'ID specificato
     */
    public User getUserById(int userId) {
        return usersManager.getUserById(userId);
    }

    /**
     * Registra un nuovo utente nell'applicazione.
     * Aggiunge nell'indice la chiave (email) con il valore associato (oggetto User);
     * Aggiunge l'utente nella lista;
     * Aggiunge i dati dell'utente nel file divisi dal separatore ';';
     * Setta il sessionUser con questo utente;
     * @param user Oggetto User da registrare nell'applicazione
     * @return {@code true} Se e solo se, l'utente è stato registrato correttamente.
     * Altrimenti {@code false}.
     */
    public boolean register(User user){
        this.sessionUser = user;
        return usersManager.register(user);
    }


    /* EmotionManager ---------------------------------------------------------------------------------------------------------------------------------------- */

    /**
     * Getter di una canzone in base all'ID della canzone
     * @param songId Intero che rappresenta l'ID della canzone
     * @return La canzone con l'ID specificato nei parametri
     */
    public Song getSongById(int songId) {
        return songsManager.getSong(songId);
    }

    /**
     * Ritorna la lista di emozioni disponibili per la recensione emozionale di una brano
     * @return La lista delle emozioni
     */
    public Vector<Emotion> getEmotionList(){
        return emotionsManager.getListEmotions();
    }




    /* PlaylistManager ---------------------------------------------------------------------------------------------------------------------------------------- */

    /**
     * Scrive in coda una playlist nel file.
     * I dati vengono salvati nel seguente formato:
     * nomePlaylist;idUtente;idCanzone;idCanzone; etc.
     * Aggionrna la mappa delle playlist
     * @param playlist Oggetto playlist da salvare nel file
     * @return {@code true} Se e solo se, la scrittura nel file è andata a buon fine.
     * Altrimenti {@code false}
     */
    public boolean savePlaylist(Playlist playlist) {
        return playlistsManager.savePlaylist(playlist);
    }

    /**
     * Controlla che il nome della playlist che si vuole creare non esista già per l'utente loggato
     * @param namePlaylist Stringa che contiene il nome scelto dall'utente
     * @return {@code true} Se e solo se, il nome della nuova playlist non esiste nella lista
     *          di playlist create dall'utente. Altrimenti {@code false}
     */
    public boolean isNamePlaylistAvailable(String namePlaylist) {
        return playlistsManager.isNameAvailable(sessionUser.getUserId(), namePlaylist);
    }

    /**
     * Ritorna una playlist, dell'utente loggato, che ha il nome esattamente
     * uguale al nome specificato nei parametri
     * @param namePlaylist Stringa che rappresenta il nome della playlist da cercare
     * @return Una playlist che corrisponde ai parametri di ricerca. Se non esiste nessuna
     * playlist con quel nome, ritorna null
     */
    public Playlist getPlaylistByName(String namePlaylist) {
        return playlistsManager.getPlaylistByName(sessionUser.getUserId(), namePlaylist);
    }

    /**
     * Ritorna una lista di playlist in base ad un userId specificato nei parametri
     * @param userId Intero che rappresenta l'ID dell'utente registrato
     * @return Una lista di playlist. Se l'utente non ha ancora creato una playlist, ritorna null
     */
    public Vector<Playlist> getPlaylistByUserId(int userId) {
        return playlistsManager.getPlaylistByUserId(userId);
    }


    /* FeedbackManager ---------------------------------------------------------------------------------------------------------------------------------------- */

    /**
     * Salva le informazioni della recensione emozionale di una canzone della playlist
     * di un utente nel seguente formato:
     * nomePlaylist; userId; songId, emotionId, score, note (opzionale); songId, emotionId, ... etc.
     * @param listFeedback Lista di feedback dove ogni item rappresenta il dettaglio della recensione suddivisa per emozione
     * @return {@code true} Se e solo se, la scrittura nel file dei dati è andata a buon fine.
     *       Altrimenti {@code false}
     */
    public boolean saveFeedback(Vector<Feedback> listFeedback) {
        return feedbackManager.saveFeedback(listFeedback);
    }

    /**
     * Conta il numero di recensioni di una canzone all'interno dell'applicazione.
     * Non conta il dettaglio della recensione, ovvero non suddivide le recensioni
     * per emozioni ma le interpreta come gruppo.
     * Poiché è obbligatorio inserire tutte le N emozioni appartenenti alla lista di emozioni disponibili
     * @param songId Intero che rappresenta l'ID della canzone
     * @return Il numero di recensioni di una canzone
     */
    public int countFeedback(int songId) {
        return feedbackManager.countFeedback(songId);
    }

    /**
     * Esegue la somma totale dell'intensità di una emozione X rispetto ad una canzone Y
     * @param songId Intero che rappresenta l'ID della canzone
     * @param emotionId Intero che rappresenta l'ID dell'emozione
     * @return La somma totale dell'intensità di una emozione X rispetto ad una canzone Y
     */
    public int totScoreFeedback(int songId, int emotionId) {
        return feedbackManager.totScoreFeedback(songId, emotionId);
    }

    /**
     * Verifica che l'utente con l'ID specificato abbia recensito una specifica canzone in una playlist
     * @param namePlaylist Stringa contente il nome della playlist
     * @param songId Intero che rappresenta l'ID della canzone
     * @return {@code true} Se e solo se, l'utente ha recensito almeno una canzone di una sua playlist.
     *          Altrimenti {@code false}
     */
    public boolean hasFeedback(String namePlaylist, int songId) {
        return feedbackManager.hasFeedback(namePlaylist, sessionUser.getUserId(), songId);
    }

    /**
     * Verifica che la canzone con l'ID specificato abbiamo almeno una recensione in tutta l'applicazione
     * @param songId Intero che rappresenta l'ID della canzone
     * @return {@code true} Se e solo se, ha ricevuto almeno una recensione.
     *         Altrimenti {@code false}
     */
    public boolean hasFeedback(int songId) {
        return feedbackManager.hasFeedback(songId);
    }

    /**
     * Ritorna una lista di feedback, che hanno una nota non vuota, rispetto ad una canzone X e a una emozione Y
     * @param songId Intero che rappresenta l'ID della canzone
     * @param emotionId Intero che rappresenta l'ID dell'emozione
     * @return Una lista di recensioni emozionali
     */
    public Vector<Feedback> getFeedbacksIfHasNote(int songId, int emotionId) {
        return feedbackManager.getFeedbacksIfHasNote(songId, emotionId);
    }

}
