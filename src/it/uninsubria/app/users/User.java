package it.uninsubria.app.users;

import it.uninsubria.app.managers.utils.SecurePassword;
import it.uninsubria.app.users.exceptions.UserException;
import it.uninsubria.app.users.interfaces.LimitUserFields;
import it.uninsubria.app.users.utils.Address;

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
 * @see     it.uninsubria.app.managers.utils.SecurePassword
 * @see     it.uninsubria.app.users.exceptions.UserException
 */
public class User extends Person implements LimitUserFields {
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
        this.psw = SecurePassword.encrypt(psw);
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


    public static boolean isPswValid(String psw) throws UserException {
        if(psw.length() < MIN_LENGTH_PSW || psw.length() > MAX_LENGTH_PSW)
            throw new UserException("La password non rispetta i requisiti di lunghezza");

        int countcs = 0, countm = 0, countn = 0;   //contatori: cs=caratteri speciali; n=numeri; m=maiuscole
        String caratterispeciali= "!#$%&'()*+,-./:;<=>?@[]^_`{|}~";
        String numeri = "0123456789";
        String[] array = psw.split("");

        for(int i = 0; i < psw.length(); i++) {
            if(caratterispeciali.contains(array[i]))
                countcs++;
            if(numeri.contains(array[i]))
                countn++;
            if(Character.isUpperCase(psw.charAt(i)))
                countm++;
        }

        if (countcs > 0) {
            if (countn > 0){
                if (countm > 0) {
                    return true;
                }
                else throw new UserException("La password non contiene lettere maiuscole, riprova");
            }
            else throw new UserException("La password non contiene numeri, riprova");
        }
        else throw new UserException("La password non contiene caratteri speciali, riprova");
    }

    public static boolean isEmailValid(String email) throws UserException {
        String[] array = email.split("@");
        Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);

        if (containsAllNumbers(array[0]))
            throw new UserException("L'email non può contenere solo numeri, riprova");
        if(Character.isDigit(email.charAt(0)))
            throw new UserException("L'email non può iniziare con un numero, riprova");
        if(!matcher.matches())
            throw new UserException("L'email inserita non è valida, riprova");

        return true;
    }

    public static boolean isNameValid(String name) throws UserException {
        if (name.length() < MIN_SURNAME_LENGTH || name.length() > MAX_NAME_LENGTH)
            throw new UserException("Nome invalido: deve essere compreso tra " + MIN_NAME_LENGTH + " e " + MAX_NAME_LENGTH);
        return true;
    }

    public static boolean isSurnameValid(String surname) throws UserException {
        if (surname.length() < MIN_SURNAME_LENGTH || surname.length() > MAX_SURNAME_LENGTH)
            throw new UserException("Cognome invalido: deve essere compreso tra " + MIN_SURNAME_LENGTH + " e " + MAX_SURNAME_LENGTH);
        return true;
    }

    public static boolean isCFValid(String cf) throws UserException {
        if (cf.length() != CF_LENGTH)
            throw new UserException("Il codice fiscale deve composto da 16 caratteri alfanumerici");

        cf = cf.toUpperCase();
        Pattern pattern = Pattern.compile("([a-zA-Z]+([0-9]+[a-zA-Z]+)+)");
        if (!pattern.matcher(cf).matches())
            throw new UserException("Il codice fiscale deve essere composto da 6 Lettere + 2 numeri + 1 lettera + 2 numeri + 1 lettera + 3 numeri + 1 lettera");

        return true;
    }

    private static boolean containsAllNumbers(String str){
        for (char c: str.toCharArray())
            if (Character.isLetter(c))
                return false;
        return true;
    }

    @Override
    public String toString() {
        return userId + ";" + email + ";" + psw + ";" + super.toString();
    }

}
