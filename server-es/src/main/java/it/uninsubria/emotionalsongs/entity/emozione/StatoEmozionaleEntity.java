package it.uninsubria.emotionalsongs.entity.emozione;

public class StatoEmozionaleEntity {
  
    private Integer id;

    private String nome;

    private String descrizione;

    public StatoEmozionaleEntity(){

    }
    public StatoEmozionaleEntity(Integer id, String nome, String descrizione){
        this.id=id;
        this.nome=nome;
        this.descrizione=descrizione;
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id=id;
    }



}
