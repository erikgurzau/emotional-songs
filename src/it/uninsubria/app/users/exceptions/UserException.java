package it.uninsubria.app.users.exceptions;

/**
 * Classe che definisce una eccezione riguardante la sezione utente
 * @author  Erik Gurzau
 * @author  Alessia Metaj
 * @author  Sara Biavaschi
 * @version 1.0.0
 */
public class UserException extends Exception {

    /**
     * Costruttore di una eccezione utente
     * @param msg Stringa contenente il messaggio che genera l'eccezzione
     */
    public UserException(String msg){
        super(msg);
    }
}
