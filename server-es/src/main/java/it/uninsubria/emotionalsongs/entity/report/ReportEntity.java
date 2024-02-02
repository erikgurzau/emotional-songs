package it.uninsubria.emotionalsongs.entity.report;

import java.util.List;

/**
 * Questa classe è responsabile della rappresentazione dell'entità relativa ad un report emozionale di un brano.
 * @author Erik Gurzau (749400, VA)
 * @author Alessia Metaj (738945, VA)
 * @author Sara Biavaschi (748698, VA)
 * @version 2.0.0
 */
public class ReportEntity {

    /**
     * L'ID della canzone da cui si riferisce il report.
     */
    private Integer idCanzone;

    /**
     * Le informazioni del report in forma aggregata per uno stato emozionale.
     */
    private List<EmotionalState> statiEmozionali;

    /**
     * Costruttore di default della classe.
     */
    public ReportEntity() { }

    /**
     * Costruttore con parametri della classe.
     * @param idCanzone L'id della canzone a cui si riferisce il report
     * @param statiEmozionali Le informazioni del report in forma aggregata per uno stato emozionale
     */
    public ReportEntity(Integer idCanzone, List<EmotionalState> statiEmozionali) {
        this.idCanzone = idCanzone;
        this.statiEmozionali = statiEmozionali;
    }

    /**
     * Getter dell'ID della canzone a cui si riferisce il metodo.
     * @return L'intero che rappresenta l'ID della canzone
     */
    public Integer getIdCanzone() {
        return idCanzone;
    }

    /**
     * Setter dell'ID della canzone a cui si riferisce il metodo.
     * @param idCanzone L'intero che rappresenta l'ID della canzone
     */
    public void setIdCanzone(Integer idCanzone) {
        this.idCanzone = idCanzone;
    }

    /**
     * Getter delle informazioni del report.
     * @return La lista che rappresenta le informazioni
     */
    public List<EmotionalState> getStatiEmozionali() {
        return statiEmozionali;
    }

    /**
     * Setter delle informazioni del report.
     * @param statiEmozionali La lista che rappresenta le informazioni
     */
    public void setStatiEmozionali(List<EmotionalState> statiEmozionali) {
        this.statiEmozionali = statiEmozionali;
    }

    /**
     * Sottoclasse rappresentante le informazioni del report per un singolo stato emozionale.
     */
    public static class EmotionalState {

        /**
         * L'ID dello stato emozionale.
         */
        private Integer idStatoEmozionale;

        /**
         * Il numero di utenti che hanno recensito l'emozione
         */
        private Integer numUtenti;

        /**
         * La media del punteggio per l'emozione.
         */
        private Double mediaPunteggio;

        /**
         * Gli ultimi 5 commenti inseriti dagli utenti per l'emozione
         */
        private List<String> ultime5Note;

        /**
         * Costruttore vuoto della classe.
         */
        public EmotionalState() { }

        /**
         * Costruttore con parametri della classe.
         * @param idStatoEmozionale L'ID dello stato emozionale
         * @param numUtenti Il numero di utenti che hanno recensito l'emozione
         * @param mediaPunteggio La media del punteggio per l'emozione
         * @param ultime5Note Gli ultimi 5 commenti per l'emozione
         */
        public EmotionalState(Integer idStatoEmozionale, Integer numUtenti, Double mediaPunteggio, List<String> ultime5Note) {
            this.idStatoEmozionale = idStatoEmozionale;
            this.numUtenti = numUtenti;
            this.mediaPunteggio = mediaPunteggio;
            this.ultime5Note = ultime5Note;
        }

        /**
         * Getter dell'ID dello stato emozionale a cui si riferisce il report.
         * @return L'intero che rappresenta l'ID dello stato emozionale
         */
        public int getIdStatoEmozionale() {
            return idStatoEmozionale;
        }

        /**
         * Setter dell'ID dello stato emozionale a cui si riferisce il metodo.
         * @param idStatoEmozionale L'intero che rappresenta l'ID dello stato emozionale
         */
        public void setIdStatoEmozionale(Integer idStatoEmozionale) {
            this.idStatoEmozionale = idStatoEmozionale;
        }

        /**
         * Getter dell'ID del numero di utenti che hanno recensito l'emozione.
         * @return L'intero che rappresenta il numero di utenti
         */
        public Integer getNumUtenti() {
            return numUtenti;
        }

        /**
         * Setter del numero di utenti che hanno recensito l'emozione.
         * @param numUtenti L'intero che rappresenta il numero di utenti
         */
        public void setNumUtenti(Integer numUtenti) {
            this.numUtenti = numUtenti;
        }

        /**
         * Getter della media del punteggio per l'emozione.
         * @return Double che rappresenta la media
         */
        public double getMediaPunteggio() {
            return mediaPunteggio;
        }

        /**
         * Setter della media del punteggio per l'emozione.
         * @param mediaPunteggio Double che rappresenta la media
         */
        public void setMediaPunteggio(Double mediaPunteggio) {
            this.mediaPunteggio = mediaPunteggio;
        }

        /**
         * Getter degli ultimi 5 commenti inseriti dagli utenti per l'emozione.
         * @return La lista di stringhe con i commenti
         */
        public List<String> getUltime5Note() {
            return ultime5Note;
        }

        /**
         * Setter delgli ultimi 5 commenti inseriti dagli utenti per l'emozione.
         * @param ultime5Note La lista di stringhe con i commenti
         */
        public void setNote(List<String> ultime5Note) {
            this.ultime5Note = ultime5Note;
        }
    }

}