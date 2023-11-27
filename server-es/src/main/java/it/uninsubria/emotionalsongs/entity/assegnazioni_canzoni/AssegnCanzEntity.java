package it.uninsubria.emotionalsongs.entity.assegnazioni_canzoni;

import java.util.List;

public class AssegnCanzEntity {

    private Integer id;
    private Integer idPlaylist;
    private List<Integer> idCanzone;

    public AssegnCanzEntity() { }

    public AssegnCanzEntity(Integer id, Integer idPlaylist, List<Integer> idCanzone) {
        this.id = id;
        this.idPlaylist = idPlaylist;
        this.idCanzone = idCanzone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdPlaylist() { return idPlaylist; }

    public void setIdPlaylist(Integer idPlaylist) {
        this.idPlaylist = idPlaylist;
    }

    public List<Integer> getIdCanzone() {
        return idCanzone;
    }

    public void setIdCanzone(List<Integer> idCanzone) { this.idCanzone = idCanzone; }

}
