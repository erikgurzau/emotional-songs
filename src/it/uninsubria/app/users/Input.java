package it.uninsubria.app.users;

import it.uninsubria.app.views.Display;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {

    public static int readOption(Scanner sc, String message){
        try {
            System.out.print(message);
            return Integer.parseInt(sc.next());
        } catch (NumberFormatException | InputMismatchException e){
            Display.printError("Errore, inserisci un numero per continuare");
            return readOption(sc, message);
        }
    }
}
