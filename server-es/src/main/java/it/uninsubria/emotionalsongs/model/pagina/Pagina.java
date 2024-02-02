package it.uninsubria.emotionalsongs.model.pagina;

import java.io.Serializable;
import java.util.List;

/**
 * Questa classe è responsabile della gestione della visualizzazione di liste di oggetti in pagine di dimensione predefinita.
 * @author Erik Gurzau (749400, VA)
 * @author Alessia Metaj (738945, VA)
 * @author Sara Biavaschi (748698, VA)
 * @version 2.0.0
 */
public class Pagina<T> implements Serializable {

    /**
     * Il numero della pagina.
     */
    private Integer numeroPagina;

    /**
     * La dimensione della pagina.
     */
    private Integer dimensionePagina;

    /**
     * Il numero totale di oggetti della lista.
     */
    private Integer totale;

    /**
     * La lista di oggetti da visualizzare.
     */
    private List<T> data;

    /**
     * Costruttore di default della classe.
     */
    public Pagina() { }

    /**
     * Costruttore con parametri della classe.
     * @param numeroPagina Il numero della pagina
     * @param dimensionePagina La dimensione della pagina
     * @param totale Il numero totale di oggetti della lista
     * @param data La lista di oggetti da visualizzare
     */
    public Pagina(Integer numeroPagina, Integer dimensionePagina, Integer totale, List<T> data) {
        this.numeroPagina = numeroPagina;
        this.dimensionePagina = dimensionePagina;
        this.totale = totale;
        this.data = data;
    }

    /**
     * Getter del numero di pagina.
     * @return L'intero che rappresenta il numero di pagina
     */
    public Integer getNumeroPagina() {
        return numeroPagina;
    }

    /**
     * Setter del numero di pagina
     * @param numeroPagina L'intero che rappresenta il numero di pagina
     */
    public void setNumeroPagina(Integer numeroPagina) {
        this.numeroPagina = numeroPagina;
    }

    /**
     * Getter della dimensione della pagina.
     * @return L'intero che rappresenta il numero di pagina
     */
    public Integer getDimensionePagina() {
        return dimensionePagina;
    }

    /**
     * Setter del numero di pagina.
     * @param dimensionePagina L'intero che rappresenta la dimensione della pagina
     */
    public void setDimensionePagina(Integer dimensionePagina) {
        this.dimensionePagina = dimensionePagina;
    }

    /**
     * Getter del numero totale di oggetti della lista.
     * @return L'intero che rappresenta il totale
     */
    public Integer getTotale() {
        return totale;
    }

    /**
     * Setter del numero totale di oggetti della lista.
     * @param totale L'intero che rappresenta il totale
     */
    public void setTotale(Integer totale) {
        this.totale = totale;
    }

    /**
     * Getter della lista di oggetti da visualizzare.
     * @return La lista degli oggetti
     */
    public List<T> getData() {
        return data;
    }

    /**
     * Setter della lista di oggetti da visualizzare.
     * @param data La lista degli oggetti
     */
    public void setData(List<T> data) {
        this.data = data;
    }

}