package it.uninsubria.entity.canzone;

public class CanzoneEntity {

    private Integer id;
    private String autore;
    private String titolo;
    private Integer anno;
    private Integer genere_musicale_id;
    private String genere_musicale_nome;
    private Long durata_ms;

    public CanzoneEntity() { }

    public CanzoneEntity(Integer id, String autore, String titolo, Integer anno, Integer genere_musicale_id, String genere_musicale_nome, Long durata_ms) {
        this.id = id;
        this.autore = autore;
        this.titolo = titolo;
        this.anno = anno;
        this.genere_musicale_id = genere_musicale_id;
        this.genere_musicale_nome = genere_musicale_nome;
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

    public Integer getGenereMusicaleId() {
        return genere_musicale_id;
    }

    public void setGenereMusicaleId(Integer genere_musicale_id) {
        this.genere_musicale_id = genere_musicale_id;
    }

    public String getGenereMusicaleNome() {
        return genere_musicale_nome;
    }

    public void setGenereMusicaleNome(String genere_musicale_nome) {
        this.genere_musicale_nome = genere_musicale_nome;
    }

    public Long getDurataMs() {
        return durata_ms;
    }

    public void setDurataMs(Long durata_ms) {
        this.durata_ms = durata_ms;
    }
}
