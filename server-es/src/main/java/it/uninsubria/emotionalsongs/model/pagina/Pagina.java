package it.uninsubria.emotionalsongs.model.pagina;

import java.io.Serializable;
import java.util.List;

public class Pagina<T> implements Serializable {

    private Integer numeroPagina;
    private Integer dimensionePagina;
    private Integer totale;
    private List<T> data;

    public Pagina() { }

    public Pagina(Integer numeroPagina, Integer dimensionePagina, Integer totale, List<T> data) {
        this.numeroPagina = numeroPagina;
        this.dimensionePagina = dimensionePagina;
        this.totale = totale;
        this.data = data;
    }

    public Integer getNumeroPagina() {
        return numeroPagina;
    }

    public void setNumeroPagina(Integer numeroPagina) {
        this.numeroPagina = numeroPagina;
    }

    public Integer getDimensionePagina() {
        return dimensionePagina;
    }

    public void setDimensionePagina(Integer dimensionePagina) {
        this.dimensionePagina = dimensionePagina;
    }

    public Integer getTotale() {
        return totale;
    }

    public void setTotale(Integer totale) {
        this.totale = totale;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
