package it.uninsubria.app.users.exceptions;

/**
 * Classe che definisce una eccezione riguardante la sezione utente
 * @author  Erik Gurzau (749400, VA)
 * @author  Alessia Metaj (738945, VA)
 * @author  Sara Biavaschi (748698, VA)
 * @version 1.0.0
 */
public class UserException extends Exception {

    /**
     * Costruttore di una eccezione utente
     * @param msg Stringa contenente il messaggio che genera l'eccezione
     */
    public UserException(String msg){
        super(msg);
    }
}
