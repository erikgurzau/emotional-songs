package it.uninsubria.app.users;

import it.uninsubria.app.users.utils.Address;

/**
 * Classe che definisce l'
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
     * Password del'utente
     */
    private final String psw;

    private final String[] domains = { "@gmail.com", "@outlook.com", "@icloud.com", "@yahoo.com" };
    
    private final int MIN_LENGTG_PSW = 8;
    
    private final int MAX_LENGTG_PSW = 16;

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

    public boolean checkLoginInfo(String email, String cryptPsw) {
        return this.email.equals(email) && psw.equals(cryptPsw);
    }


    @Override
    public String toString() {
        return userId + ";" + email + ";" + psw + ";" + super.toString();
    }
}
