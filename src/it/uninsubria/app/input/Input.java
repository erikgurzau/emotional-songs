package it.uninsubria.app.input;

import it.uninsubria.app.input.exceptions.InputException;
import it.uninsubria.app.users.User;
import it.uninsubria.app.users.exceptions.UserException;
import it.uninsubria.app.users.utils.TypeStreet;
import it.uninsubria.app.views.Display;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {
    Scanner sc;

    public Input() {
        sc = new Scanner(System.in);
        sc.useDelimiter("\n");
    }

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

    public TypeStreet readTypeStreet( String message) {
        try {
            System.out.print(message);
            return TypeStreet.decode(sc.next());
        } catch (InputException e){
            Display.printError(e.getMessage());
            return readTypeStreet(message);
        }
    }

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

    public String readString(String message, boolean canBeEmpty) {
        if (canBeEmpty) {
            System.out.print(message);
            String str = sc.next();
            return str;
        }
        else return readString(message);

    }

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

    public String readString(String message, int length) {
        return readString(message, length, length);
    }

    public int readInteger(String message) {
        try {
            System.out.print(message);
            return Integer.parseInt(sc.next());
        } catch (NumberFormatException | InputMismatchException e) {
            Display.printError("Errore, inserisci un numero per continuare\n");
            return readInteger(message);
        }
    }




}
