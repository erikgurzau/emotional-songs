package it.uninsubria.app.emotionalsongs;

/**
 * Classe che definisce una recensione emozionale di una canzone
 * @author  Erik Gurzau
 * @author  Alessia Metaj
 * @author  Sara Biavaschi
 * @version 1.0.0
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
     * Costruttore di una recensione con le note
     * @param namePlaylist Stringa che contiene il nome della playlist
     * @param userId Intero che rappresenta l'ID dell'utente
     * @param songId Intero che rappresenta l'ID della canzone
     * @param emotionId Intero che rappresenta l'ID dell'emozione
     * @param score Intero che rappresenta l'intensità dell'emozione
     * @param note Stringa che contiene le note aggiuntive
     */
    public Feedback(String namePlaylist, int userId, int songId, int emotionId, int score, String note) {
        this.namePlaylist = namePlaylist;
        this.userId = userId;
        this.songId = songId;
        this.emotionId = emotionId;
        this.score = score;
        this.note = note;
    }

    /**
     * Costruttore di una recensione senza le note
     * @param namePlaylist Stringa che contiene il nome della playlist
     * @param userId Intero che rappresenta l'ID dell'utente
     * @param songId Intero che rappresenta l'ID della canzone
     * @param emotionId Intero che rappresenta l'ID dell'emozione
     * @param score Intero che rappresenta l'intensità dell'emozione
     */
    public Feedback(String namePlaylist, int userId, int songId, int emotionId, int score) {
        this.namePlaylist = namePlaylist;
        this.userId = userId;
        this.songId = songId;
        this.emotionId = emotionId;
        this.score = score;
        this.note = "";
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
        return songId + "," +
                emotionId + "," +
                score +
                (note.isEmpty() ? ";" : "," + note + ";" );
    }
}
