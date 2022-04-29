package it.uninsubria.app.emotionalsongs;

import it.uninsubria.app.main.Main;
import it.uninsubria.app.managers.SongsManager;
import it.uninsubria.app.managers.UsersManager;
import it.uninsubria.app.managers.utils.SecurePassword;
import it.uninsubria.app.songs.Song;
import it.uninsubria.app.input.Input;
import it.uninsubria.app.users.User;
import it.uninsubria.app.users.exceptions.TypeStreetException;
import it.uninsubria.app.users.exceptions.UserException;
import it.uninsubria.app.users.utils.TypeStreet;
import it.uninsubria.app.views.Display;

import java.util.*;


/**
 * Classe che definisce un utente dell'applicazione
 * @author  Erik Gurzau
 * @author  Alessia Metaj
 * @author  Sara Biavaschi
 * @version 1.0.0
 * @see     it.uninsubria.app.managers.SongsManager
 * @see     it.uninsubria.app.managers.UsersManager
 * @see     it.uninsubria.app.users.exceptions.UserException
 */

public class EmotionalSongs {
    /**
     * Percorso del file degli utenti registrati all'applicazione
     */
    private final String pathFileUsers = "./data/UtentiRegistrati.txt";

    /**
     * Percorso del file delle canzoni presenti nell'applicazione
     */
    private final String pathFileSongs = "./data/Canzoni.txt";

    /**
     * Percorso del file delle playlist create dagli utenti
     */
    private final String pathFilePlaylists = "./data/Playlist.txt";

    /**
     * Percorso del file delle emozioni da scegliere per recensire una canzone
     */
    private final String pathFileEmotions = "./data/Emozioni.txt";

    /**
     * Gestore delle canzoni
     */
    private SongsManager songsManager;

    /**
     * Gestore degli utenti
     */
    private UsersManager usersManager;

    /**
     * {@code true} Se e solo se, l'utente si è loggato all'app. Altrimenti {@code false}
     */
    private boolean isLogged = false;


    /**
     * Costruttore dell'applicazione
     */
    public EmotionalSongs() {
        usersManager = new UsersManager(pathFileUsers);
        songsManager = new SongsManager(pathFileSongs);
    }


    /**
     *  Ritorna vero se l'utente si è loggato, altrimenti falso
     * @return {@code true} se e solo se, l'utente si è loggato all'applicazione.
     *      Altrimenti {@code false}
     */
    public boolean isLogged() {
        return isLogged;
    }


    /**
     * Ritorna una stringa contenente tutte le informazioni delle canzoni
     * @return stringa contenente tutte le informazioni delle canzoni
     */
    public String getAllSongs() {
        return songsManager.toString();
    }

    /**
     * Ritorna la lista delle canzoni dell'applicazione
     * @return lista delle canzoni
     */
    public Vector<Song> getListSongs() {
        return songsManager.getListSongs();
    }

    /**
     * Ritorna una sottolista di canzoni da una posizione di partenza "idxFrom"
     * ad un posizione di arrivo "idxTo"
     * @param idxFrom posizione della canzoni di partenza
     * @param idxTo posizione della canzone di fine
     * @return
     */
    public Vector<Song> getListSongs(int idxFrom, int idxTo) {
        return songsManager.getListSongs(idxFrom, idxTo);
    }


    /**
     * Verifica che le credenziali di accesso inserite dall'utente siano corrette
     * @param email email
     * @param psw password
     * @return {@code true} se e solo se, l'utente ha inserito le credenziali corrette,
     *          ovvero le due email e le due password sono uguali. Altrimenti {@code false}
     * @throws UserException se le credenziali non sono corrette
     */
    public boolean login(String email, String psw) throws UserException {
        if (!usersManager.contains(email))
            throw new UserException("E-mail inesistente!");
        if(!usersManager.login(email, psw))
            throw new UserException("Password non corretta! Riprova");

        return isLogged = true;
    }


    /**
     * Controlla se un'email è presente nella lista degli utenti
     * @param email stringa dell'email da cercare
     * @return {@code true} se e solo se, la lista degli utenti contiente almeno
     *          un utente con questa email. Altrimeni {@code false}
     */
    public boolean contains(String email){
        return usersManager.contains(email);
    }

    /**
     * Ritorna il prossimo ID disponibile da assegnare ad un nuovo utente
     * @return intero che rappresenta l'ID
     */
    public int nextUserId(){
        return usersManager.nextUserId();
    }

    /**
     * Aggiunge un nuovo utente alla lista e al file degli utenti registrati
     * @param user utente da inserire
     * @return {@code true} se e solo se, l'aggiunta del nuovo utente è
     *          stata eseguita con successo. Altrimeni {@code false}
     */
    public boolean addUser(User user){
        return usersManager.addUser(user);
    }


    public static void main(String[] args) {
        new Main();
    }
}
