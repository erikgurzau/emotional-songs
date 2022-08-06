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

    /**
     * Legge i dati dal file e li converte in playlist
     */
    public void loadData() {
        listPlaylist = parseData(fm.getContent());
    }

    /**
     * Converte una lista di stringhe, che corrispondono alle righe del file .txt
     * contenenti tutte le playlist degli utenti, in una lista di playlist.
     * I dati nel file sono salvati con il seguente formato:
     * nomePlaylist;idUtente;idCanzone,idEmozione,idEmozione;idCanzone; etc.
     * @param rowsFile Lista di stringhe con le informazione delle canzoni
     * @return Una lista di canzoni
     */
    private Vector<Playlist> parseData(Vector<String> rowsFile) {
        Vector<Playlist> listPlaylist = new Vector<>();

        EmotionsManager em = new EmotionsManager();
        SongsManager sm = new SongsManager();

        for (String row: rowsFile) {
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

    public boolean savePlaylist (Playlist playlist) {
        listPlaylist.add(playlist);
        return fm.println(playlist.toString(), 'a');
    }

    public String toString(){
        return listPlaylist.toString();
    }
}
