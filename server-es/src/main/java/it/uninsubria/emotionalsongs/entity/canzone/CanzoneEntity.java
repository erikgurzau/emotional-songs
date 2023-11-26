package it.uninsubria.emotionalsongs.entity.canzone;

public class CanzoneEntity {

    private Integer id;
    private String autore;
    private String titolo;
    private Integer anno;
    private GenereMusicaleEntity genereMusicaleEntity;
    private Long durata_ms;

    public CanzoneEntity() {
        genereMusicaleEntity = new GenereMusicaleEntity();
    }

    public CanzoneEntity(Integer id, String autore, String titolo, Integer anno, GenereMusicaleEntity genereMusicaleEntity, Long durata_ms) {
        this.id = id;
        this.autore = autore;
        this.titolo = titolo;
        this.anno = anno;
        this.genereMusicaleEntity = genereMusicaleEntity;
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

    public GenereMusicaleEntity getGenereMusicaleEntity() {
        return genereMusicaleEntity;
    }

    public void setGenereMusicaleEntity(GenereMusicaleEntity genereMusicaleEntity) {
        this.genereMusicaleEntity = genereMusicaleEntity;
    }

    public Long getDurataMs() {
        return durata_ms;
    }

    public void setDurataMs(Long durata_ms) {
        this.durata_ms = durata_ms;
    }

}
