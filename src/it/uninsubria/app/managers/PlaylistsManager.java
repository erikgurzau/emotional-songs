package it.uninsubria.app.managers;

import it.uninsubria.app.managers.utils.FileManager;
import it.uninsubria.app.songs.Playlist;

import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Vector;

public class PlaylistsManager {
    /**
     * Percorso del file delle playlist create dagli utenti
     */
    private String pathFilePlaylist = "./data/Playlist.txt";

    /**
     * Mappa con chiave l'ID utente e come valore una lista di playlist create da quell'utente
     */
    private HashMap<Integer, Vector<Playlist>> playlistMap;


    /**
     * Gestore I/O del file delle playlist create dagli utenti
     */
    private FileManager fm;


    /**
     * Construttore
     */
    public PlaylistsManager() {
        playlistMap = new HashMap<>();
        fm = new FileManager(pathFilePlaylist);
        loadData();
    }

    /**
     * Legge i dati dal file e li converte in una mappa con chiave
     * l'ID dell'utente e come valore la lista delle playlist create
     * dal quel utente
     */
    public void loadData() {
        parseData(fm.getContent());
    }

    /**
     * Converte una lista di stringhe, che corrispondono alle righe del file .txt
     * contenenti tutte le playlist degli utenti, in una mappa con chiave
     * l'ID dell'utente e come valore la lista delle playlist create dal quel utente.
     * I dati nel file sono salvati con il seguente formato:
     * nomePlaylist;idUtente;idCanzone;idCanzone; etc.
     * @param rowsFile Lista di stringhe con le informazione delle canzoni
     */
    private void parseData(Vector<String> rowsFile) {

        for (String row: rowsFile) {
            StringTokenizer st = new StringTokenizer(row, ";");
            String name = st.nextToken();
            int userId = Integer.parseInt(st.nextToken());
            Playlist p = new Playlist(userId, name);

            while (st.hasMoreTokens()){
                int idSong = Integer.parseInt(st.nextToken());
                p.addSong(idSong);
            }

            /*
            Se nella mappa esiste già l'ID utente allora devo aggiungere
            la lista appena letta dal file alla lista della mappa.
            Altrimenti aggiungo semplicemente il nuovo ID utente e la nuova lista
            di playlist
             */
            if (playlistMap.containsKey(userId)) {
                Vector<Playlist> currentList = playlistMap.get(userId);
                currentList.add(p);
                playlistMap.put(userId, currentList);
            }
            else {
                Vector<Playlist> listPlaylist = new Vector<>();
                listPlaylist.add(p);
                playlistMap.put(userId, listPlaylist);
            }

        }
    }

    /**
     * Scrive in coda una playlist nel file.
     * I dati vengono salvati nel seguente formato:
     * nomePlaylist;idUtente;idCanzone;idCanzone; etc.
     * @param playlist Oggetto playlist da salvare nel file
     * @return {@code = true} Se e solo se, la scrittura nel file è andata a buon fine.
     * Altrimenti {@code = false}
     */
    public boolean savePlaylist (Playlist playlist) {
        Vector<Playlist> currPlaylist = playlistMap.get(playlist.getUserId());
        currPlaylist.add(playlist);
        playlistMap.put(playlist.getUserId(), currPlaylist);
        return fm.println(playlist.toString(), 'a');
    }

    /**
     * Conta il numero di playlist create da un determinato utente, identificato
     * dal suo proprio ID
     * @param userId Intero che rappresenta l'ID dell'utente
     * @return Intero che rappresenta il numero totale di playlist create dall'utente specificato
     */
    public int countPlaylists(int userId) {
        return playlistMap.get(userId).size();
    }


    /**
     * Controlla che il nome della playlist che si vuole creare non esista già per quell'utente
     * @param userId Intero che rappresenta l'ID utente che vuole creare una nuova playlist
     * @param namePlaylist Stringa che contiene il nome scelto dall'utente
     * @return {@code = true} Se e solo se, il nome della nuova playlist non esiste nella lista
     *          di playlist create dall'utente. Altrimenti {@code = false}
     */
    public boolean isNameAvailable(int userId, String namePlaylist) {
        Vector<Playlist> listPlaylist = playlistMap.get(userId);
        for (Playlist p : listPlaylist)
            if (p.getName().equals(namePlaylist))
                return false;
        return true;
    }


    public Vector<Playlist> getPlaylistByUserId(int userId) {
        return playlistMap.get(userId);
    }

    public Playlist getPlaylistByName(int userId, String name) {
        Vector<Playlist> listPlaylist = playlistMap.get(userId);
        for(Playlist p: listPlaylist)
            if(p.getName().equals(name))
                return p;

        return null;
    }


    public String toString(){
        return playlistMap.toString();
    }

}
