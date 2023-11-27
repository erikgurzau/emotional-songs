package it.uninsubria.emotionalsongs.entity.emozione;

import it.uninsubria.emotionalsongs.entity.assegnazioni_canzoni.AssegnCanzEntity;


public class EmozioneEntity {
    private Integer id;

    private AssegnCanzEntity id_assegnazione;

    private StatoEmozionaleEntity id_stato_emozionale;

    private Integer intensita;

    private String nota;

    public EmozioneEntity(){}


    public EmozioneEntity(Integer id,AssegnCanzEntity id_assegnazione_canzone, StatoEmozionaleEntity id_stato_emozionale, Integer intensita, String nota){
        this.id=id;
        this.id_assegnazione = id_assegnazione_canzone;
        this.id_stato_emozionale = id_stato_emozionale;
        this.intensita=intensita;
        this.nota=nota;

    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdAssegnazioneCanzone() {
        return id_assegnazione.getId();
    }

    public void setIdAssegnazioneCanzone(AssegnCanzEntity id_assegnazione){
        this.id_assegnazione=id_assegnazione;
    }

    public Integer getIdStatoEmozionale(){return id_stato_emozionale.getId();}

    public void setIdStatoEmozionale(StatoEmozionaleEntity statoEmozionaleEntity) {
        this.id_stato_emozionale = id_stato_emozionale;
    }

    public Integer getIntensita() {
        return intensita;
    }

    public void setIntensita(Integer intensita) {
        this.intensita = intensita;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

}
