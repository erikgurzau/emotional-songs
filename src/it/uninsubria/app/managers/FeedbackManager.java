package it.uninsubria.app.managers;

import it.uninsubria.app.emotionalsongs.Feedback;
import it.uninsubria.app.managers.utils.FileManager;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * Classe che rappresenta il sistema di gestione delle recensioni emozionali all'interno dell'applicazione
 * @author  Erik Gurzau
 * @author  Alessia Metaj
 * @author  Sara Biavaschi
 * @version 1.0.0
 * @see     it.uninsubria.app.emotionalsongs.Feedback
 * @see     it.uninsubria.app.managers.utils.FileManager
 */
public class FeedbackManager {

    /**
     * Percorso del file delle emozioni provate dagli utenti
     */
    private String pathFileEmotions = "data/Emozioni.txt";

    /**
     * Mappa con chiave un codice, composto dal nome della playlist e da l'ID utente (es. "Playlist-1")
     * e come valore una lista di recensioni dell'utente
     */
    private HashMap<String, Vector<Feedback>> mapFeedback;

    /**
     * Gestore I/O del file delle emozioni provate dagli utenti
     */
    private FileManager fm;

    /**
     * Costruttore
     */
    public FeedbackManager() {
        mapFeedback = new HashMap<>();
        fm = new FileManager(pathFileEmotions);
        loadData();
    }

    /**
     * Legge i dati dal file e li converte in una mappa dove
     * la chiave è formata nel seguente schema: nomePlaylist-userId;
     * Il valore è una lista di recensioni emozionali che appartengono
     * alla playlist, identificata dal nome della playlist, e dall'ID utente,
     * anch'esso definito nella chiave composta
     */
    public void loadData() {
        parseData(fm.getContent());
    }

    /**
     * Converte una lista di stringhe, che corrispondono alle righe del file .txt
     * contenenti tutte le recensioni emozionali, in una lista di recensioni.
     * Ogni riga contiene i dati della recensione fatta da un utente X su una canzone Y
     * appartenente ad una playlist Z dell'utente X
     * @param rowsFile Lista di stringhe con le informazioni delle recensioni
     * @return Una lista di recensioni emozionali
     */
    private void parseData(Vector<String> rowsFile) {

        for (String row : rowsFile) {
            Vector<Feedback> listFeedback = new Vector<>();
            StringTokenizer st = new StringTokenizer(row, ";");
            String namePlaylist = st.nextToken();
            int userId = Integer.parseInt(st.nextToken());

            while (st.hasMoreTokens()) {
                StringTokenizer feedbackTkn = new StringTokenizer(st.nextToken(), ",");
                int songId = Integer.parseInt(feedbackTkn.nextToken());
                int emotionId = Integer.parseInt(feedbackTkn.nextToken());
                int score = Integer.parseInt(feedbackTkn.nextToken());
                String note;
                if (feedbackTkn.hasMoreTokens())
                    note = feedbackTkn.nextToken();
                else note = "";
                
                listFeedback.add(new Feedback(namePlaylist, userId, songId, emotionId, score, note));
            }

            mapFeedback.put(namePlaylist + "-" + userId, listFeedback);

        }
    }

    /**
     * Verifica che l'utente con l'ID specificato abbia recensito una specifica canzone in una playlist
     * @param namePlaylist Stringa contente il nome della playlist
     * @param userId Intero che rappresenta l'ID dell'utente
     * @param songId Intero che rappresenta l'ID della canzone
     * @return {@code true} Se e solo se, l'utente ha recensito almeno una canzone di una sua playlist.
     *          Altrimenti {@code false}
     */
    public boolean hasFeedback(String namePlaylist, int userId, int songId) {
        if (!mapFeedback.containsKey(namePlaylist + "-" + userId))
            return false;

        Vector<Feedback> listFeedback = mapFeedback.get(namePlaylist + "-" + userId);
        for (Feedback f : listFeedback) {
            if (f.getSongId() == songId)
                return true;
        }
        return false;
    }

    /**
     * Verifica che la canzone con l'ID specificato abbiamo almeno una recensione in tutta l'applicazione
     * @param songId Intero che rappresenta l'ID della canzone
     * @return {@code true} Se e solo se, ha ricevuto almeno una recensione.
     *         Altrimenti {@code false}
     */
    public boolean hasFeedback(int songId) {
        for (Map.Entry<String, Vector<Feedback>> entry : mapFeedback.entrySet()) {
            Vector<Feedback> list = entry.getValue();
            for (Feedback f: list)
                if (f.getSongId() == songId)
                    return true;

        }
        return false;
    }

    /**
     * Salva le informazioni della recensione emozionale di una canzone della playlist
     * di un utente nel seguente formato:
     * nomePlaylist; userId; songId, emotionId, score, note (opzionale); songId, emotionId, ... etc.
     * @param listFeedback Lista di feedback dove ogni item rappresenta il dettaglio della recensione suddivisa per emozione
     * @return {@code true} Se e solo se, la scrittura nel file dei dati è andata a buon fine.
     *       Altrimenti {@code false}
     */
    public boolean saveFeedback(Vector<Feedback> listFeedback) {
        if (listFeedback.isEmpty()) return false;
        String s = listFeedback.get(0).getNamePlaylist() + ";" + listFeedback.get(0).getUserId() + ";";
        for (Feedback f : listFeedback) {
            s += f.toString();
        }
        return fm.println(s, 'a');
    }

    /**
     * Conta il numero di recensioni di una canzone all'interno dell'applicazione.
     * Non conta il dettaglio della recensione, ovvero non suddivide le recensioni
     * per emozioni ma le interpreta come gruppo.
     * Poiché è obbligatorio inserire tutte le N emozioni appartenenti alla lista di emozioni disponibili
     * @param songId Intero che rappresenta l'ID della canzone
     * @return Il numero di recensioni di una canzone
     */
    public int countFeedback(int songId) {
        int count = 0;
        for (Map.Entry<String, Vector<Feedback>> entry : mapFeedback.entrySet()) {
            Vector<Feedback> list = entry.getValue();
            /* prendo il primo item della lista perchè gli elementi della lista di
             feedback fanno riferimento tutti alla stessa recensione,
             quindi hanno nomePlaylist, userId e songId uguali
             */
            Feedback f = list.firstElement();
            count = f.getSongId() == songId ? count + 1 : count;
        }
        return count;
    }

    /**
     * Esegue la somma totale dell'intensità di una emozione X rispetto ad una canzone Y
     * @param songId Intero che rappresenta l'ID della canzone
     * @param emotionId Intero che rappresenta l'ID dell'emozione
     * @return La somma totale dell'intensità di una emozione X rispetto ad una canzone Y
     */
    public int totScoreFeedback(int songId, int emotionId) {
        int sum = 0;
        for (Map.Entry<String, Vector<Feedback>> entry : mapFeedback.entrySet()) {
            Vector<Feedback> list = entry.getValue();
            if (list.firstElement().getSongId() == songId) {
                for (Feedback f : list) {
                    if (f.getEmotionId() == emotionId)
                        sum += f.getScore();
                }
            }

        }
        return sum;
    }

    /**
     * Ritorna una lista di feedback, che hanno una nota non vuota, rispetto ad una canzone X e a una emozione Y
     * @param songId Intero che rappresenta l'ID della canzone
     * @param emotionId Intero che rappresenta l'ID dell'emozione
     * @return Una lista di recensioni emozionali
     */
    public Vector<Feedback> getFeedbacksIfHasNote (int songId, int emotionId) {
        Vector<Feedback> notesList = new Vector<>();
        for (Map.Entry<String, Vector<Feedback>> entry : mapFeedback.entrySet()) {
            Vector<Feedback> list = entry.getValue();
            if (list.firstElement().getSongId() == songId) {
                for (Feedback f : list) {
                    if (f.getEmotionId() == emotionId) {
                        if(!f.getNote().isEmpty()) {
                            notesList.add(f);
                        }
                    }
                }
            }

        }
        return notesList;
    }

}
