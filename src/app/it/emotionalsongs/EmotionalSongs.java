package app.it.emotionalsongs;

import app.it.utenti.Utente;
import app.it.utenti.utils.Indirizzo;
import app.it.utenti.utils.Strade;

public class EmotionalSongs {
    public static void main(String[] args) {
        Utente u;

        u = new Utente("Mario",
                "Rossi",
                "MRSRK322KKP",
                new Indirizzo(Strade.Via, 15, "20010", "Cangrate", "Milano"),
                "46512355",
                "123PASS",
                "mario.rossi@test.com");
    }
}
