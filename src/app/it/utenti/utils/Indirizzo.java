package app.it.utenti.utils;

public class Indirizzo {
    private Strade strada;
    private int num_civico;
    private String cap;
    private String comune, provincia;

    public Indirizzo(Strade strada, int num_civico, String cap, String comune, String provincia) {
        this.strada = strada;
        this.num_civico = num_civico;
        this.cap = cap;
        this.comune = comune;
        this.provincia = provincia;
    }

    public Strade getStrada() {
        return strada;
    }

    public int getNum_civico() {
        return num_civico;
    }

    public String getCap() {
        return cap;
    }

    public String getComune() {
        return comune;
    }

    public String getProvincia() {
        return provincia;
    }
}
