package it.uninsubria.emotionalsongs.entity.assegnazioni_canzoni;

import java.util.List;

public class AssegnCanzEntity {

    private Integer id;
    private Integer id_playlist;
    private List<Integer> id_canzone;

    public AssegnCanzEntity() { }

    public AssegnCanzEntity(Integer id, Integer id_playlist, List<Integer> id_canzone) {
        this.id = id;
        this.id_playlist = id_playlist;
        this.id_canzone = id_canzone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdPlaylist() { return id_playlist; }

    public void setIdPlaylist(Integer id_playlist) {
        this.id_playlist = id_playlist;
    }

    public List<Integer> getIdCanzone() {
        return id_canzone;
    }

    public void setIdCanzone(List<Integer> id_canzone) { this.id_canzone = id_canzone; }

}
