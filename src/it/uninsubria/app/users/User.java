package it.uninsubria.app.users;

import it.uninsubria.app.controllers.utils.FileManager;
import it.uninsubria.app.songs.Song;
import it.uninsubria.app.users.utils.Address;
import it.uninsubria.app.users.exceptions.UserException;


import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
     * ID che identifica in modo univoco ogni utente
     */
    private final int userId;

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
    private final String[] domains = { "@gmail.com", "@outlook.com", "@icloud.com", "@yahoo.com", "@libero.it", "hotmail.it" , "outlook.it"};

    /**
     * Lunghezza minima di caratteri per la password
     */
    private static final int MIN_LENGTH_PSW = 8;

    /**
     * Lunghezza massima di caratteri per la password
     */
    private static final int MAX_LENGTH_PSW = 16;

    /**
     * Costruttore di un utente
     * @param name Stringa che contiene il nome dell'utente
     * @param surname Stringa che contiene il cognome dell'utente
     * @param cf Stringa che contiene il codice fiscale dell'utente, composta da obbligatoriamente 16 caratteri
     * @param address Indirizzo dell'utente
     * @param userId Stringa che contiene l'ID univoco dell'utente
     * @param email Stringa che contiene l'email dell'utente
     * @param psw Stringa che contiene la password
     */
    public User(String name, String surname, String cf, Address address, int userId, String email, String psw) {
        super(name, surname, cf, address);
        this.userId = userId;
        this.email = email;
        this.psw = psw;
    }

    public int getUserId() {
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

    public static boolean isPswPatternValid(String psw) throws UserException {
        if(psw==null|psw.length()<MIN_LENGTH_PSW|psw.length()>MAX_LENGTH_PSW) throw new UserException("La password non rispetta i requisiti di lunghezza.");
        int countcs=0, countm=0, countn=0;   //contatori: cs=caratteri speciali; n=numeri; m=maiuscole
        String caratterispeciali= "!#$%&'()*+,-./:;<=>?@[]^_`{|}~";
        String numeri = "0123456789";
        String array[] = psw.split("");
        for(int i=0; i<psw.length();i++) {
            if(caratterispeciali.contains(array[i]))
                countcs++;
            if(numeri.contains(array[i]))
                countn++;
            if(Character.isUpperCase(psw.charAt(i)))
                countm++;
        }
        if(countcs>0 & countn>0 & countm>0)
            return true;
        else
            throw new UserException("La password deve contenere almeno un carattere speciale, un numero e una lettera maiuscola.");
    }

    public static boolean isEmailPatternValid(String email) throws UserException {
        Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        boolean matchFound = matcher.matches();

        String array[] = email.split("@");
        for(int i=0; i<array[0].length();i++) {
            if (Character.isDigit(email.charAt(i)))
                throw new UserException("L'email non può essere composta solamente da numeri.");
        }
        if(Character.isDigit(email.charAt(0)))
            throw new UserException("L'email non può iniziare con un numero.");

        if(matchFound)
            return true;
        else
            throw new UserException("L'email inserita non è valida");
    }

    //prova dei metodi isPswPatternValid e isEmailPatternValid
    public static void main(String[] args) throws UserException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Inserire password: ");
        String psw = scanner.nextLine();
        isPswPatternValid(psw);

        System.out.println("Inserire email: ");
        String email = scanner.nextLine();
        isEmailPatternValid(email);

    }

    @Override
    public String toString() {
        return userId + ";" + email + ";" + psw + ";" + super.toString();
    }

}
