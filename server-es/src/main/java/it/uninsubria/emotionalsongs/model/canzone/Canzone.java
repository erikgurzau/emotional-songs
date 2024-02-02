package it.uninsubria.emotionalsongs.model.canzone;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Questa classe è responsabile della rappresentazione di una canzone.
 * Implementa l'interfaccia Serializable.
 * @author Erik Gurzau (749400, VA)
 * @author Alessia Metaj (738945, VA)
 * @author Sara Biavaschi (748698, VA)
 * @version 2.0.0
 */
public class Canzone implements Serializable {

    /**
     * L'ID della canzone.
     */
    @JsonProperty("id")
    private Integer id;

    /**
     * L'autore della canzone.
     */
    @JsonProperty("autore")
    private String autore;

    /**
     * Il titolo della canzone.
     */
    @JsonProperty("titolo")
    private String titolo;

    /**
     * L'anno di uscita della canzone.
     */
    @JsonProperty("anno")
    private Integer anno;

    /**
     * Il genere della canzone.
     */
    @JsonProperty("genere")
    private String genere;

    /**
     * La durata della canzone espressa in millisecondi.
     */
    @JsonProperty("durata")
    private Long durataMs;

    /**
     * Costruttore di defualt della classe.
     */
    public Canzone() { }

    /**
     * Costruttore con parametri della classe.
     * @param id L'id della canzone
     * @param autore L'autore della canzone
     * @param titolo Il titolo della canzone
     * @param anno L'anno della canzone
     * @param genere Il genere della canzone
     * @param durataMs La durata in ms della canzone
     */
    public Canzone(Integer id, String autore, String titolo, Integer anno, String genere, Long durataMs) {
        this.id = id;
        this.autore = autore;
        this.titolo = titolo;
        this.anno = anno;
        this.genere = genere;
        this.durataMs = durataMs;
    }

    /**
     * Getter dell'ID della canzone.
     * @return L'intero che rappresenta l'ID della canzone
     */
    public Integer getId() {
        return id;
    }

    /**
     * Setter l'ID della canzone.
     * @param id L'intero che rappresenta l'ID della canzone
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Getter dell'autore della canzone.
     * @return La stringa che rappresenta l'autore della canzone
     */
    public String getAutore() {
        return autore;
    }

    /**
     * Setter dell'autore della canzone.
     * @param autore La stringa che rappresenta l'autore della canzone
     */
    public void setAutore(String autore) {
        this.autore = autore;
    }

    /**
     * Getter del titolo della canzone.
     * @return La stringa che rappresenta il titolo della canzone
     */
    public String getTitolo() {
        return titolo;
    }

    /**
     * Setter del titolo della canzone.
     * @param titolo La stringa che rappresenta il titolo della canzone
     */
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    /**
     * Getter dell'anno di uscita della canzone.
     * @return L'intero che rappresenta l'anno di uscita della canzone
     */
    public Integer getAnno() {
        return anno;
    }

    /**
     * Setter dell'anno di uscita della canzone.
     * @param anno L'intero che rappresenta l'anno di uscita della canzone
     */
    public void setAnno(Integer anno) {
        this.anno = anno;
    }

    /**
     * Getter del genere della canzone.
     * @return La stringa che rappresenta il genere della canzone
     */
    public String getGenere() {
        return genere;
    }

    /**
     * Setter del genere della canzone.
     * @param genere La stringa che rappresenta il genere della canzone
     */
    public void setGenere(String genere) {
        this.genere = genere;
    }

    /**
     * Getter della durata in ms della canzone.
     * @return Long che rappresenta la durata in ms della canzone
     */
    public Long getDurata() {
        return durataMs;
    }

    /**
     * Setter della durata in ms della canzone.
     * @param durataMs Long che rappresenta la durata in ms della canzone
     */
    public void setDurata(Long durataMs) {
        this.durataMs = durataMs;
    }

    /**
     * Restituisce una rappresentazione testuale dell'oggetto Canzone.
     * @return una stringa contenente l'ID, l'autore, il titolo, l'anno
     * di uscita, il genere, la durata in ms
     */
    public String toString() {
        return String.join(",",
                getId().toString(), getAutore(), getTitolo(),
                getAnno().toString(), getGenere(), getDurata().toString()
        );
    }

}