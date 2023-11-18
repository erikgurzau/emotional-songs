package it.uninsubria.emotionalsongs.model.utente;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * La classe Persona rappresenta una persona del sistema.
 * Implementa l'interfaccia Serializable.
 * @author Erik Gurzau (749400, VA)
 * @author Alessia Metaj (738945, VA)
 * @author Sara Biavaschi (748698, VA)
 * @version 1.0.0
 */
public class Persona implements Serializable {

    /**
     * L'Il nome della persona.
     */
    @JsonProperty("nome")
    private String nome;

    /**
     * Il cognome della persona.
     */
    @JsonProperty("cognome")
    private String cognome;

    /**
     * Il codice fiscale della persona.
     */
    @JsonProperty("codFiscale")
    private String codFiscale;

    /**
     * L'indirizzo della persona.
     */
    @JsonProperty("indirizzo")
    private String indirizzo;

    /**
     * Il CAP della persona.
     */
    @JsonProperty("cap")
    private String cap;

    /**
     * Il comune della persona.
     */
    @JsonProperty("comune")
    private String comune;

    /**
     * La provincia della persona.
     */
    @JsonProperty("provincia")
    private String provincia;

    /**
     * Costruttore di default.
     */
    public Persona() { }

    /**
     * Costruttore con parametri.
     * @param nome il nome della persona
     * @param cognome il cognome della persona
     * @param codFiscale il codice fiscale della persona
     * @param indirizzo l'indirizzo della persona
     * @param cap il CAP della persona
     * @param comune il comune della persona
     * @param provincia la provincia della persona
     */
    public Persona(String nome, String cognome, String codFiscale, String indirizzo, String cap, String comune, String provincia) {
        this.nome = nome;
        this.cognome = cognome;
        this.codFiscale = codFiscale;
        this.indirizzo = indirizzo;
        this.cap = cap;
        this.comune = comune;
        this.provincia = provincia;
    }

    /**
     * Restituisce il nome della persona.
     * @return il nome della persona
     */
    public String getNome() {
        return nome;
    }

    /**
     * Imposta il nome della persona.
     * @param nome il nome della persona
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Restituisce il cognome della persona.
     * @return il cognome della persona
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Imposta il cognome della persona.
     * @param cognome il cognome della persona
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * Restituisce il codice fiscale della persona.
     * @return il codice fiscale della persona
     */
    public String getCodFiscale() {
        return codFiscale;
    }

    /**
     * Imposta il codice fiscale della persona.
     * @param codFiscale il codice fiscale dell'utente
     */
    public void setCodFiscale(String codFiscale) {
        this.codFiscale = codFiscale;
    }

    /**
     * Restituisce l'indirizzo della persona.
     * @return l'indirizzo della persona
     */
    public String getIndirizzo() {
        return indirizzo;
    }

    /**
     * Imposta l'indirizzo della persona.
     * @param indirizzo l'indirizzo della persona
     */
    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    /**
     * Restituisce il CAP della persona.
     * @return il CAP della persona
     */
    public String getCap() {
        return cap;
    }

    /**
     * Imposta il CAP della persona.
     * @param cap il CAP della persona
     */
    public void setCap(String cap) {
        this.cap = cap;
    }

    /**
     * Restituisce il comune della persona.
     * @return il comune della persona
     */
    public String getComune() {
        return comune;
    }

    /**
     * Imposta il comune della persona.
     * @param comune il comune della persona
     */
    public void setComune(String comune) {
        this.comune = comune;
    }

    /**
     * Restituisce la provincia della persona.
     * @return il nome della persona
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * Imposta la provincia della persona.
     * @param provincia la provincia della persona
     */
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    /**
     * Restituisce una rappresentazione testuale dell'oggetto Persona.
     * @return una stringa contenente il nome, il cognome, il codice
     * fiscale, l'indirizzo, il CAP, il comune e la provincia.
     */
    public String toString() {
        return String.join(",",
                getNome(), getCognome(), getCodFiscale(),
                getIndirizzo(), getCap(), getComune(), getProvincia()
        );
    }

}
