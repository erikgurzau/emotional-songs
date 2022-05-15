package it.uninsubria.app.managers;

import it.uninsubria.app.emotionalsongs.Emotion;
import it.uninsubria.app.managers.utils.FileManager;
import it.uninsubria.app.songs.Playlist;
import it.uninsubria.app.songs.Song;
import it.uninsubria.app.users.User;

import java.util.StringTokenizer;
import java.util.Vector;

public class PlaylistsManager {
    /**
     * Percorso del file delle playlist create dagli utenti
     */
    private String pathFile = "./data/Playlist.txt";
    private Vector<Playlist> listPlaylist;
    private FileManager fm;

    public PlaylistsManager() {
        fm = new FileManager(pathFile);
        loadData();
    }

    public void loadData() {
        listPlaylist = loadData(fm.getContent());
    }

    private Vector<Playlist> loadData(Vector<String> dbPlaylist) {
        Vector<Playlist> listPlaylist = new Vector<>();
        Vector<Emotion> listEmotion = new Vector<>();

        EmotionsManager em = new EmotionsManager();
        SongsManager sm = new SongsManager();

        for (String row: dbPlaylist) {
            StringTokenizer st = new StringTokenizer(row, ";");
            int userId = Integer.parseInt(st.nextToken());
            while (st.hasMoreTokens()){
                StringTokenizer tokenizer = new StringTokenizer(st.nextToken(), ",");
                int songId = Integer.parseInt(tokenizer.nextToken());
                Song s = sm.getSong(songId);
                while (tokenizer.hasMoreTokens()){
                    Emotion e = em.getEmotion(Integer.parseInt(tokenizer.nextToken()));
                    listEmotion.add(e);
                }

                Playlist p = new Playlist(userId);
                p.set(s, listEmotion);

                listPlaylist.add(p);
                listEmotion = new Vector<>();

            }
        }
        return listPlaylist;
    }

    public String toString(){
        return listPlaylist.toString();
    }

    public static void main(String[] args) {
        PlaylistsManager pm = new PlaylistsManager();
        System.out.println(pm.toString());
    }
}
