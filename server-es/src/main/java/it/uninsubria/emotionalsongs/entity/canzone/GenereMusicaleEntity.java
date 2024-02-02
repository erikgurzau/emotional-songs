package it.uninsubria.emotionalsongs.entity.canzone;

/**
 * Questa classe è responsabile della rappresentazione dell'entità relativa ad un genere musicale.
 * @author Erik Gurzau (749400, VA)
 * @author Alessia Metaj (738945, VA)
 * @author Sara Biavaschi (748698, VA)
 * @version 2.0.0
 */
public class GenereMusicaleEntity {

    /**
     * L'ID del genere musicale.
     */
    private Integer id;

    /**
     * Il nome del genere musicale.
     */
    private String nome;

    /**
     * Costruttore di default della classe.
     */
    public GenereMusicaleEntity() { }

    /**
     * Costruttore con parametri della classe.
     * @param id L'ID del genere musicale
     * @param nome Il nome del genere musicale
     */
    public GenereMusicaleEntity(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    /**
     * Getter dell'ID del genere musicale.
     * @return L'intero che rappresenta l'ID associato al genere musicale
     */
    public Integer getId() {
        return id;
    }

    /**
     * Setter dell'ID del genere musicale.
     * @param id L'intero che rappresenta l'ID del genere musicale
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Getter del nome del genere musicale.
     * @return La stringa che rappresenta il nome del genere musicale
     */
    public String getNome() {
        return nome;
    }

    /**
     * Setter del nome del genere musicale.
     * @param nome La stringa che rappresenta il nome del genere musicale
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

}