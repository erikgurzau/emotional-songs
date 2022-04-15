package it.uninsubria.app.controllers;

import it.uninsubria.app.controllers.utils.SecurePassword;
import it.uninsubria.app.controllers.utils.FileManager;
import it.uninsubria.app.users.User;
import it.uninsubria.app.users.utils.Address;
import it.uninsubria.app.users.utils.TypeStreet;

import java.util.StringTokenizer;
import java.util.Vector;


public class UsersController {
    private String pathFile;
    private Vector<User> listUsers;
    private FileManager fm;

    public UsersController(String pathFile) {
        this.pathFile = pathFile;
        fm = new FileManager(pathFile);
        loadData();
    }

    public void loadData(){
        listUsers = loadData(fm.getContent());
    }

    private Vector<User> loadData(Vector<String> dbUsers) {
        Vector<User> list = new Vector<>();
        for (String row: dbUsers) {
            StringTokenizer st = new StringTokenizer(row, ";");
            while (st.hasMoreTokens()){
                int userId = Integer.parseInt(st.nextToken());
                String email = st.nextToken();
                String psw = st.nextToken();
                String name = st.nextToken();
                String surname = st.nextToken();
                String cf = st.nextToken();
                TypeStreet typeStreet = TypeStreet.decode(st.nextToken());
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

    public boolean registrazione(User user) {
        listUsers.add(user);
        return fm.println(user.toString(), 'a');
    }

    public int countUsers() {
        return listUsers.size();
    }

    public boolean login(String email, String cryptPsw){
        for (User u: listUsers) {
            if (u.login(email, cryptPsw)) return true;
        }
        return false;
    }

    public int getNextFreeID(){
        return listUsers.size() + 1;
    }

    public String encryptPsw(String psw) {
        return SecurePassword.encrypt(psw);
    }

    public String toString(){
        return listUsers.toString();
    }


    /*public static void main(String[] args) {
        UsersController uc = new UsersController();
        System.out.println(uc.toString());
        System.out.println(uc.countUsers());
        System.out.println(uc.login("erikgurzau@gmail.com", "e79a8581c65a2a45e5162f01187db0842c0dbc4c1"));
    }*/

}
