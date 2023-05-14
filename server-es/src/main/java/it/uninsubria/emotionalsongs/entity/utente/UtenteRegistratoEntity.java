package it.uninsubria.emotionalsongs.entity.utente;

import java.io.Serializable;

public class UtenteRegistratoEntity implements Serializable {

    private Integer id;
    private String cod_fiscale;
    private String nome;
    private String cognome;
    private String email;
    private String psw;
    private String indirizzo;
    private String cap;
    private String comune;
    private String provincia;

    public UtenteRegistratoEntity() { }

    public UtenteRegistratoEntity(Integer id, String cod_fiscale, String nome, String cognome, String email, String psw, String indirizzo, String cap, String comune, String provincia) {
        this.id = id;
        this.cod_fiscale = cod_fiscale;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.psw = psw;
        this.indirizzo = indirizzo;
        this.cap = cap;
        this.comune = comune;
        this.provincia = provincia;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodFiscale() {
        return cod_fiscale;
    }

    public void setCodFiscale(String cod_fiscale) {
        this.cod_fiscale = cod_fiscale;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
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
