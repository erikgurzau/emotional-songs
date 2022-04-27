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


    public boolean isLogged() {
        return isLogged;
    }

    public String getAllSongs(){
        return songsManager.toString();
    }

    public Vector<Song> getListSongs(){
        return songsManager.getListSongs();
    }

    public Vector<Song> getListSongs(int idxFrom, int idxTo){
        return songsManager.getListSongs(idxFrom, idxTo);
    }


    public boolean login(String email, String psw) throws UserException {
        if (!usersManager.contains(email))
            throw new UserException("E-mail inesistente!");
        if(!usersManager.login(email, psw))
            throw new UserException("Password non corretta! Riprova");

        return isLogged = true;
    }

    public boolean contains(String email){
        return usersManager.contains(email);
    }

    public int nextUserId(){
        return usersManager.nextUserId();
    }

    public boolean addUser(User user){
        return usersManager.addUser(user);
    }


    public static void main(String[] args) {
        new Main();
    }
}
