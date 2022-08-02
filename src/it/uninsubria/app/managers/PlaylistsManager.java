package it.uninsubria.app.managers;

import it.uninsubria.app.emotionalsongs.Emotion;
import it.uninsubria.app.managers.utils.FileManager;
import it.uninsubria.app.songs.Playlist;
import it.uninsubria.app.songs.Song;

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
        listPlaylist = parseData(fm.getContent());
    }

    private Vector<Playlist> parseData(Vector<String> dbPlaylist) {
        Vector<Playlist> listPlaylist = new Vector<>();

        EmotionsManager em = new EmotionsManager();
        SongsManager sm = new SongsManager();

        for (String row: dbPlaylist) {
            StringTokenizer st = new StringTokenizer(row, ";");
            String name = st.nextToken();
            int userId = Integer.parseInt(st.nextToken());
            Playlist p = new Playlist(userId, name);

            while (st.hasMoreTokens()){
                StringTokenizer emotionTok = new StringTokenizer(st.nextToken(), ",");
                int songId = Integer.parseInt(emotionTok.nextToken());
                Song s = sm.getSong(songId);
                p.addSong(s);
                while (emotionTok.hasMoreTokens()){
                    Emotion e = em.getEmotion(Integer.parseInt(emotionTok.nextToken()));
                    p.addEmotion(s, e);
                }
            }

            listPlaylist.add(p);
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
