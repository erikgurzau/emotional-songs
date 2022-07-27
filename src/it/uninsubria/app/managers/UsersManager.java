package it.uninsubria.app.managers;

import it.uninsubria.app.input.exceptions.InputException;
import it.uninsubria.app.managers.utils.SecurePassword;
import it.uninsubria.app.managers.utils.FileManager;
import it.uninsubria.app.users.User;
import it.uninsubria.app.users.utils.Address;
import it.uninsubria.app.users.utils.TypeStreet;

import java.util.StringTokenizer;
import java.util.Vector;


public class UsersManager {
    /**
     * Percorso del file degli utenti registrati all'applicazione
     */
    private String pathFile = "./data/UtentiRegistrati.txt";
    private Vector<User> listUsers;
    private FileManager fm;


    public UsersManager() {
        fm = new FileManager(pathFile);
        loadData();
    }

    public void loadData() {
        listUsers = loadData(fm.getContent());
    }

    private Vector<User> loadData(Vector<String> dbUsers) {
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
                list.add(new User(name, surname, cf, address, userId, email, psw));
            }
        }
        return list;
    }

    public int countUsers() {
        return listUsers.size();
    }


    public boolean register(User user) {
        listUsers.add(user);
        return fm.println(user.toString(), 'a');
    }
    public boolean login(String email, String psw){
        String cryptPsw = SecurePassword.encrypt(psw);
        for (User u: listUsers) {
            if (u.login(email, cryptPsw))
                return true;
        }
        return false;
    }

    public String encryptPsw(String psw) {
        return SecurePassword.encrypt(psw);
    }


    public boolean contains(String email){
        for (User u: listUsers)
            if (u.getEmail().equals(email))
                return true;
        return false;
    }


    public int nextUserId(){
        return listUsers.isEmpty()
                ? 1
                : listUsers.lastElement().getUserId() + 1;
    }


    public User getUserByEmail(String email) {
        for(User user: listUsers)
            if(user.getEmail().equals(email))
                return user;

        return null;
    }

    public String toString(){
        return listUsers.toString();
    }


}
