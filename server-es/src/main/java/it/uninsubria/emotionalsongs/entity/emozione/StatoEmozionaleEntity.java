package it.uninsubria.emotionalsongs.entity.emozione;

/**
 * Questa classe è responsabile della rappresentazione dell'entità relativa ad uno stato emozionale che può essere recensito.
 * @author Erik Gurzau (749400, VA)
 * @author Alessia Metaj (738945, VA)
 * @author Sara Biavaschi (748698, VA)
 * @version 2.0.0
 */
public class StatoEmozionaleEntity {

    /**
     * L'ID delli stato emozionale.
     */
    private Integer id;

    /**
     * Il nome dello stato emozionale.
     */
    private String nome;

    /**
     * La descrizione dello stato emozionale.
     */
    private String descrizione;

    /**
     * Costruttore di default della classe.
     */
    public StatoEmozionaleEntity(){ }

    /**
     * Costruttore con parametri della classe.
     * @param id L'ID dello stato emozionale
     * @param nome Il nome dello stato emozionale
     * @param descrizione La descrizione dello stato emozionale
     */
    public StatoEmozionaleEntity(Integer id, String nome, String descrizione){
        this.id=id;
        this.nome=nome;
        this.descrizione=descrizione;
    }

    /**
     * Getter dell'ID dello stato emozionale.
     * @return L'intero che rappresenta l'ID associato allo stato emozionale
     */
    public Integer getId(){
        return id;
    }

    /**
     * Setter dell'ID dello stato emozionale.
     * @param id L'intero che rappresenta l'ID associato allo stato emozionale
     */
    public void setId(Integer id){
        this.id=id;
    }

    /**
     * Getter del nome dello stato emozionale.
     * @return La stringa che rappresenta il nome dello stato emozionale
     */
    public String getNome() {
        return nome;
    }

    /**
     * Setter del nome dello stato emozionale.
     * @param nome La stringa che rappresenta il nome associato allo stato emozionale
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Getter della descrizione dello stato emozionale.
     * @return La stringa che rappresenta la descrizione dello stato emozionale
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Setter dell'ID dello stato emozionale.
     * @param descrizione La stringa che rappresenta la descrizione associata allo stato emozionale
     */
    public void setDescrizione(String descrizione) {
        this.descrizione=descrizione;
    }

}