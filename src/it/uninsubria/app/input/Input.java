package it.uninsubria.app.input;

import it.uninsubria.app.input.exceptions.InputException;
import it.uninsubria.app.users.User;
import it.uninsubria.app.users.exceptions.UserException;
import it.uninsubria.app.users.utils.TypeStreet;
import it.uninsubria.app.views.Display;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Classe che gestisce il sistema di input sulla console dell'applicazione
 * @author  Erik Gurzau
 * @author  Alessia Metaj
 * @author  Sara Biavaschi
 * @version 1.0.0
 * @see     it.uninsubria.app.users.User
 * @see     it.uninsubria.app.users.utils.TypeStreet
 * @see     it.uninsubria.app.users.exceptions.UserException
 * @see     it.uninsubria.app.input.exceptions.InputException
 */
public class Input {
    /**
     * Scanner per gli input sulla console
     */
    Scanner sc;

    /**
     * Costruttore di un Input
     */
    public Input() {
        sc = new Scanner(System.in);
    }

    /**
     * Legge dalla console l'email dell'utente ed esegue il controllo sulla validità
     * @param message Stringa che contiene il messaggio per l'input dell'utente
     * @return Stringa che contiene l'email dell'utente
     */
    public String readEmail(String message) {
        try {
            System.out.print(message);
            String email = sc.next();
            User.isEmailValid(email);
            return email;
        } catch (UserException e) {
            Display.printError(e.getMessage() + "\n");
            return readEmail(message);
        }
    }

    /**
     * Legge dalla console la password dell'utente ed esegue il controllo sulla validità
     * @param message Stringa che contiene il messaggio per l'input dell'utente
     * @return Stringa che contiene la password dell'utente
     */
    public String readPassword(String message) {
        try {
            System.out.print(message);
            String psw = sc.next();
            User.isPswValid(psw);
            return psw;
        } catch (UserException e) {
            Display.printError(e.getMessage() + "\n");
            return readPassword(message);
        }
    }

    /**
     * Legge dalla console 'yes' / 'y' / 'no' / 'n' e restituisce il carattere
     * corrispondente all'input dell'utente
     * @param message Stringa che contiene il messaggio per l'input dell'utente
     * @return Carattere 'y' se l'utente ha inserito 'yes' o 'n'. Invece
     * 'n' se l'utente ha inserito 'no' o 'n'
     */
    public char readYesNo(String message) {
        try {
            Display.printInfo(message);
            String risp = sc.next();
            char c = risp.charAt(0);

            if (risp.length() > 1 && !risp.equalsIgnoreCase("yes") & !risp.equalsIgnoreCase("no"))
                throw new InputException("Errore, inserisci una parola tra 'yes' o 'no'\n");
            if (!Character.isLetter(c))
                throw new InputException("Errore, inserisci una lettera tra 'y' o 'n'\n");
            if (c != 'y' & c != 'Y' & c != 'n' & c != 'N')
                throw new InputException("Errore, inserisci una lettera tra 'y' o 'n'\n");

            return c;
        } catch (StringIndexOutOfBoundsException | InputException e) {
            Display.printError(e.getMessage());
            return readYesNo(message);
        }
    }


    /**
     * Legge dalla console il codice fiscale dell'utente ed esegue il controllo sulla validità
     * @param message Stringa che contiene il messaggio per l'input dell'utente
     * @return Stringa contenente il codice fiscale dell'utente
     */
    public String readCF( String message) {
        try {
            System.out.print(message);
            String cf = sc.next();
            User.isCFValid(cf);
            return cf;
        } catch (UserException e) {
            Display.printError(e.getMessage());
            return readCF(message);
        }
    }

    /**
     * Legge dalla console il tipo di strada inserito dall'utente
     * @param message Stringa che contiene il messaggio per l'input dell'utente
     * @return Enum del tipo di strada corrispondente a quello scelto dall'utente
     */
    public TypeStreet readTypeStreet( String message) {
        try {
            System.out.print(message);
            return TypeStreet.decode(sc.next());
        } catch (InputException e){
            Display.printError(e.getMessage());
            return readTypeStreet(message);
        }
    }

    /**
     * Legge dalla console una stringa non nulla, ovvero non deve avere lunghezza uguale a 0
     * @param message Stringa che contiene il messaggio per l'input dell'utente
     * @return Stringa inserita dall'utente
     */
    public String readString(String message) {
        try {
            System.out.print(message);
            String str = sc.next();

            if (str.length() == 0)
                throw new InputException("Errore, non è possibile inserire una stringa vuota");

            return str;
        } catch (InputException e) {
            Display.printError(e.getMessage());
            return readString(message);
        }
    }

    /**
     * Legge dalla console una stringa che può essere vuota in base al
     * boolean canBeEmpty. Se {@code = true} allora la stringa inserita
     * dall'utente può essere vuota. Altrimenti deve contenere almeno un carattere
     * @param message Stringa che contiene il messaggio per l'input dell'utente
     * @param canBeEmpty Booleano per abilitare il controllo sulla lunghezza della stringa di input
     * @return Stringa inserita dall'utente
     */
    public String readString(String message, boolean canBeEmpty) {
        if (canBeEmpty) {
            System.out.print(message);
            String str = sc.next();
            return str;
        }
        else return readString(message);

    }

    /**
     * Legge dalla console una stringa che deve necessariamente avere una lunghezza
     * pari o superiore al parametro minLength ed avere una lunghezza pari o inferiore
     * al parametro maxLength
     * @param message Stringa che contiene il messaggio per l'input dell'utente
     * @param minLength Intero che rappresenta la lunghezza minima della stringa
     * @param maxLength Intero che rappresenta la lunghezza massima della stringa
     * @return Stringa inserita dall'utente
     */
    public String readString(String message, int minLength, int maxLength) {
        try {
            minLength = Math.min(minLength, maxLength);
            maxLength = Math.max(minLength, maxLength);

            System.out.print(message);
            String str = sc.next();

            if (str.length() == 0)
                throw new InputException("Errore, non è possibile inserire una stringa vuota" + "\n");
            if (str.length() < minLength || str.length() > maxLength)
                throw new InputException("Errore, la stringa deve essere lunga nel range " + minLength + " - " + maxLength + "\n");

            return str;
        } catch (InputException e) {
            Display.printError(e.getMessage());
            return readString(message, minLength, maxLength);
        }
    }

    /**
     * Legge dalla console una stringa che deve avere necessariamente lunghezza N
     * @param message Stringa che contiene il messaggio per l'input dell'utente
     * @param N Intero che rappresenta la lunghezza minima e massima della stringa
     * @return Stringa inserita dall'utente
     */
    public String readString(String message, int N) {
        return readString(message, N, N);
    }

    /**
     * Legge dalla console un numero intero
     * @param message Stringa che contiene il messaggio per l'input dell'utente
     * @return Intero inserito dall'utente
     */
    public int readInteger(String message) {
        try {
            System.out.print(message);
            return Integer.parseInt(sc.next());
        } catch (NumberFormatException | InputMismatchException e) {
            Display.printError("Errore, inserisci un numero per continuare\n");
            return readInteger(message);
        }
    }

    /**
     * Legge dalla console il tasto 'Invio'
     * @param message Stringa che contiene il messaggio per l'input dell'utente
     */
    public void readEnter(String message) {
        sc.useDelimiter("\n");
        readString(message, true);
        sc = sc.reset();
    }



}
