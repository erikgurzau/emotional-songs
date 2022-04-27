package it.uninsubria.app.managers;

import it.uninsubria.app.managers.utils.FileManager;
import it.uninsubria.app.songs.Song;

import java.util.StringTokenizer;
import java.util.Vector;

public class SongsManager {
    private String pathFile;
    private Vector<Song> listSongs;
    private FileManager fm;

    public SongsManager(String pathFile) {
        this.pathFile = pathFile;
        fm = new FileManager(pathFile);
        loadData();
    }

    public void loadData(){
        listSongs = loadData(fm.getContent());
    }

    private Vector<Song> loadData(Vector<String> dbSongs) {
        Vector<Song> list = new Vector<>();
        for (String row: dbSongs) {
            StringTokenizer st = new StringTokenizer(row, ";");
            while (st.hasMoreTokens()){
                int id = Integer.parseInt(st.nextToken());
                String title = st.nextToken();
                String author = st.nextToken();
                String genre = st.nextToken();
                int year = Integer.parseInt(st.nextToken());
                int duration = Integer.parseInt(st.nextToken());
                list.add(new Song(id, title, author, genre, year, duration));
            }
        }
        return list;
    }

    public Vector<Song> getListSongs() {
        return listSongs;
    }

    public Vector<Song> getListSongs(int idxFrom, int idxTo){
        return new Vector(listSongs.subList(idxFrom, idxTo));
    }

    @Override
    public String toString() {
        return listSongs.toString();
    }


}
