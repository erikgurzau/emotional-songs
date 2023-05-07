package it.uninsubria.model.utente;

import java.io.Serializable;

public class Utente extends Persona implements Serializable {

    private int id;

    private String email;
    private String password;

    public Utente() {
        super();
    }

    public Utente(String nome, String cognome, String cod_fiscale, String indirizzo, String cap, String comune, String provincia, int id, String email, String password) {
        super(nome, cognome, cod_fiscale, indirizzo, cap, comune, provincia);
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
