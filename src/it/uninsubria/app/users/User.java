package it.uninsubria.app.users;

import it.uninsubria.app.users.utils.Address;

/**
 * Classe che definisce un utente dell'applicazione
 * @author  Erik Gurzau
 * @author  Alessia Metaj
 * @author  Sara Biavaschi
 * @version 1.0.0
 * @see     it.uninsubria.app.users.Person
 * @see     it.uninsubria.app.users.utils.Address
 * @see     it.uninsubria.app.controllers.utils.SecurePassword
 * @see     it.uninsubria.app.users.exceptions.UserException
 */
public class User extends Person {
    /**
     * Stringa che identifica in modo univoco ogni utente
     */
    private final String userId;
    
    /**
     * Email dell'utente
     */
    private final String email;
    
    /**
     * Password dell'utente
     */
    private final String psw;

    /**
     * Array dei possibili domini email consentiti
     */
    private final String[] domains = { "@gmail.com", "@outlook.com", "@icloud.com", "@yahoo.com" };

    /**
     * Lunghezza minima di caratteri per la password
     */
    private final int MIN_LENGTH_PSW = 8;

    /**
     * Lunghezza massima di caratteri per la password
     */
    private final int MAX_LENGTH_PSW = 16;

    /**
     * Costruttore di un utente
     * @param name Stringa che contiene il nome dell'utente
     * @param surname Stringa che contiene il cognome dell'utente
     * @param cf Stringa che contiene il codice fiscale dell'utente, composta da obbligatoriamente 16 caratteri
     * @param address Indirizzo dell'utente
     * @param userId Stringa che contiene l'ID dell'utente
     * @param email Stringa che contiene l'email dell'utente
     * @param psw Stringa che contiene la password
     */
    public User(String name, String surname, String cf, Address address, String userId, String email, String psw) {
        super(name, surname, cf, address);
        this.userId = userId;
        this.email = email;
        this.psw = psw;
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

    public boolean login(String email, String psw) {
        return this.email.equals(email) && this.psw.equals(psw);
    }


    @Override
    public String toString() {
        return userId + ";" + email + ";" + psw + ";" + super.toString();
    }
}
