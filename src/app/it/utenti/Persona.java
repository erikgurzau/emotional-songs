package app.it.utenti;

import app.it.utenti.utils.Indirizzo;

public class Persona {
    private String nome, cognome;
    private String cf;
    private Indirizzo indirizzo;

    public Persona(String nome, String cognome, String cf, Indirizzo indirizzo) {
        this.nome = nome;
        this.cognome = cognome;
        this.cf = cf;
        this.indirizzo = indirizzo;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getCf() {
        return cf;
    }

    public Indirizzo getIndirizzo() {
        return indirizzo;
    }
}
