package it.uninsubria.app.songs;

import java.util.Vector;
/**
 * Classe che rappresenta una playlist di un utente
 * @author  Erik Gurzau
 * @author  Alessia Metaj
 * @author  Sara Biavaschi
 * @version 1.0.0
 * @see     it.uninsubria.app.songs.Song
 * @see     it.uninsubria.app.managers.utils.FileManager
 */
public class Playlist {
    /**
     * ID dell'utente che ha creato la playlist
     */
    private int userId;

    /**
     * Nome della playlist
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

    /**
     * Ritorna la lista di interi che corrispondono agli ID delle canzoni che l'utente
     * ha scelto per creare la sua playlist
     * @return La lista di ID delle canzoni
     */
    public Vector<Integer> getListIdSongs() {
        return listSongs;
    }

    /**
     * Aggiunge una nuova canzone, mediante l'utilizzo del suo ID, alla lista di ID delle canzoni
     * @param songId Intero che rappresenta l'ID della canzone
     */
    public void addSong(int songId) {
        listSongs.add(songId);
    }

    /**
     * Verifica se esiste un determinato ID di una canzone all'interno della lista degli ID delle canzoni
     * @param songId Intero che rappresenta l'ID delle canzone da cercare
     * @return {@code true} Se e solo se, l'ID della canzone da cercare è presente all'interno della lista.
     *          Altrimenti {@code false}
     */
    public boolean contains(int songId) {
        return listSongs.contains(songId);
    }

    /**
     * Ritorna una stringa che contiene le informazioni della playlist
     * @return String che contiene i dati della playlist divisi dal separatore ';'
     */
    public String toString() {
        String s = name + ";" + userId + ";";
        for (int songId: listSongs) {
            s += songId + ";";
        }
        return s;
    }
}
