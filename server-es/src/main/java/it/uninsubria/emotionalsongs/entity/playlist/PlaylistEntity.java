package it.uninsubria.emotionalsongs.entity.playlist;

import it.uninsubria.emotionalsongs.entity.canzone.CanzoneEntity;
import it.uninsubria.emotionalsongs.entity.utente.UtenteRegistratoEntity;

import java.util.List;

public class PlaylistEntity {
    private Integer id;

    private Integer id_utente;
    //private UtenteRegistratoEntity utenteRegistratoEntity;
    private String nome;

    private List<CanzoneEntity> canzoni;

    public PlaylistEntity() { }
    //public PlaylistEntity() { utenteRegistratoEntity = new UtenteRegistratoEntity();   }

    public PlaylistEntity(Integer id, Integer id_utente, String nome, List<CanzoneEntity> canzoni) {
        this.id = id;
        this.id_utente = id_utente;
        this.nome = nome;
        this.canzoni = canzoni;
    }
    /*public PlaylistEntity(Integer id, UtenteRegistratoEntity utenteRegistratoEntity, String nome) {
        this.id = id;
        this.utenteRegistratoEntity = utenteRegistratoEntity;
        this.nome = nome;
    }*/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdUtente() {
        return id_utente;
    }
    //public Integer getIdUtenteRegistratoEntity() { return utenteRegistratoEntity.getId(); }

    public void setIdUtente(Integer id_utente) {
        this.id_utente = id_utente;
    }
    //public void setIdUtenteRegistratoEntity(Integer id) { utenteRegistratoEntity.setId(id); }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<CanzoneEntity> getCanzoni() { return canzoni; }

    public void setCanzoni(List<CanzoneEntity> canzoni) { this.canzoni = canzoni; }


}
