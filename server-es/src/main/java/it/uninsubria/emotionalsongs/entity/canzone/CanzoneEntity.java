package it.uninsubria.emotionalsongs.entity.canzone;

/**
 * Questa classe è responsabile della rappresentazione dell'entità relativa ad una canzone.
 * @author Erik Gurzau (749400, VA)
 * @author Alessia Metaj (738945, VA)
 * @author Sara Biavaschi (748698, VA)
 * @version 2.0.0
 */
public class CanzoneEntity {

    /**
     * L'ID della canzone.
     */
    private Integer id;

    /**
     * L'autore della canzone.
     */
    private String autore;

    /**
     * Il titolo della canzone.
     */
    private String titolo;

    /**
     * L'anno di pubblicazione della canzone.
     */
    private Integer anno;

    /**
     * Il genere musicale della canzone.
     */
    private GenereMusicaleEntity genereMusicaleEntity;

    /**
     * La durata della canzone espressa in millisecondi.
     */
    private Long durata_ms;

    /**
     * Costruttore di default della classe.
     */
    public CanzoneEntity() {
        genereMusicaleEntity = new GenereMusicaleEntity();
    }

    /**
     * Costruttore con parametri della classe.
     * @param id L'ID della canzone
     * @param autore L'autore della canzone
     * @param titolo Il titolo della canzone
     * @param anno L'anno di pubblicazione della canzone
     * @param genereMusicaleEntity Il genere musicale della canzone
     * @param durata_ms La durata in millisecondi della canzone
     */
    public CanzoneEntity(Integer id, String autore, String titolo, Integer anno, GenereMusicaleEntity genereMusicaleEntity, Long durata_ms) {
        this.id = id;
        this.autore = autore;
        this.titolo = titolo;
        this.anno = anno;
        this.genereMusicaleEntity = genereMusicaleEntity;
        this.durata_ms = durata_ms;
    }

    /**
     * Getter dell'ID della canzone.
     * @return L'intero che rappresenta l'ID associato alla canzone
     */
    public Integer getId() {
        return id;
    }

    /**
     * Setter dell'ID della canzone.
     * @param id L'intero che rappresenta l'ID della canzone
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Getter dell'autore della canzone.
     * @return La stringa che rappresenta il nome dell'autore
     */
    public String getAutore() {
        return autore;
    }

    /**
     * Setter dell'autore della canzone.
     * @param autore La stringa che rappresenta il nome dell'autore
     */
    public void setAutore(String autore) {
        this.autore = autore;
    }

    /**
     * Getter del titolo della canzone.
     * @return La stringa che rappresenta il titolo della canzone
     */
    public String getTitolo() {
        return titolo;
    }

    /**
     * Setter del titolo della canzone.
     * @param titolo La stringa che rappresenta il titolo
     */
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    /**
     * Getter dell'anno di pubblicazione della canzone.
     * @return L'intero che rappresenta l'anno di pubblicazione
     */
    public Integer getAnno() {
        return anno;
    }

    /**
     * Setter dell'anno di pubblicazione della canzone.
     * @param anno L'intero che rappresenta l'anno di pubblicazione
     */
    public void setAnno(Integer anno) {
        this.anno = anno;
    }

    /**
     * Getter dell'ID del genere musicale della canzone.
     * @return L'intero che rappresenta l'ID del genere musicale
     */
    public Integer getIdGenereMusicale() {
        return genereMusicaleEntity.getId();
    }

    /**
     * Setter dell'ID del genere musicale della canzone.
     * @param id L'intero che rappresenta l'ID del genere musicale
     */
    public void setIdGenereMusicale(Integer id) {
        genereMusicaleEntity.setId(id);
    }

    /**
     * Getter del genere musicale della canzone.
     * @return La stringa che rappresenta il genere musicale
     */
    public String getNomeGenereMusicale() {
        return genereMusicaleEntity.getNome();
    }

    /**
     * Setter del genere musicale della canzone.
     * @param nome La stringa che rappresenta il nome del genere musicale
     */
    public void setNomeGenereMusicale(String nome) {
        genereMusicaleEntity.setNome(nome);
    }

    /**
     * Getter della durata in millisecondi della canzone.
     * @return Long che rappresenta la durata in millisecondi
     */
    public Long getDurataMs() {
        return durata_ms;
    }

    /**
     * Setter della durata in millisecondi della canzone.
     * @param durata_ms Long che rappresenta la durata in millisecondi
     */
    public void setDurataMs(Long durata_ms) {
        this.durata_ms = durata_ms;
    }

}