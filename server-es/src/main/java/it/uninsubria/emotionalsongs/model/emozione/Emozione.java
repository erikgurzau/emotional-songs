package it.uninsubria.emotionalsongs.model.emozione;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Questa classe è responsabile della rappresentazione di una recensione di uno stato emozionale.
 * Implementa l'interfaccia Serializable.
 * @author Erik Gurzau (749400, VA)
 * @author Alessia Metaj (738945, VA)
 * @author Sara Biavaschi (748698, VA)
 * @version 2.0.0
 */
public class Emozione implements Serializable {

    /**
     * L'ID della recensione.
     */
    @JsonProperty("id")
    private Integer id;

    /**
     * L'ID dell'assegnazione alla playlist delle canzoni da recensire.
     */
    @JsonProperty("idAssegnazione")
    private Integer id_assegnazione;

    /**
     * L'ID dello stato emozionale da recensire.
     */
    @JsonProperty("idStatoEmozionale")
    private Integer id_stato_emozionale;

    /**
     * L'intensità dell'emozione, con valori compresi tra 1 (Per niente) e 5 (Molto).
     */
    @JsonProperty("intensita")
    private Integer intensita;

    /**
     * L'eventuale nota della recensione (facoltativa).
     */
    @JsonProperty("nota")
    private String nota;

    /**
     * Costruttore di default della classe.
     */
    public Emozione(){}

    /**
     * Costruttore con parametri della classe.
     * @param id L'ID della recensione
     * @param id_assegnazione L'ID dell'assegnazione
     * @param id_stato_emozionale L'ID dello stato emozionale
     * @param intensita L'intensità dell'emozione
     * @param nota La nota della recensione
     */
    public Emozione(Integer id, Integer id_assegnazione, Integer id_stato_emozionale, Integer intensita, String nota){

        this.id=id;
        this.id_assegnazione = id_assegnazione;
        this.id_stato_emozionale=id_stato_emozionale;
        this.intensita=intensita;
        this.nota=nota;

    }

    /**
     * Getter dell'ID della recensione.
     * @return L'intero che rappresenta l'ID associato alla recensione
     */
    public Integer getId() {
        return id;
    }

    /**
     * Setter dell'ID della recensione emozionale.
     * @param id L'intero che rappresenta l'ID associato alla recensione
     */
    public void setId(Integer id){
        this.id = id;
    }

    /**
     * Getter dell'ID dell'assegnazione di canzoni ad una playlist.
     * @return L'intero che rappresenta l'ID associato all'assegnazione
     */
    public Integer getIdAssegnazione() {
        return id_assegnazione;
    }

    /**
     * Setter dell'ID dell'assegnazione di canzoni.
     * @param id_assegnazione L'intero che rappresenta l'ID associato all'assegnazione
     */
    public void setIdAssegnazione(Integer id_assegnazione) {
        this.id_assegnazione = id_assegnazione;
    }

    /**
     * Getter dell'ID dello stato emozionale da recensire.
     * @return L'intero che rappresenta l'ID associato allo stato emozionale
     */
    public Integer getIdStatoEmozionale() {
        return id_stato_emozionale;
    }

    /**
     * Setter dell'ID dello stato emozionale da recensire.
     * @param id_stato_emozionale L'intero che rappresenta l'ID dello stato emozionale
     */
    public void setIdStatoEmozionale(Integer id_stato_emozionale) {
        this.id_stato_emozionale = id_stato_emozionale;
    }

    /**
     * Getter dell'intensità dell'emozione.
     * @return L'intero che rappresenta l'intensità attribuita all'emozione
     */
    public Integer getIntensita() {
        return intensita;
    }

    /**
     * Setter dell'intensità dell'emozione.
     * @param intensita L'intero che rappresenta l'intensità da attribuire all'emozione
     */
    public void setIntensita(Integer intensita) {
        this.intensita = intensita;
    }

    /**
     * Getter della nota della recensione.
     * @return La stringa che rappresenta la nota
     */
    public String getNota() {
        return nota;
    }

    /**
     * Setter della nota della recensione.
     * @param nota La stringa che rappresenta la nota della recensione
     */
    public void setNota(String nota) {
        this.nota = nota;
    }

    /**
     * Restituisce una rappresentazione testuale dell'oggetto Emozione.
     * @return una stringa contenente l'ID, l'ID dell'assegnazione, l'ID dello stato emozionale, l'intensità dell'emozione e la nota
     */
    public String toString() {
        return String.join(",",
                getId().toString(), getIdAssegnazione().toString(), getIdStatoEmozionale().toString(),
                getIntensita().toString(), getNota()
        );
    }

}