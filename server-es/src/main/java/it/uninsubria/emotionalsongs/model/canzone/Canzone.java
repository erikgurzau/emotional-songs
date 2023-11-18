package it.uninsubria.emotionalsongs.model.canzone;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * La classe Canzone rappresenta una canzone.
 * Implementa l'interfaccia Serializable.
 * @author Erik Gurzau (749400, VA)
 * @author Alessia Metaj (738945, VA)
 * @author Sara Biavaschi (748698, VA)
 * @version 1.0.0
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
    @JsonProperty("durataMs")
    private Long durataMs;

    /**
     * Costruttore di default.
     */
    public Canzone() { }

    /**
     * Costruttore con parametri.
     * @param id l'id della canzone
     * @param autore l'autore della canzone
     * @param titolo il titolo della canzone
     * @param anno l'anno della canzone
     * @param genere il genere della canzone
     * @param durataMs la durata in ms della canzone
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
     * Restituisce l'ID della canzone.
     * @return l'ID della canzone
     */
    public Integer getId() {
        return id;
    }

    /**
     * Imposta l'ID della canzone.
     * @param id l'ID della canzone
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Restituisce l'autore della canzone.
     * @return l'autore della canzone
     */
    public String getAutore() {
        return autore;
    }

    /**
     * Imposta l'autore' della canzone.
     * @param autore l'autore della canzone
     */
    public void setAutore(String autore) {
        this.autore = autore;
    }

    /**
     * Restituisce il titolo della canzone.
     * @return il titolo della canzone
     */
    public String getTitolo() {
        return titolo;
    }

    /**
     * Imposta il titolo della canzone.
     * @param titolo il titolo della canzone
     */
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    /**
     * Restituisce l'anno di uscita della canzone.
     * @return l'anno di uscita della canzone
     */
    public Integer getAnno() {
        return anno;
    }

    /**
     * Imposta l'anno di uscita della canzone.
     * @param anno l'anno di uscita della canzone
     */
    public void setAnno(Integer anno) {
        this.anno = anno;
    }

    /**
     * Restituisce il genere della canzone.
     * @return il genere della canzone
     */
    public String getGenere() {
        return genere;
    }

    /**
     * Imposta il genere della canzone.
     * @param genere il genere della canzone
     */
    public void setGenere(String genere) {
        this.genere = genere;
    }

    /**
     * Restituisce la durata in ms della canzone.
     * @return la durata in ms della canzone
     */
    public Long getDurata() {
        return durataMs;
    }

    /**
     * Imposta la durata in ms della canzone.
     * @param durataMs la durata in ms della canzone
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