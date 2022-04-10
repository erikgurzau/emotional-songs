package it.uninsubria.app.controllers;

import it.uninsubria.app.controllers.utils.FileManager;
import it.uninsubria.app.songs.Song;

import java.util.StringTokenizer;
import java.util.Vector;

public class SongsController {
    public final String pathDB = "./data/Canzoni.txt";
    private Vector<Song> listSongs;
    private FileManager fm;

    public SongsController() {
        fm = new FileManager(pathDB);
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
                String title = st.nextToken();
                String author = st.nextToken();
                String genre = st.nextToken();
                int year = Integer.parseInt(st.nextToken());
                long duration = Long.parseLong(st.nextToken());
                list.add(new Song(title, author, genre, year, duration));
            }
        }
        return list;
    }

    @Override
    public String toString() {
        return listSongs.toString();
    }

    public static void main(String[] args) {
        SongsController songsController = new SongsController();
        System.out.println(songsController.toString());
    }
}
