package it.uninsubria.app.managers;

import it.uninsubria.app.input.exceptions.InputException;
import it.uninsubria.app.managers.utils.FileManager;
import it.uninsubria.app.users.User;
import it.uninsubria.app.users.utils.Address;
import it.uninsubria.app.users.utils.TypeStreet;

import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Vector;


public class UsersManager {
    /**
     * Percorso del file degli utenti registrati all'applicazione
     */
    private String pathFile = "./data/UtentiRegistrati.txt";

    /**
     * Lista degli utenti registrati
     */
    private Vector<User> listUsers;

    /**
     * Indice degli utenti con chiave l'email e valore l'oggetto User
     */
    private HashMap<String, User> mapUsers;

    /**
     * Gestore I/O del file degli utenti registrati
     */
    private FileManager fm;


    /**
     * Costruttore
     */
    public UsersManager() {
        mapUsers = new HashMap<>();
        fm = new FileManager(pathFile);
        loadData();
    }


    public void loadData() {
        listUsers = parseData(fm.getContent());
    }

    private Vector<User> parseData(Vector<String> dbUsers) {
        Vector<User> list = new Vector<>();
        TypeStreet typeStreet;
        for (String row: dbUsers) {
            StringTokenizer st = new StringTokenizer(row, ";");
            while (st.hasMoreTokens()){
                int userId = Integer.parseInt(st.nextToken());
                String email = st.nextToken();
                String psw = st.nextToken();
                String name = st.nextToken();
                String surname = st.nextToken();
                String cf = st.nextToken();
                try { typeStreet = TypeStreet.decode(st.nextToken()); }
                catch (InputException e) { typeStreet = null; }
                String nameStreet = st.nextToken();
                int houseNumber = Integer.parseInt(st.nextToken());
                String postalCode = st.nextToken();
                String city = st.nextToken();
                String province = st.nextToken();

                Address address = new Address(typeStreet, nameStreet, houseNumber, postalCode, city, province);
                User u = new User(name, surname, cf, address, userId, email, psw);
                list.add(u);
                mapUsers.put(email, u);
            }
        }
        return list;
    }

    /**
     * Conta gli utenti totali dell'applicazione
     * @return Intero che corrisponde al numero totale degli utenti registrati
     */
    public int countUsers() {
        return listUsers.size();
    }


    /**
     * Registra un nuovo utente nell'applicazione.
     * Aggiunge nell'indice la chiava (email) con il valore associato (oggetto User);
     * Aggiunge l'utente nella lista;
     * Aggiunge i dati dell'utente nel file divisi dal separatore ';'
     * @param user Ogetto User da registrare nell'applicazione
     * @return {@code = true} Se e solo se, l'utente è stato registrato correttamente.
     * Altrimenti {@code = false}.
     */
    public boolean register(User user) {
        listUsers.add(user);
        mapUsers.put(user.getEmail(), user);
        return fm.println(user.toString(), 'a');
    }

    /**
     * Controlla se la password è corretta per autenticare l'utente
     * che vuole accedere con le proprie credenziali
     * @param email Stringa che contiene l'email dell'utente
     * @param psw Stringa che contiene la password criptata
     * @return
     */
    public boolean login(String email, String psw){
        User u = mapUsers.get(email);
        return u.comparePsw(psw);
    }


    /**
     * Controlla se nell'indice è contenuta l'email specificata
     * @param email Stringa che contiene l'email dell'utente
     * @return {@code = true} Se e solo se, nell'indice esiste una chiave
     * uguale a quella specificata. Altrimenti {@code = false}
     */
    public boolean contains(String email){
        return mapUsers.containsKey(email);
    }


    /**
     * Assegna ad un nuovo utente che si vuole registrare, l'ultimo ID disponibile
     * @return Intero che corrisponde all'ID dell'utente
     */
    public int nextUserId(){
        return listUsers.isEmpty()
                ? 1
                : listUsers.lastElement().getUserId() + 1;
    }


    /**
     * Restituisce l'utente in base all'email specificata.
     * @param email Stringa contentente l'email dell'utente
     * @return Un oggetto User. Se non esiste nessun utente
     * con l'email specificata restiruisce null.
     */
    public User getUserByEmail(String email) {
        return mapUsers.get(email);
    }

    /**
     * Ritorna una stringa che contiene le informazioni di tutti gli utenti
     * @return Stringa che contiene i dati di tutti gli utenti divisi dal separatore ';'
     */
    public String toString(){
        return listUsers.toString();
    }


}
