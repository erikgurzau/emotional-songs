package it.uninsubria.emotionalsongs.entity.emozione;

import it.uninsubria.emotionalsongs.entity.assegnazioni_canzoni.AssegnCanzEntity;

/**
 * Questa classe è responsabile della rappresentazione dell'entità relativa ad una recensione emozionale di un brano.
 * @author Erik Gurzau (749400, VA)
 * @author Alessia Metaj (738945, VA)
 * @author Sara Biavaschi (748698, VA)
 * @version 2.0.0
 * @see it.uninsubria.emotionalsongs.entity.assegnazioni_canzoni.AssegnCanzEntity
 */
public class EmozioneEntity {

    /**
     * L'ID della recensione.
     */
    private Integer id;

    /**
     * L'insieme delle canzoni assegnate alla playlist a cui si riferisce la recensione.
     */
    private AssegnCanzEntity assegnCanzEntity;

    /**
     * Lo stato emozionale a cui si riferisce la recensione.
     */
    private StatoEmozionaleEntity statoEmozionaleEntity;

    /**
     * L'intensità dell'emozione, con valori compresi tra 1 (Per niente) e 5 (Molto).
     */
    private Integer intensita;

    /**
     * L'eventuale nota della recensione (facoltativa).
     */
    private String nota;

    /**
     * Costruttore vuoto della classe.
     */
    public EmozioneEntity() {
        assegnCanzEntity=new AssegnCanzEntity();
        statoEmozionaleEntity=new StatoEmozionaleEntity();
    }

    /**
     * Costruttore con parametri della classe.
     * @param id L'ID della recensione
     * @param assegnCanzEntity Le canzoni da recensire
     * @param statoEmozionaleEntity Lo stato emozionale da recensire
     * @param intensita L'intensità dell'emozione
     * @param nota La nota della recensione
     */
    public EmozioneEntity(Integer id,AssegnCanzEntity assegnCanzEntity, StatoEmozionaleEntity statoEmozionaleEntity, Integer intensita, String nota){
        this.id=id;
        this.assegnCanzEntity = assegnCanzEntity;
        this.statoEmozionaleEntity = statoEmozionaleEntity;
        this.intensita=intensita;
        this.nota=nota;

    }

    /**
     * Getter dell'ID della recensione.
     * @return L'intero che rappresenta l'ID associato alla recensione
     */
    public Integer getId() {
        return id;
    }

    /**
     * Setter dell'ID della recensione emozionale.
     * @param id L'intero che rappresenta l'ID associato alla recensione
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Getter dell'ID dell'assegnazione di canzoni ad una playlist.
     * @return L'intero che rappresenta l'ID associato all'assegnazione
     */
    public Integer getIdAssegnazioneCanzone() {
        return assegnCanzEntity.getId();
    }

    /**
     * Setter dell'ID dell'assegnazione di canzoni.
     * @param id L'intero che rappresenta l'ID associato all'assegnazione
     */
    public void setIdAssegnazioneCanzone(Integer id){
        assegnCanzEntity.setId(id);
    }

    /**
     * Getter dell'ID dello stato emozionale da recensire.
     * @return L'intero che rappresenta l'ID associato allo stato emozionale
     */
    public Integer getIdStatoEmozionale() {
        return statoEmozionaleEntity.getId();
    }

    /**
     * Setter dell'ID dello stato emozionale da recensire.
     * @param id L'intero che rappresenta l'ID dello stato emozionale
     */
    public void setIdStatoEmozionale(Integer id) {
        statoEmozionaleEntity.setId(id);
    }

    /**
     * Getter del nome dello stato emozionale da recensire.
     * @return La stringa che rappresenta il nome dello stato emozionale
     */
    public String getNomeStatoEmozionale() {
        return statoEmozionaleEntity.getNome();
    }

    /**
     * Setter del nome dello stato emozionale.
     * @param nome La stringa che rappresenta il nome dello stato emozionale
     */
    public void setNomeStatoEmozionale(String nome) {
        statoEmozionaleEntity.setNome(nome);
    }

    /**
     * Getter dell'intensità dell'emozione.
     * @return L'intero che rappresenta l'intensità attribuita all'emozione
     */
    public Integer getIntensita() {
        return intensita;
    }

    /**
     * Setter dell'intensità dell'emozione.
     * @param intensita L'intero che rappresenta l'intensità da attribuire all'emozione
     */
    public void setIntensita(Integer intensita) {
        this.intensita = intensita;
    }

    /**
     * Getter della nota della recensione.
     * @return La stringa che rappresenta la nota
     */
    public String getNota() {
        return nota;
    }

    /**
     * Setter della nota della recensione.
     * @param nota La stringa che rappresenta la nota della recensione
     */
    public void setNota(String nota) {
        this.nota = nota;
    }

}