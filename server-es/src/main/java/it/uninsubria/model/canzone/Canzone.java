package it.uninsubria.model.canzone;

import java.io.Serializable;

public class Canzone implements Serializable {

    private Integer id;
    private String autore;
    private String titolo;
    private Integer anno;
    private String genere;
    private Long durata_ms;

    public Canzone() { }

    public Canzone(Integer id, String autore, String titolo, Integer anno, String genere, Long durata_ms) {
        this.id = id;
        this.autore = autore;
        this.titolo = titolo;
        this.anno = anno;
        this.genere = genere;
        this.durata_ms = durata_ms;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public Integer getAnno() {
        return anno;
    }

    public void setAnno(Integer anno) {
        this.anno = anno;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public Long getDurata() {
        return durata_ms;
    }

    public void setDurata(Long durata_ms) {
        this.durata_ms = durata_ms;
    }

    public String toString() {
        return String.join(",",
                getId().toString(), getAutore(), getTitolo(),
                getAnno().toString(), getGenere(), getDurata().toString()
        );
    }
}