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

    public void loadData(){
        listSongs = loadData(fm.getContent());
    }

    private Vector<Song> loadData(Vector<String> dbSongs) {
        Vector<Song> list = new Vector<>();
        mapSongs.clear();
        for (String row: dbSongs) {
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

    public Song getSong(int songId) {
        return mapSongs.get(songId);
    }

    public Vector<Song> getListSongs() {
        return listSongs;
    }

    public Vector<Song> getListSongs(int idxFrom, int idxTo){
        return new Vector(listSongs.subList(idxFrom, idxTo));
    }


    /**
     * Ritorna una lista di canzoni che contengono nel titolo la stringa cercata (case insensitive)
     * @param research
     * @return un vettore con le canzoni trovate
     */
    public Vector<Song> findSongsByTitle(String research) {
        Vector<Song> result = new Vector<>();
        for(Song song: listSongs) {
            String title = song.getTitle();
            String lowTitle = title.toLowerCase();
            if(lowTitle.contains(research)) {
                result.add(song);
            }
        }
        return result;
    }

    /**
     * Prende in input un autore e un anno e restituisce il nome del/i brano/i corrispondenti ad autore ed anno ricercato (ricerca autore case insensitive)
     * @param rscAuth
     * @param rscYear
     * @return un vettore con le canzoni trovate
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
