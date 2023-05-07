package it.uninsubria.model.canzone;

import java.io.Serializable;
import java.math.BigInteger;

public class Canzone implements Serializable {

    private Integer id;
    private String titolo;
    private String autore;
    private String genere;
    private BigInteger durata;
    private Integer anno;

    public Canzone(Integer id, String titolo, String autore, String genere, BigInteger durata, Integer anno) {
        this.id = id;
        this.titolo = titolo;
        this.autore = autore;
        this.genere = genere;
        this.durata = durata;
        this.anno = anno;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public BigInteger getDurata() {
        return durata;
    }

    public void setDurata(BigInteger durata) {
        this.durata = durata;
    }

    public Integer getAnno() {
        return anno;
    }

    public void setAnno(Integer anno) {
        this.anno = anno;
    }
}