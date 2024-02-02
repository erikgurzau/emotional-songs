package it.uninsubria.emotionalsongs.model.errore;

import java.util.ArrayList;
import java.util.List;

/**
 * Questa classe è responsabile della rappresentazione di un errore.
 * @author Erik Gurzau (749400, VA)
 * @author Alessia Metaj (738945, VA)
 * @author Sara Biavaschi (748698, VA)
 * @version 2.0.0
 */
public class Errore {

    /**
     * Il codice di errore.
     */
    private Integer statusCode;

    /**
     * La lista degli errori.
     */
    private List<String> errors;

    /**
     * La lista delle avvertenze.
     */
    private List<String> warnings;

    /**
     * Costruttore di default della classe.
     */
    public Errore() {}

    /**
     * Costruttore con parametri della classe.
     * @param statusCode Il codice di errore
     * @param errors La lista di errori
     * @param warnings La lista di avvertenze
     */
    public Errore(Integer statusCode, List<String> errors, List<String> warnings) {
        this.statusCode = statusCode;
        this.errors = errors;
        this.warnings = warnings;
    }

    /**
     * Costruttore con parametri della classe.
     * @param statusCode Il codice di errore
     * @param errors La lista di errori
     */
    public Errore(Integer statusCode, List<String> errors) {
        this.statusCode = statusCode;
        this.errors = errors;
        this.warnings = new ArrayList<>();
    }

    /**
     * Getter del codice di errore.
     * @return L'intero che rappresenta il codice di errore
     */
    public Integer getStatusCode() {
        return statusCode;
    }

    /**
     * Setter del codice di errore.
     * @param statusCode L'intero che rappresenta il codice di errore
     */
    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * Getter degli errori.
     * @return La lista delle stringhe che rappresentano gli errori
     */
    public List<String> getErrors() {
        return errors;
    }

    /**
     * Setter degli errori.
     * @param errors La lista delle stringhe che rappresentano gli errori
     */
    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    /**
     * Getter delle avvertenze.
     * @return La lista delle stringhe che rappresentano le avvertenze
     */
    public List<String> getWarnings() {
        return warnings;
    }

    /**
     * Setter delle avvertenze.
     * @param warnings La lista delle stringhe che rappresentano le avvertenze
     */
    public void setWarnings(List<String> warnings) {
        this.warnings = warnings;
    }

}