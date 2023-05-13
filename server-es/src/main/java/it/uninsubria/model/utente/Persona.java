package it.uninsubria.model.utente;

import java.io.Serializable;

public class Persona implements Serializable {

    private String nome;
    private String cognome;
    private String codFiscale;
    private String indirizzo;
    private String cap;
    private String comune;
    private String provincia;

    public Persona() { }

    public Persona(String nome, String cognome, String codFiscale, String indirizzo, String cap, String comune, String provincia) {
        this.nome = nome;
        this.cognome = cognome;
        this.codFiscale = codFiscale;
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
        return codFiscale;
    }

    public void setCodFiscale(String codFiscale) {
        this.codFiscale = codFiscale;
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
