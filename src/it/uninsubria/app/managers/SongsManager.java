package it.uninsubria.app.managers;

import it.uninsubria.app.managers.utils.FileManager;
import it.uninsubria.app.songs.Song;


import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Vector;

public class SongsManager {

    /**
     * Percorso del file delle canzoni presenti nell'applicazione
     */
    private String pathFile = "./data/Canzoni.txt";
    private Vector<Song> listSongs;
    private HashMap<Integer, Song> mapSongs;
    private FileManager fm;

    public SongsManager() {
        mapSongs = new HashMap<>();
        fm = new FileManager(pathFile);
        loadData();
    }

    /**
     * Legge i dati dal file e li converte in canzoni
     */
    public void loadData(){
        listSongs = parseData(fm.getContent());
    }


    /**
     * Converte una lista di stringhe, che corrispondono alle righe del file .txt
     * contenenti tutte le canzoni dell'applicazione, in una lista di
     * oggetti Song
     * @param rowsFile Lista di stringhe con le informazione delle canzoni
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
     * Getter di una canzone in base all'id della canzone.
     * Utilizzo di un indice con chiave l'id della canzone e
     * come valore la canzone
     * @param songId Id della canzone
     * @return La canzone con id specificato nei parametri
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
     * Ricerca nella lista una canzone in base a dei parametri di filtraggio:
     * titolo della canzone
     * @param titleSong Stringa che contiene il titolo della canzone
     * @return Una lista con le canzoni trovate in base ai parametri di ricerca specificati
     */
    public Vector<Song> findSongsByTitle(String titleSong) {
        Vector<Song> result = new Vector<>();
        for(Song song: listSongs) {
            String title = song.getTitle();
            String lowTitle = title.toLowerCase();
            if(lowTitle.contains(titleSong)) {
                result.add(song);
            }
        }
        return result;
    }

    /**
     * Ricerca nella lista una canzone in base a dei parametri di filtraggio:
     * nome dell'autore e anno della canzone
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
            if(lowAuth.equals(rscAuth) && year==rscYear) {
                result.add(song);
            }
        }
        return result;
    }


    @Override
    public String toString() {
        return listSongs.toString();
    }


}
