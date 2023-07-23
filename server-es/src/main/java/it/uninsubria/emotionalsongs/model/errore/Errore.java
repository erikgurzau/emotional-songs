package it.uninsubria.emotionalsongs.model.errore;

import java.util.ArrayList;
import java.util.List;

public class Errore {

    private Integer statusCode;
    private List<String> errors;
    private List<String> warnings;

    public Errore() {}

    public Errore(Integer statusCode, List<String> errors, List<String> warnings) {
        this.statusCode = statusCode;
        this.errors = errors;
        this.warnings = warnings;
    }

    public Errore(Integer statusCode, List<String> errors) {
        this.statusCode = statusCode;
        this.errors = errors;
        this.warnings = new ArrayList<>();
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getWarnings() {
        return warnings;
    }

    public void setWarnings(List<String> warnings) {
        this.warnings = warnings;
    }
}
