package it.uninsubria.app.input.exceptions;

/**
 * Classe che definisce una eccezione riguardante la sezione di input
 * @author  Erik Gurzau (749400, VA)
 * @author  Alessia Metaj (738945, VA)
 * @author  Sara Biavaschi (748698, VA)
 * @version 1.0.0
 */
public class InputException extends Exception {
    /**
     * Costruttore di una eccezione di input
     * @param msg Stringa che contiene il messaggio di eccezione
     */
    public InputException(String msg){
        super(msg);
    }
}
