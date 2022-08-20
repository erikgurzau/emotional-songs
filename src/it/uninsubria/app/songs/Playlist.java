package it.uninsubria.app.songs;

import java.util.Vector;

public class Playlist {
    /**
     * ID dell'utente che ha creato la playlist
     */
    private int userId;

    /**
     * Nome della playlist (nome univoco)
     */
    private String name;

    /**
     * Lista con gli ID delle canzoni scelte dall'utente per creare la playlist
     */
    private Vector<Integer> listSongs;


    /**
     * Costruttore di una playlist
     * @param userId Intero che rappresente l'ID dell'utente
     * @param name Stringa con il nome della playlist
     */
    public Playlist(int userId, String name){
        this.userId = userId;
        this.name = name;
        listSongs = new Vector<>();
    }

    /**
     * Restituisce il numero di canzoni totali nella playlist
     * @return Intero che rappresenta il numero di canzoni totali nella playlist
     */
    public int size() {
        return listSongs.size();
    }

    /**
     * Getter dell'ID dell'utente proprietario della playlist
     * @return Intero che rappresenta l'ID dell'utente proprietario della playlist
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Getter del nome della playlist
     * @return String che contiene il nome della playlist
     */
    public String getName() {
        return name;
    }

    public Vector<Integer> getListIdSongs() {
        return listSongs;
    }

    public void addSong(int songId) {
        listSongs.add(songId);
    }

    public boolean contains(int songId) {
        for (int id: listSongs)
            if (songId == id)
                return true;
        return false;
    }

    public String toString() {
        String s = name + ";" + userId + ";";
        for (int songId: listSongs) {
            s += songId + ";";
        }
        return s;
    }
}
