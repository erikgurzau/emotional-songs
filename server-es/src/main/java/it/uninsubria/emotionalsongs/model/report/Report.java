package it.uninsubria.emotionalsongs.model.report;

import com.fasterxml.jackson.annotation.JsonProperty;
import it.uninsubria.emotionalsongs.entity.report.ReportEntity;

import java.io.Serializable;
import java.util.List;

public class Report implements Serializable {

    /**
     * L'ID della canzone da cui si riferisce il report.
     */
    @JsonProperty("idCanzone")
    private Integer idCanzone;

    /**
     * Le informazioni del report in forma aggregata per uno stato emozionale.
     */
    @JsonProperty("statiEmozionali")
    private List<ReportEntity.EmotionalState> statiEmozionali;

    /**
     * Costruttore di default.
     */
    public Report() { }

    /**
     * Costruttore con parametri.
     * @param idCanzone l'ID della canzone a cui si riferisce il report
     * @param statiEmozionali le informazioni del report in forma aggregata per uno stato emozionale
     */
    public Report(Integer idCanzone, List<ReportEntity.EmotionalState> statiEmozionali) {
        this.idCanzone = idCanzone;
        this.statiEmozionali = statiEmozionali;
    }

    /**
     * Restituisce l'ID della canzone a cui si riferisce il report
     * @return l'ID della canzone a cui si riferisce il report
     */
    public Integer getIdCanzone() {
        return idCanzone;
    }

    /**
     * Imposta l'ID della canzone a cui si riferisce il report.
     * @param idCanzone l'ID della canzone a cui si riferisce il report
     */
    public void setIdCanzone(Integer idCanzone) {
        this.idCanzone = idCanzone;
    }

    /**
     * Restituisce le informazioni del report in forma aggregata per uno stato emozionale.
     * @return le informazioni del report in forma aggregata per uno stato emozionale
     */
    public List<ReportEntity.EmotionalState> getStatiEmozionali() {
        return statiEmozionali;
    }

    /**
     * Imposta le informazioni del report in forma aggregata per uno stato emozionale.
     * @param statiEmozionali le informazioni del report in forma aggregata per uno stato emozionale
     */
    public void setStatiEmozionali(List<ReportEntity.EmotionalState> statiEmozionali) {
        this.statiEmozionali = statiEmozionali;
    }

    /**
     * Restituisce una rappresentazione testuale dell'oggetto Report.
     * @return una stringa contenente l'ID della canzone e le informazioni del
     * report in forma aggregata per uno stato emozionale, ovvero id dell'emozione,
     * numero di utenti che hanno recensito, media dei punteggi assegnati e una
     * lista con le ultime 5 note inserite.
     */
    public String toString() {
        return String.join(",",
                getIdCanzone().toString(), getStatiEmozionali().toString()
        );
    }
}
