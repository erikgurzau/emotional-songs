package it.uninsubria.entity.canzone;

public class GenereMusicaleEntity {

    private Integer id;
    private String nome;

    public GenereMusicaleEntity() { }

    public GenereMusicaleEntity(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String toString() {
        return String.join(",",
                getId().toString(), getNome()
        );
    }
}
