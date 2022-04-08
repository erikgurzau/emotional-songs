package app.it.utenti;

import app.it.utenti.utils.Indirizzo;

public class Utente extends Persona{
   private String userId;
   private String psw;
   private String email;

    public Utente(String nome, String cognome, String cf, Indirizzo indirizzo, String userId, String psw, String email) {
        super(nome, cognome, cf, indirizzo);
        this.userId = userId;
        this.psw = psw;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public String getPsw() {
        return psw;
    }

    public String getEmail() {
        return email;
    }



}
