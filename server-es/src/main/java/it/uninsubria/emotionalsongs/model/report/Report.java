package it.uninsubria.emotionalsongs.model.report;

import com.fasterxml.jackson.annotation.JsonProperty;
import it.uninsubria.emotionalsongs.entity.report.ReportEntity;

import java.io.Serializable;
import java.util.List;

/**
 * La classe Playlist è responsabile della rappresentazione di una playlist creata da un utente del sistema.
 * Implementa l'interfaccia Serializable.
 * @author Erik Gurzau (749400, VA)
 * @author Alessia Metaj (738945, VA)
 * @author Sara Biavaschi (748698, VA)
 * @version 2.0.0
 * @see it.uninsubria.emotionalsongs.entity.report.ReportEntity
 */
public class Report implements Serializable {

    /**
     * L'ID della canzone a cui si riferisce il report.
     */
    @JsonProperty("idCanzone")
    private Integer idCanzone;

    /**
     * Le informazioni del report in forma aggregata per uno stato emozionale.
     */
    @JsonProperty("statiEmozionali")
    private List<ReportEntity.EmotionalState> statiEmozionali;

    /**
     * Costruttore di default della classe.
     */
    public Report() { }

    /**
     * Costruttore con parametri della classe.
     * @param idCanzone L'ID della canzone a cui si riferisce il report
     * @param statiEmozionali Le informazioni del report in forma aggregata per uno stato emozionale
     */
    public Report(Integer idCanzone, List<ReportEntity.EmotionalState> statiEmozionali) {
        this.idCanzone = idCanzone;
        this.statiEmozionali = statiEmozionali;
    }

    /**
     * Getter dell'ID della canzone a cui si riferisce il report
     * @return L'intero che rappresenta l'ID della canzone a cui si riferisce il report
     */
    public Integer getIdCanzone() {
        return idCanzone;
    }

    /**
     * Setter dell'ID della canzone a cui si riferisce il report.
     * @param idCanzone L'intero che rappresenta l'ID della canzone a cui si riferisce il report
     */
    public void setIdCanzone(Integer idCanzone) {
        this.idCanzone = idCanzone;
    }

    /**
     * Getter delle informazioni del report in forma aggregata per uno stato emozionale.
     * @return La lista delle informazioni del report per uno stato emozionale
     */
    public List<ReportEntity.EmotionalState> getStatiEmozionali() {
        return statiEmozionali;
    }

    /**
     * Setter delle informazioni del report in forma aggregata per uno stato emozionale.
     * @param statiEmozionali La lista delle informazioni del report per uno stato emozionale
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