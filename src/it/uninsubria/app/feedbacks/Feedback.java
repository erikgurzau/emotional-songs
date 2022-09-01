package it.uninsubria.app.feedbacks;

import java.util.Vector;

/**
 * Classe che definisce una l'insieme di recensioni emozionali di una canzone in una playlist di un utente
 * @author  Erik Gurzau (749400, VA)
 * @author  Alessia Metaj (738945, VA)
 * @author  Sara Biavaschi (748698, VA)
 * @version 1.0.0
 * @see     it.uninsubria.app.feedbacks.FeedbackItem
 */
public class Feedback {
    /**
     * Nome della playlist (univoco per le playlist di un utente)
     */
    private String namePlaylist;

    /**
     * Intero che rappresenta l'ID dell'utente che ha prodotto la recensione
     */
    private int userId;

    /**
     * Intero che rappresenta l'ID della canzone sulla quale l'utente ha prodotto la recensione
     */
    private int songId;

    /**
     * Liste che contiene le singole recensioni di ogni emozione
     */
    private Vector<FeedbackItem> listFeedbackItem;


    /**
     * Costruttore di una recensione
     * @param namePlaylist Stringa che contiene il nome della playlist
     * @param userId Intero che rappresenta l'ID dell'utente
     * @param songId Intero che rappresenta l'ID della canzone
     */
    public Feedback(String namePlaylist, int userId, int songId) {
        this.namePlaylist = namePlaylist;
        this.userId = userId;
        this.songId = songId;
        listFeedbackItem = new Vector<>();
    }

    /**
     * Getter del nome della playlist
     * @return String contenente il nome della playlist
     */
    public String getNamePlaylist() {
        return namePlaylist;
    }

    /**
     * Getter dell'ID utente
     * @return Intero che rappresenta l'ID dell'utente
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Getter dell'ID della canzone
     * @return Intero che rappresenta l'ID della canzone
     */
    public int getSongId() {
        return songId;
    }


    public Vector<FeedbackItem> getListFeedbackItem() {
        return listFeedbackItem;
    }

    public boolean addItem(FeedbackItem feedbackItem) {
        return listFeedbackItem.add(feedbackItem);
    }

    public FeedbackItem getFeedbackItemByEmotionId(int emotionId) {
        for (FeedbackItem item: listFeedbackItem)
            if (item.getEmotionId() == emotionId)
                return item;
        return null;
    }



    /**
     * Ritorna una stringa che contiene le informazioni della recensione
     * @return String che contiene i dati della recensione divisi dal separatore ';'
     */
    public String toString() {
        String s = namePlaylist + ";" + userId + ";" + songId + ";";
        for (FeedbackItem item: listFeedbackItem)
            s += item.toString();
        return s;
    }
}