package it.uninsubria.app.feedbacks;

/**
 * Classe che definisce il dettaglio di una emozione per una recensione di una canzone
 * @author  Erik Gurzau (749400, VA)
 * @author  Alessia Metaj (738945, VA)
 * @author  Sara Biavaschi (748698, VA)
 * @version 1.0.0
 */
public class FeedbackItem {

    /**
     * Intero che rappresenta l'ID dell'emozione provata durante l'ascolta della canzone
     */
    private int emotionId;

    /**
     * Intero che rappresenta l'intensità dell'emozione
     */
    private int score; // da 1 a 5

    /**
     * Note della recensione (facoltative)
     */
    private String note;

    /**
     * Costruttore di una recensione emozionale con le note
     * @param emotionId Intero che rappresenta l'ID dell'emozione
     * @param score Intero che rappresenta l'intensità dell'emozione
     * @param note Stringa che contiene le note aggiuntive
     */
    public FeedbackItem(int emotionId, int score, String note) {
        this.emotionId = emotionId;
        this.score = score;
        this.note = note;
    }

    /**
     * Costruttore di una recensione emozionale senza le note
     * @param emotionId
     * @param score
     */
    public FeedbackItem(int emotionId, int score) {
        this.emotionId = emotionId;
        this.score = score;
        this.note = "";
    }

    /**
     * Getter dell'ID dell'emozione
     * @return Intero che rappresenta l'ID dell'emozione
     */
    public int getEmotionId() {
        return emotionId;
    }

    /**
     * Getter dell'intensità dell'emozione
     * @return Intero che rappresenta l'intensità dell'emozione
     */
    public int getScore() {
        return score;
    }

    /**
     * Getter delle note
     * @return Stringa che contiene le note aggiuntive
     */
    public String getNote() {
        return note;
    }

    /**
     * Ritorna una stringa che contiene le informazioni della recensione
     * @return String che contiene i dati della recensione divisi dal separatore ';'
     */
    public String toString() {
        return emotionId + "," +
                score +
                (note.isEmpty() ? ";" : "," + note + ";" );
    }
}
