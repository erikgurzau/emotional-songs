package it.uninsubria.model.utente;

import java.io.Serializable;

public class Persona implements Serializable {

    private String nome;
    private String cognome;
    private String cod_fiscale;
    private String indirizzo;
    private String cap;
    private String comune;
    private String provincia;

    public Persona() { }

    public Persona(String nome, String cognome, String cod_fiscale, String indirizzo, String cap, String comune, String provincia) {
        this.nome = nome;
        this.cognome = cognome;
        this.cod_fiscale = cod_fiscale;
        this.indirizzo = indirizzo;
        this.cap = cap;
        this.comune = comune;
        this.provincia = provincia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCodFiscale() {
        return cod_fiscale;
    }

    public void setCodFiscale(String cod_fiscale) {
        this.cod_fiscale = cod_fiscale;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getComune() {
        return comune;
    }

    public void setComune(String comune) {
        this.comune = comune;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
}
