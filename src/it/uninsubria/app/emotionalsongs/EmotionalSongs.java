package it.uninsubria.app.emotionalsongs;

import it.uninsubria.app.main.Main;
import it.uninsubria.app.managers.EmotionsManager;
import it.uninsubria.app.managers.SongsManager;
import it.uninsubria.app.managers.UsersManager;
import it.uninsubria.app.songs.Song;
import it.uninsubria.app.users.User;
import it.uninsubria.app.users.exceptions.UserException;

import java.util.*;

public class EmotionalSongs {

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
     * Utente che ha effettuato l'accesso
     */
    private User sessionUser;

    /**
     * {@code true} Se e solo se, l'utente si è loggato all'app. Altrimenti {@code false}
     */
    private boolean isLogged = false;


    /**
     * Costruttore dell'applicazione
     */
    public EmotionalSongs() {
        usersManager = new UsersManager();
        songsManager = new SongsManager();
        emotionsManager = new EmotionsManager();
    }

    public User getSessionUser() {
        return sessionUser;
    }

    /**
     * Ritorna lo stato dell'avvenuto accesso all'applicazione
     * @return Restituisce {@code true} Se e solo se, l'utente si è loggato all'app.
     *          Altrimenti {@code false}
     */
    public boolean isLogged() {
        return isLogged;
    }

    /**
     * Ritorna tutta la lista delle canzoni sotto forma di stringa, avvelendosi
     * del motodo toString() della classe Song
     * @return Una stringa di tutte le canzoni presenti nell'applicazione
     */
    public String getAllSongs(){
        return songsManager.toString();
    }

    /**
     * Ritorna una lista di canzoni
     * @return Una lista di canzoni
     */
    public Vector<Song> getListSongs(){
        return songsManager.getListSongs();
    }

    public Vector<Song> getListSongs(int idxFrom, int idxTo){
        return songsManager.getListSongs(idxFrom, idxTo);
    }

    public Vector<Song> findSongsByTitle(String research) { return songsManager.findSongsByTitle(research); }

    public Vector<Song> findSongsByAuthorAndYear(String rscAuth, int rscYear) { return songsManager.findSongsByAuthorAndYear(rscAuth, rscYear); }

    public int getUserId(String email) { return usersManager.getUserByEmail(email).getUserId(); }


    public boolean login(String email, String psw) throws UserException {
        if (!usersManager.contains(email))
            throw new UserException("E-mail inesistente!");
        if(!usersManager.login(email, psw))
            throw new UserException("Password non corretta! Riprova");

        this.sessionUser = this.usersManager.getUserByEmail(email);
        return isLogged = true;
    }

    public void logout() {
        this.sessionUser = null;
        this.isLogged = false;
    }

    public boolean contains(String email){
        return usersManager.contains(email);
    }

    public int nextUserId(){
        return usersManager.nextUserId();
    }

    public boolean register(User user){
        this.sessionUser = user;
        return usersManager.register(user);
    }
    
    public boolean registraPlaylist(Playlist playlist) {
        return playlistsManager.registraPlaylist(playlist);
    }

    public static void main(String[] args) {
        new Main();
    }
}
