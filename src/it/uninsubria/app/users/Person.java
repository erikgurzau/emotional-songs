package it.uninsubria.app.users;


import it.uninsubria.app.users.exceptions.UserException;
import it.uninsubria.app.users.utils.Address;

import java.util.regex.Pattern;

/**
 * Classe che definisce una persona, prima che diventi un utente dell'applicazione
 * @author  Erik Gurzau
 * @author  Alessia Metaj
 * @author  Sara Biavaschi
 * @version 1.0.0
 * @see     it.uninsubria.app.users.utils.Address
 */
public class Person {
    /**
     * Nome della persona
     */
    private String name;

    /**
     * Cognome della persona
     */
    private String surname;

    /**
     * Codice fiscale
     */
    private String cf;

    /**
     * Indirizzo di residenza/domicilio
     */
    private Address address;

    /**
     * Numero massimo e minimo di caratteri alfanumerici contenuti nel codice fiscale
     */
    public static final byte LENGTH_CF = 16;


    /**
     * Costruttore di una persona
     * @param name Stringa contenente il nome della persona
     * @param surname Stringa contenente il cognome della persona
     * @param cf Stringa contenente il codice fiscale della persona
     * @param address Indirizzo di residenza/domicilio della persona
     */
    public Person(String name, String surname, String cf, Address address) {
        this.name = name;
        this.surname = surname;
        this.cf = cf.toUpperCase();
        this.address = address;
    }

    /**
     * Getter del nome
     * @return Stringa con il nome
     */
    public String getName() {
        return name;
    }

    /**
     * Getter del cognome
     * @return Stringa con il cognome
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Getter del codice fiscale
     * @return Stringa con il codice fiscale
     */
    public String getCf() {
        return cf;
    }

    /**
     * Getter dell'indirizzo
     * @return Ritorna un oggetto contenti i dati dell'indirizzo
     */
    public Address getAddress() {
        return address;
    }


    /**
     * Verifica se il codice fiscale fornito è valido ad essere considerato tale:
     * Controlla se ci sono 1 caratteri alfanumerici;
     * Controlla se il codice fiscale corrisponde esattamente al seguente pattern:
     * 3 Lettere + 2 numeri + 1 lettera + 2 numeri + 1 lettera + 3 numeri + 1 lettera
     * @param cf Stringa con il codice fiscale da validare
     * @return {@code = true} Se e solo se, il codice fiscale rispetti tutti i parametri minimi di validità.
     * Altrimenti {@code = false}
     * @throws UserException Se il codice fiscale non è valido
     */
    public static boolean isCFValid(String cf) throws UserException {
        if (cf.length() != LENGTH_CF)
            throw new UserException("Il codice fiscale deve composto da 16 caratteri alfanumerici\n");

        cf = cf.toUpperCase();
        Pattern pattern = Pattern.compile("([a-zA-Z]+([0-9]+[a-zA-Z]+)+)");
        if (!pattern.matcher(cf).matches())
            throw new UserException("Il codice fiscale deve essere composto da 6 " +
                    "3 Lettere + 2 numeri + 1 lettera + 2 numeri + 1 lettera + 3 numeri + 1 lettera\n");

        return true;
    }


    /**
     * Ritorna una stringa che contiene le informazioni della persona
     * @return String che contiene i dati della persona divisi dal separatore ';'
     */
    public String toString() {
        return name + ";" + surname + ";" + cf + ";" + address.toString();
    }

}


