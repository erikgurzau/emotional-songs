package it.uninsubria.app.input;

import it.uninsubria.app.input.exceptions.InputException;
import it.uninsubria.app.users.User;
import it.uninsubria.app.users.exceptions.TypeStreetException;
import it.uninsubria.app.users.exceptions.UserException;
import it.uninsubria.app.users.utils.Address;
import it.uninsubria.app.users.utils.TypeStreet;
import it.uninsubria.app.views.Display;
import it.uninsubria.app.views.utils.DisplayColors;

import java.lang.reflect.Type;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {



    public static String readEmail(Scanner sc, String message) {
        try {
            System.out.print(message);
            String email = sc.next();
            User.isEmailValid(email);
            return email;
        } catch (UserException e) {
            Display.printError(e.getMessage() + "\n");
            return readEmail(sc, message);
        }
    }

    public static String readPassword(Scanner sc, String message) {
        try {
            System.out.print(message);
            String psw = sc.next();
            User.isPswValid(psw);
            return psw;
        } catch (UserException e) {
            Display.printError(e.getMessage() + "\n");
            return readPassword(sc, message);
        }
    }

    public static char readYesNo(Scanner sc, String message, boolean initEndOfLine) {
        try {
            Display.printInfo(message, initEndOfLine);
            String risp = sc.next();
            char c = risp.charAt(0);

            if (risp.length() > 1 && !risp.equalsIgnoreCase("yes") & !risp.equalsIgnoreCase("no"))
                throw new InputException("Errore, inserisci una parola tra 'yes' o 'no'");
            if (!Character.isLetter(c))
                throw new InputException("Errore, inserisci una lettera tra 'y' o 'n'");
            if (c != 'y' & c != 'Y' & c != 'n' & c != 'N')
                throw new InputException("Errore, inserisci una lettera tra 'y' o 'n'");

            return c;
        } catch (InputException e) {
            Display.printError(e.getMessage());
            return readYesNo(sc, message, initEndOfLine);
        }
    }

    public static String readName(Scanner sc, String message) {
        try {
            System.out.print(message);
            String name = sc.next();
            User.isNameValid(name);
            return name;
        } catch (UserException e) {
            Display.printError(e.getMessage() + "\n");
            return readName(sc, message);
        }
    }

    public static String readSurname(Scanner sc, String message) {
        try {
            System.out.print(message);
            String surname = sc.next();
            User.isSurnameValid(surname);
            return surname;
        } catch (UserException e) {
            Display.printError(e.getMessage() + "\n");
            return readSurname(sc, message);
        }
    }

    public static String readCF(Scanner sc, String message) {
        try {
            System.out.print(message);
            String cf = sc.next();
            User.isCFValid(cf);
            return cf;
        } catch (UserException e) {
            Display.printError(e.getMessage() + "\n");
            return readCF(sc, message);
        }
    }

    public static TypeStreet readTypeStreet(Scanner sc, String message) {
        try {
            System.out.print(message);
            return TypeStreet.decode(sc.next());
        } catch (TypeStreetException e){
            Display.printError(e.getMessage());
            return readTypeStreet(sc, message);
        }
    }

    public static String readString(Scanner sc, String message) {
        try {
            System.out.print(message);
            return sc.next();
        } catch (InputMismatchException e) {
            Display.printError(e.getMessage());
            return readString(sc, message);
        }
    }

    public static int readInteger(Scanner sc, String message) {
        try {
            System.out.print(message);
            return Integer.parseInt(sc.next());
        } catch (NumberFormatException | InputMismatchException e) {
            Display.printError("Errore, inserisci un numero per continuare");
            return readInteger(sc, message);
        }
    }




}
