package it.uninsubria.app.managers;

import it.uninsubria.app.emotionalsongs.Feedback;
import it.uninsubria.app.input.exceptions.InputException;
import it.uninsubria.app.managers.utils.FileManager;
import it.uninsubria.app.users.User;
import it.uninsubria.app.users.utils.Address;
import it.uninsubria.app.users.utils.TypeStreet;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * Classe che rappresenta il sistema di gestione degli utenti all'interno dell'applicazione
 * @author  Erik Gurzau
 * @author  Alessia Metaj
 * @author  Sara Biavaschi
 * @version 1.0.0
 * @see     it.uninsubria.app.users.User
 * @see     it.uninsubria.app.managers.utils.FileManager
 */
public class UsersManager {
    /**
     * Percorso del file degli utenti registrati all'applicazione
     */
    private String pathFile = "./data/UtentiRegistrati.txt";


    /**
     * Mappa degli utenti con chiave l'ID utente e valore l'oggetto User
     */
    private HashMap<String, User> mapUsers;

    /**
     * Gestore I/O del file degli utenti registrati
     */
    private FileManager fm;


    /**
     * Costruttore del gestore degli utenti
     */
    public UsersManager() {
        mapUsers = new HashMap<>();
        fm = new FileManager(pathFile);
        loadData();
    }

    /**
     * Legge i dati dal file e li converte in una lista di utenti
     */
    public void loadData() {
        parseData(fm.getContent());
    }

    /**
     * Converte una lista di stringhe, che corrispondono alle righe del file .txt
     * contenenti tutte gli utenti registrati dell'applicazione, in una mappa di utenti
     * @param rowsFile Lista di stringhe con le informazioni degli utenti
     * @return Una lista di canzoni
     */
    private void parseData(Vector<String> rowsFile) {
        TypeStreet typeStreet;

        for (String row: rowsFile) {
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
                mapUsers.put(email, u);
            }
        }
    }

    /**
     * Conta gli utenti totali dell'applicazione
     * @return Intero che corrisponde al numero totale degli utenti registrati
     */
    public int countUsers() {
        return mapUsers.size();
    }

    /**
     * Ricerca una un utente per un ID specificato
     * @param userId Intero che rappresenta l'ID dell'utente
     * @return L'utente con l'ID specificato
     */
    public User getUserById(int userId) {
        for (Map.Entry<String, User> entry : mapUsers.entrySet()) {
            User u = entry.getValue();
            if (u.getUserId() == userId)
                return u;
        }
        return null;
    }


    /**
     * Registra un nuovo utente nell'applicazione.
     * Aggiunge nell'indice la chiave (email) con il valore associato (oggetto User);
     * Aggiunge l'utente nella lista;
     * Aggiunge i dati dell'utente nel file divisi dal separatore ';'
     * @param user Ogetto User da registrare nell'applicazione
     * @return {@code true} Se e solo se, l'utente è stato registrato correttamente.
     * Altrimenti {@code false}.
     */
    public boolean register(User user) {
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
     * @return {@code true} Se e solo se, nell'indice esiste una chiave
     * uguale a quella specificata. Altrimenti {@code false}
     */
    public boolean contains(String email){
        return mapUsers.containsKey(email);
    }


    /**
     * Assegna ad un nuovo utente che si vuole registrare, il prossimo ID disponibile
     * @return Intero che corrisponde all'ID dell'utente
     */
    public int nextUserId(){
        if (mapUsers.isEmpty()) return 1;
        User[] vetUsers = (User[]) mapUsers.values().toArray();
        return vetUsers[vetUsers.length - 1].getUserId() + 1;
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



}
