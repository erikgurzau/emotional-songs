package it.uninsubria.entity.canzone;

import java.math.BigInteger;

public class CanzoneEntity {

    private Integer id;
    private String autore;
    private String titolo;
    private String anno;
    private String id_genere;
    private BigInteger durata_ms;

    public CanzoneEntity() { }

    public CanzoneEntity(Integer id, String autore, String titolo, String anno, String id_genere, BigInteger durata_ms) {
        this.id = id;
        this.autore = autore;
        this.titolo = titolo;
        this.anno = anno;
        this.id_genere = id_genere;
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

    public String getAnno() {
        return anno;
    }

    public void setAnno(String anno) {
        this.anno = anno;
    }

    public String getId_genere() {
        return id_genere;
    }

    public void setId_genere(String id_genere) {
        this.id_genere = id_genere;
    }

    public BigInteger getDurata_ms() {
        return durata_ms;
    }

    public void setDurata_ms(BigInteger durata_ms) {
        this.durata_ms = durata_ms;
    }
}
