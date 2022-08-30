package it.uninsubria.app.managers;

import it.uninsubria.app.managers.utils.FileManager;
import it.uninsubria.app.songs.Song;

import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * Classe che rappresenta il sistema di gestione delle canzoni all'interno dell'applicazione
 * @author  Erik Gurzau
 * @author  Alessia Metaj
 * @author  Sara Biavaschi
 * @version 1.0.0
 * @see     it.uninsubria.app.songs.Song
 * @see     it.uninsubria.app.managers.utils.FileManager
 */
public class SongsManager {

    /**
     * Percorso del file delle canzoni presenti nell'applicazione
     */
    private String pathFile = "data/Canzoni.txt";

    /**
     * Lista delle canzoni
     */
    private Vector<Song> listSongs;

    /**
     * Indice delle canzoni con chiave l'ID della canzone e come valore l'oggetto Song
     */
    private HashMap<Integer, Song> mapSongs;

    /**
     * Gestore I/O del file delle canzoni
     */
    private FileManager fm;


    /**
     * Costruttore del gestore delle canzoni
     */
    public SongsManager() {
        mapSongs = new HashMap<>();
        fm = new FileManager(pathFile);
        loadData();
    }

    /**
     * Legge i dati dal file e li converte in una lista di canzoni
     */
    public void loadData(){
        listSongs = parseData(fm.getContent());
    }


    /**
     * Converte una lista di stringhe, che corrispondono alle righe del file .txt
     * contenenti tutte le canzoni dell'applicazione, in una lista di oggetti Song
     * @param rowsFile Lista di stringhe con le informazioni delle canzoni
     * @return Una lista di canzoni
     */
    private Vector<Song> parseData(Vector<String> rowsFile) {
        Vector<Song> list = new Vector<>();
        mapSongs.clear();
        for (String row: rowsFile) {
            StringTokenizer st = new StringTokenizer(row, ";");
            while (st.hasMoreTokens()){
                int id = Integer.parseInt(st.nextToken());
                String title = st.nextToken();
                String author = st.nextToken();
                String genre = st.nextToken();
                int year = Integer.parseInt(st.nextToken());
                int duration = Integer.parseInt(st.nextToken());
                Song s = new Song(id, title, author, genre, year, duration);
                list.add(s);
                mapSongs.put(id, s);
            }
        }
        return list;
    }

    /**
     * Getter di una canzone in base all'ID della canzone
     * @param songId Intero che rappresenta l'ID della canzone
     * @return La canzone con l'ID specificato nei parametri
     */
    public Song getSong(int songId) {
        return mapSongs.get(songId);
    }


    /**
     * Getter della lista di canzoni
     * @return Una lista di canzoni
     */
    public Vector<Song> getListSongs() {
        return listSongs;
    }

    /**
     * Crea una sotto-lista di canzoni tra un range di indici (posizioni delle canzoni nella lista)
     * @param idxFrom Indice numerico di partenza
     * @param idxTo Indice numerico di destinazione
     * @return Una lista di canzoni aventi posizione nella lista compresa tra idxFrom e idxTo
     */
    public Vector<Song> getListSongs(int idxFrom, int idxTo){
        return new Vector(listSongs.subList(idxFrom, idxTo));
    }


    /**
     * Cerca una canzone nella lista in base ad un parametro di filtraggio, ovvero il titolo della canzone.
     * Una canzone viene inserita nella lista dei risultati se nel titolo contiene rscTitleSong
     * @param titleSong Stringa che contiene il titolo della canzone
     * @return Una lista con le canzoni trovate in base ai parametri di ricerca specificati
     */
    public Vector<Song> findSongsByTitle(String titleSong) {
        Vector<Song> result = new Vector<>();
        for(Song song: listSongs) {
            String title = song.getTitle();
            String lowTitle = title.toLowerCase();
            if(lowTitle.contains(titleSong.toLowerCase())) {
                result.add(song);
            }
        }
        return result;
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
        Vector<Song> result = new Vector<>();
        for(Song song: listSongs) {
            String author = song.getAuthor();
            String lowAuth = author.toLowerCase();
            int year = song.getYear();
            if(lowAuth.contains(rscAuth.toLowerCase()) && year == rscYear) {
                result.add(song);
            }
        }
        return result;
    }


}
