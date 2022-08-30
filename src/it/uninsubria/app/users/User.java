package it.uninsubria.app.users;

import it.uninsubria.app.users.exceptions.UserException;
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
     * Lunghezza minima di caratteri della password
     */
    public static final byte MIN_LENGTH_PSW = 6;

    /**
     * Lunghezza massima di caratteri della password
     */
    public static final byte MAX_LENGTH_PSW = 16;



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

    /**
     * Getter dell'ID dell'utente
     * @return Intero che definisce l'ID dell'utente
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Getter della password dell'utente
     * La password non è in chiaro, ma criptata mediante
     * l'utilizzo di SHA1 che genera una stringa esadecimale di 40 caratteri
     * @return Password criptata lunga 40 caratteri
     */
    public String getPsw() {
        return psw;
    }

    /**
     * Getter dell'email dell'utente
     * @return Stringa contenente l'email
     */
    public String getEmail() {
        return email;
    }


    /**
     * Verifica che la password fornita corrisponda
     * esattamente alla password di questo utente
     * @param psw Password criptata da confrontare
     * @return {@code = true} Se e solo se, le due password sono uguali.
     * Altrimenti {@code = false}
     */
    public boolean comparePsw(String psw) {
        return this.psw.equals(psw);
    }


    /**
     * Verifica che la password fornita rispetti i requisiti minimi di validità:
     * Controlla se la password ha una lunghezza compresa tra MIN_LENGTH_PSW e MAX_LENGTH_PSW;
     * Controlla se è presente almeno un carattere maiuscolo;
     * Controlla se è presente almeno un numero;
     * Controlla se è presente almeno un carattere speciale;
     * @param psw Stringa da validare come password
     * @return {@code = true} Se e solo se, la stringa rispetta tutti i requisiti minimi di validità.
     * Altrimenti {@code = false}
     * @throws UserException Se la password non rispetta i requisiti minimi di validità
     */
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

    /**
     * Verifica che l'email fornita rappresenta una possibile email valida:
     * Controlla se la parte del nome utente, contiene lettere o numeri;
     * Controlla se la parte prima della '@', non contiene solo numeri oppure non inizia con un numero;
     * Controlla se la parte del dominio contiene lettere o numeri;
     * Controlla se contiene il DNS. es .it .com. biz .com etc.;
     * @param email Stringa da validare come email
     * @return {@code = true} Se e solo se, l'email rispetta i requisiti di validità
     * @throws UserException Se l'email non rispetta i requisiti di validità
     */
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

    /**
     * Controlla se la stringa contiene solo numeri
     * @param str Stringa da controllare
     * @return {@code = true} Se e solo se, la stringa contiene solo numeri.
     * Altrimenti {@code = false}
     */
    private static boolean containsAllNumbers(String str){
        for (char c: str.toCharArray())
            if (Character.isLetter(c))
                return false;
        return true;
    }

    /**
     * Ritorna una stringa che contiene le informazioni dell'utente
     * @return String che contiene i dati dell'utente divisi dal separatore ';'
     */
    public String toString() {
        return userId + ";" + email + ";" + psw + ";" + super.toString();
    }

}
