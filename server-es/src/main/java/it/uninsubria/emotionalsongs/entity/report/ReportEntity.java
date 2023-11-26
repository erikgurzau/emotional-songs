package it.uninsubria.emotionalsongs.entity.report;

import java.util.List;

public class ReportEntity {

    /**
     * L'ID della canzone da cui si riferisce il report.
     */
    private Integer idCanzone;

    /**
     * le informazioni del report in forma aggregata per uno stato emozionale.
     */
    private List<EmotionalState> statiEmozionali;

    public ReportEntity() { }

    public ReportEntity(Integer idCanzone, List<EmotionalState> statiEmozionali) {
        this.idCanzone = idCanzone;
        this.statiEmozionali = statiEmozionali;
    }

    public Integer getIdCanzone() {
        return idCanzone;
    }

    public void setIdCanzone(Integer idCanzone) {
        this.idCanzone = idCanzone;
    }

    public List<EmotionalState> getStatiEmozionali() {
        return statiEmozionali;
    }

    public void setStatiEmozionali(List<EmotionalState> statiEmozionali) {
        this.statiEmozionali = statiEmozionali;
    }

    public static class EmotionalState {

        /**
         * L'ID dello stato emozionale.
         */
        private Integer idStatoEmozionale;

        /**
         * Il numero di utenti che hanno recensito ciascuna emozione
         */
        private Integer numUtenti;

        /**
         * La media del punteggio per ciascuna emozione.
         */
        private Double mediaPunteggio;

        /**
         * Gli ultimi 5 commenti inseriti dagli utenti per ciascuna emozione
         */
        private List<String> ultime5Note;

        public EmotionalState() { }

        public EmotionalState(Integer idStatoEmozionale, Integer numUtenti, Double mediaPunteggio, List<String> ultime5Note) {
            this.idStatoEmozionale = idStatoEmozionale;
            this.numUtenti = numUtenti;
            this.mediaPunteggio = mediaPunteggio;
            this.ultime5Note = ultime5Note;
        }

        public int getIdStatoEmozionale() {
            return idStatoEmozionale;
        }

        public void setIdStatoEmozionale(Integer idStatoEmozionale) {
            this.idStatoEmozionale = idStatoEmozionale;
        }

        public Integer getNumUtenti() {
            return numUtenti;
        }

        public void setNumUtenti(Integer numUtenti) {
            this.numUtenti = numUtenti;
        }

        public double getMediaPunteggio() {
            return mediaPunteggio;
        }

        public void setMediaPunteggio(Double mediaPunteggio) {
            this.mediaPunteggio = mediaPunteggio;
        }

        public List<String> getUltime5Note() {
            return ultime5Note;
        }

        public void setNote(List<String> ultime5Note) {
            this.ultime5Note = ultime5Note;
        }
    }
}
