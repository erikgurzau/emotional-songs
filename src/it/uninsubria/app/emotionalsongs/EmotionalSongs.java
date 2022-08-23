package it.uninsubria.app.emotionalsongs;

import it.uninsubria.app.main.Main;
import it.uninsubria.app.managers.*;
import it.uninsubria.app.songs.Playlist;
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
     * Costruttore dell'applicazione
     */
    public EmotionalSongs() {
        usersManager = new UsersManager();
        songsManager = new SongsManager();
        emotionsManager = new EmotionsManager();
        playlistsManager = new PlaylistsManager();
        feedbackManager = new FeedbackManager();
    }

    public SongsManager getSongsManager() {
        return songsManager;
    }

    public EmotionsManager getEmotionsManager() {
        return emotionsManager;
    }

    public User getSessionUser() {
        return sessionUser;
    }

    /**
     * Ritorna lo stato dell'avvenuto accesso all'applicazione
     * @return Restituisce {@code true} Se e solo se, l'utente si è loggato all'app.
     *         Altrimenti {@code false}
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

    public Vector<Song> findSongsByTitle(String research) {
        return songsManager.findSongsByTitle(research);
    }

    public Vector<Song> findSongsByAuthorAndYear(String rscAuth, int rscYear) {
        return songsManager.findSongsByAuthorAndYear(rscAuth, rscYear);
    }



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

    public Song getSongById(int idSong) {
        return songsManager.getSong(idSong);
    }

    public boolean savePlaylist(Playlist playlist) {
        return playlistsManager.savePlaylist(playlist);
    }

    public boolean isNamePlaylistAvailable(String namePlaylist) {
        return playlistsManager.isNameAvailable(sessionUser.getUserId(), namePlaylist);
    }

    public int countPlaylists(int userId) {
        return playlistsManager.countPlaylists(userId);
    }


    public Playlist getPlaylistByName(String namePlaylist) {
        return playlistsManager.getPlaylistByName(sessionUser.getUserId(), namePlaylist);
    }


    public int getUserId(String email) { return usersManager.getUserByEmail(email).getUserId(); }

    public Vector<Playlist> getPlaylistByUserId(int userId) { return playlistsManager.getPlaylistByUserId(userId); }

    public Song getSong(int songId) { return songsManager.getSong(songId); }

    public Emotion getEmotion(int emotionId) { return emotionsManager.getEmotion(emotionId); }

    public int emotionsListSize() {
        return emotionsManager.size();
    }

    public Vector<Emotion> getEmotionList(){
        return emotionsManager.getListEmotions();
    }

    public boolean saveFeedback(Vector<Feedback> listFeedback) {
        return feedbackManager.saveFeedback(listFeedback);
    }

    public int countFeedback(int songId, int emotionId) {
        return feedbackManager.countFeedback(songId, emotionId);
    }

    public int totScoreFeedback(int songId, int emotionId) {
        return feedbackManager.totScoreFeedback(songId, emotionId);
    }

    public boolean containsFeedback(String namePlaylist, int songId) {
        return false;
    }

    public static void main(String[] args) {
        new Main();
    }
}
