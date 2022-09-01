package it.uninsubria.app.managers;

import it.uninsubria.app.feedbacks.Feedback;
import it.uninsubria.app.feedbacks.FeedbackItem;
import it.uninsubria.app.managers.utils.FileManager;

import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * Classe che rappresenta il sistema di gestione delle recensioni emozionali all'interno dell'applicazione
 * @author  Erik Gurzau (749400, VA)
 * @author  Alessia Metaj (738945, VA)
 * @author  Sara Biavaschi (748698, VA)
 * @version 1.0.0
 * @see     it.uninsubria.app.feedbacks.Feedback
 * @see     it.uninsubria.app.managers.utils.FileManager
 */
public class FeedbacksManager {

    /**
     * Percorso del file delle emozioni provate dagli utenti
     */
    private String pathFileEmotions = "data/Emozioni.txt";

    /**
     * Mappa con chiave l'ID della canzone e come valore una lista di recensioni di quella canzone
     */
    private HashMap<Integer, Vector<Feedback>> mapFeedback;

    /**
     * Gestore I/O del file delle emozioni provate dagli utenti
     */
    private FileManager fm;

    /**
     * Costruttore
     */
    public FeedbacksManager() {
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
            StringTokenizer st = new StringTokenizer(row, ";");
            String namePlaylist = st.nextToken();
            int userId = Integer.parseInt(st.nextToken());
            int songId = Integer.parseInt(st.nextToken());

            Feedback f = new Feedback(namePlaylist, userId, songId);
            while (st.hasMoreTokens()) {
                StringTokenizer feedbackTkn = new StringTokenizer(st.nextToken(), ",");
                int emotionId = Integer.parseInt(feedbackTkn.nextToken());
                int score = Integer.parseInt(feedbackTkn.nextToken());
                String note;
                if (feedbackTkn.hasMoreTokens())
                    note = feedbackTkn.nextToken();
                else note = "";
                
                f.addItem(new FeedbackItem(emotionId, score, note));
            }

            addFeedback(songId, f);

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
        if (!mapFeedback.containsKey(songId))
            return false;

        Vector<Feedback> listFeedback = mapFeedback.get(songId);
        for (Feedback f : listFeedback) {
            if (f.getNamePlaylist().equals(namePlaylist) &&
                f.getUserId() == userId &&
                f.getSongId() == songId
            )
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
        if (!mapFeedback.containsKey(songId))
            return false;

        Vector<Feedback> listFeedback = mapFeedback.get(songId);
        for (Feedback f : listFeedback) {
            if (f.getSongId() == songId)
                return true;
        }
        return false;
    }

    /**
     * Salva le informazioni della recensione emozionale di una canzone della playlist
     * di un utente nel seguente formato:
     * nomePlaylist; userId; songId; emotionId, score, note (opzionale); songId, emotionId, ... etc.
     * @param f Feedback di una canzone X, in una playlist Y che appartiene ad un utente Z
     * @return {@code true} Se e solo se, la scrittura nel file dei dati è andata a buon fine.
     *       Altrimenti {@code false}
     */
    public boolean saveFeedback(Feedback f) {
        addFeedback(f.getSongId(), f);
        return fm.println(f.toString(), 'a');
    }

    /**
     * Conta il numero di recensioni di una canzone all'interno dell'applicazione.
     * @param songId Intero che rappresenta l'ID della canzone
     * @return Il numero di recensioni di una canzone
     */
    public int countFeedback(int songId) {
        return mapFeedback.get(songId).size();
    }

    /**
     * Esegue la somma totale dell'intensità di una emozione X rispetto ad una canzone Y
     * @param songId Intero che rappresenta l'ID della canzone
     * @param emotionId Intero che rappresenta l'ID dell'emozione
     * @return La somma totale dell'intensità di una emozione X rispetto ad una canzone Y
     */
    public int totScoreFeedback(int songId, int emotionId) {
        int sum = 0;
        for (Feedback f: mapFeedback.get(songId)) {
            for (FeedbackItem item: f.getListFeedbackItem())
            if (item.getEmotionId() == emotionId)
                sum += item.getScore();
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
        for (Feedback f: mapFeedback.get(songId)) {
            for (FeedbackItem item: f.getListFeedbackItem())
                if (item.getEmotionId() == emotionId)
                    if (!item.getNote().isEmpty())
                        notesList.add(f);

        }
        return notesList;
    }

    /**
     * Aggiorna la mappa aggiungendo la nuova recensione alla lista di recensioni di una canzone
     * @param songId Intero che rappresenta l'ID della canzone alla quale aggiungere la recensione
     * @param f La recensione da aggiungere
     */
    private void addFeedback(int songId, Feedback f) {
        if (mapFeedback.containsKey(songId)) {
            Vector<Feedback> currFeedbackList = mapFeedback.get(songId);
            currFeedbackList.add(f);
            mapFeedback.put(songId, currFeedbackList);
        }
        else {
            Vector<Feedback> newFeedbackList = new Vector<>();
            newFeedbackList.add(f);
            mapFeedback.put(songId, newFeedbackList);
        }
    }

}
